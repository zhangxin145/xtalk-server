package com.xtalk.server.xtalkserver.service;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.xtalk.server.xtalkserver.entity.*;
import com.xtalk.server.xtalkserver.model.*;
import com.xtalk.server.xtalkserver.repository.*;
import com.xtalk.server.xtalkserver.security.SecurityAuditor;
import com.xtalk.server.xtalkserver.security.SecurityConst;
import com.xtalk.server.xtalkserver.security.SecurityUtil;
import com.xtalk.server.xtalkserver.utils.DateUtils;
import com.xtalk.server.xtalkserver.utils.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author xin.z
 * @date 2021/1/21 8:43 下午
 */
@Service
@Slf4j
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    AdminUserRepository adminUserRepository;

    @Autowired
    AdminRoleRepository adminRoleRepository;

    @Autowired
    EntityManager entityManager;

    @Autowired
    SecurityAuditor securityAuditor;

    @Autowired
    RolePermissionRepository rolePermissionRepository;

    @Autowired
    PermissionService permissionService;

    @Autowired
    PermissionRepository permissionRepository;

    @Autowired
    RoleRepository roleRepository;


    @Override
    public AdminUserLoginModel loginByPwd(String account, String password) throws Exception {
        Optional<AdminEntity> optional = adminUserRepository.findByAccountAndDeleted(account, 0);
        AdminEntity adminUser = optional.<RuntimeException>orElseThrow(() -> {
            log.warn("[loginByPwd] 登录失败, 账号不存在: [{}]", account);
            throw new IllegalArgumentException("登录失败");
        });
        if (adminUser.getStatus() != 0) {
            throw new IllegalArgumentException("该用户已被禁用");
        }
        if (!adminUser.getPassword().equals(MD5Utils.stringToMD5(password))) {
            throw new IllegalArgumentException("密码错误");
        }


        return buildUserLogin(adminUser);
    }

    @Override
    public AdminEntity add(AdminVo adminVo) {
        AdminEntity entity = new AdminEntity();
        BeanUtils.copyProperties(adminVo, entity);
        checkParam(entity);
        List<Long> roleIds = adminVo.getRoleIds();
        Assert.isTrue(roleIds != null && roleIds.size() > 0,
                "请选中用户角色");
        Optional<AdminEntity> adminEntity = adminUserRepository.findByAccountAndDeleted(entity.getAccount(), 0);
        adminEntity.ifPresent(a -> {
            throw new IllegalArgumentException("account 已存在");
        });
        Long currentAdminId = (Long) securityAuditor.getCurrentAuditor().orElseGet(() -> {
            throw new RuntimeException("no login");
        });
        entity.setPassword("");
        entity.setCtime(new Date());
        entity.setUtime(new Date());
        entity.setCid(currentAdminId);
        entity.setUid(currentAdminId);
        entity.setStatus(0);
        entity.setDeleted(0);
        entity.setPassword(MD5Utils.stringToMD5("aa123"));
        AdminEntity save = adminUserRepository.save(entity);

        if (roleIds != null && roleIds.size() > 0) {
            List<AdminRoleEntity> adminRoleEntityList = buildAdminRoles(save.getId(), roleIds, currentAdminId);
            adminRoleRepository.saveAll(adminRoleEntityList);
        }

        return save;
    }

    private List<AdminRoleEntity> buildAdminRoles(Long id, List<Long> roleIds, Long currentAdminId) {
        return roleIds.stream().map(a -> {
            AdminRoleEntity adminRoleEntity = new AdminRoleEntity();
            adminRoleEntity.setAdminId(id);
            adminRoleEntity.setRoleId(a);
            adminRoleEntity.setCtime(new Date());
            adminRoleEntity.setEtime(new Date());
            adminRoleEntity.setCid(currentAdminId);
            adminRoleEntity.setEid(currentAdminId);
            adminRoleEntity.setDeleted(0);
            return adminRoleEntity;
        }).collect(Collectors.toList());
    }

    private void checkParam(AdminEntity entity) {
        Assert.isTrue(StringUtils.isNotBlank(entity.getName()),
                "姓名不能为空");
        Assert.isTrue(StringUtils.isNotBlank(entity.getAccount()),
                "登录名不能为空");
        Assert.isTrue(StringUtils.isNotBlank(entity.getMobile()),
                "电话号不能为空");
    }

    @Override
    public AdminEntity update(AdminVo adminVo) {
        AdminEntity entity = new AdminEntity();
        BeanUtils.copyProperties(adminVo, entity);
        checkParam(entity);
        Long id = entity.getId();
        Assert.isTrue(id != null && id > 0,
                "id不能为空");
        List<Long> roleIds = adminVo.getRoleIds();
        Assert.isTrue(roleIds != null && roleIds.size() > 0,
                "请选中用户角色");
        AdminEntity update = adminUserRepository.findById(id).orElseGet(() -> {
            throw new IllegalArgumentException("id 有误");
        });
        Optional<AdminEntity> adminEntity = adminUserRepository.findByAccountAndDeleted(entity.getAccount(), 0);
        adminEntity.ifPresent(a -> {
            if (!(a.getId() == id)) {
                throw new IllegalArgumentException("account 已存在");
            }
        });
        Long currentAdminId = (Long) securityAuditor.getCurrentAuditor().orElseGet(() -> {
            throw new RuntimeException("no login");
        });
        update.setName(entity.getName());
        update.setMobile(entity.getMobile());
        update.setAccount(entity.getAccount());
        update.setUtime(new Date());
        update.setUid(currentAdminId);
        delRolesByAdminId(id);
        List<AdminRoleEntity> adminRoleEntityList = buildAdminRoles(id, roleIds, currentAdminId);
        if (adminRoleEntityList != null && adminRoleEntityList.size() > 0) {
            adminRoleRepository.saveAll(adminRoleEntityList);
        }
        return adminUserRepository.save(update);
    }

    private void delRolesByAdminId(Long adminId) {
        Optional<List<AdminRoleEntity>> adminRoleEntities = adminRoleRepository.findByAdminIdAndDeleted(adminId, 0);
        adminRoleEntities.ifPresent(a -> {
            if (a.size() > 0) {
                a.stream().forEach(c -> {
                    c.setDeleted(1);
                });
                adminRoleRepository.saveAll(a);
            }
        });
    }

    @Override
    public AdminEntity del(Long id) {
        AdminEntity update = adminUserRepository.findById(id).orElseGet(() -> {
            throw new IllegalArgumentException("id 有误");
        });
        Long currentAdminId = (Long) securityAuditor.getCurrentAuditor().orElseGet(() -> {
            throw new RuntimeException("no login");
        });
        update.setDeleted(1);
        update.setUid(currentAdminId);
        update.setUtime(new Date());
        Optional<List<AdminRoleEntity>> adminRoles = adminRoleRepository.findByAdminIdAndDeleted(id, 0);
        adminRoles.ifPresent(a->{
            a.forEach(r->{
                r.setDeleted(1);
            });
            adminRoleRepository.saveAll(a);
        });
        return adminUserRepository.save(update);
    }

    @Override
    public List<AdminEntity> changeStatus(AdminChangeStatusModel model) {
        List<Long> ids = model.getAdminId();
        Assert.isTrue(ids != null && ids.size() > 0,
                "adminIds不能为空");
        Integer status = model.getStatus();
        Assert.isTrue(status != null && (status == 0 || status == 1),
                "status 有误");
        List<AdminEntity> admins = adminUserRepository.findAllById(ids);
        if (admins != null && admins.size() > 0) {
            admins.stream().forEach(a -> {
                a.setStatus(status);
            });
            return adminUserRepository.saveAll(admins);
        }
        return null;
    }

    @Override
    public AdminModel getById(Long id) {
        AdminEntity admin = adminUserRepository.findById(id).orElseGet(() -> {
            throw new IllegalArgumentException("id 有误");
        });
        AdminModel adminModel = new AdminModel();
        BeanUtils.copyProperties(admin, adminModel);
        ArrayList<Long> ids = new ArrayList<>();
        ids.add(id);
        List<AdminRoleModel> adminRoleModels = getAdminRoleModels(ids);
        if (adminRoleModels != null && adminRoleModels.size() > 0) {
            adminModel.setAdminRoles(adminRoleModels);
        }
        return adminModel;
    }

    @Override
    public AdminEntity setPassById(Long id, String pass, String rePass) {
        AdminEntity admin = adminUserRepository.findById(id).orElseGet(() -> {
            throw new IllegalArgumentException("id 有误");
        });

        String check = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z_]{4,20}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(pass);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("密码格式有误，请重新输入");
        }
        Assert.isTrue(pass.equals(rePass),
                "俩次输入密码不同，请重新输入");
        admin.setPassword(MD5Utils.stringToMD5(pass));
        admin.setUtime(new Date());
        admin.setUid(1L);
        return adminUserRepository.save(admin);
    }

    @Override
    public Page<List<AdminModel>> getResultPage(AdminQueryVo queryVo) {

        int pageNumber = queryVo.getPageNumber() == null || queryVo.getPageNumber() < 1 ? 1 : queryVo.getPageNumber();
        int pageSize = queryVo.getPageSize() == null || queryVo.getPageSize() < 1 ? 20 : queryVo.getPageSize();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);

        QAdminEntity adminEntity = QAdminEntity.adminEntity;
        JPAQuery<AdminEntity> query = jpaQueryFactory.selectFrom(adminEntity);
        List<Long> roleIds = queryVo.getRoleIds();
        if (roleIds != null && roleIds.size() > 0) {
            Optional<List<AdminRoleEntity>> adminRoleEntities = adminRoleRepository.findByRoleIdInAndDeleted(roleIds, 0);
            adminRoleEntities.ifPresent(a -> {
                if (a.size() > 0) {
                    List<Long> adminIds = a.stream().map(AdminRoleEntity::getAdminId).distinct().collect(Collectors.toList());
                    query.where(adminEntity.id.in(adminIds));
                }
            });
        }
        String name = queryVo.getName();
        if (StringUtils.isNotBlank(name)) {
            query.where(adminEntity.name.like("%" + name + "%"));
        }


        Integer status = queryVo.getStatus();
        if (status != null) {
            query.where(adminEntity.status.eq(status));
        }

        if (StringUtils.isNoneBlank(queryVo.getCreateTimeBegin())) {
            String openTimeBegin = queryVo.getCreateTimeBegin();
            Date localDateTime = DateUtils.parseDate(openTimeBegin, "yyyy-MM-dd HH:mm:ss");
            query.where(adminEntity.ctime.goe(localDateTime));
        }
        if (StringUtils.isNoneBlank(queryVo.getCreateTimeEnd())) {
            String openTimeEnd = queryVo.getCreateTimeEnd();
            Date localDateTime = DateUtils.parseDate(openTimeEnd, "yyyy-MM-dd HH:mm:ss");
            query.where(adminEntity.ctime.loe(localDateTime));
        }
        if (StringUtils.isNotBlank(queryVo.getMobile())) {
            query.where(adminEntity.mobile.like("%" + queryVo.getMobile() + "%"));
        }
        Long id = queryVo.getId();
        if (id != null && id > 0) {
            query.where(adminEntity.id.eq(id));
        }
        query.where(adminEntity.deleted.eq(0));
        long count = query.fetchCount();
        OrderSpecifier<Long> orderBy = adminEntity.id.asc();
        query.orderBy(orderBy)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());
        List<AdminEntity> result = Optional.ofNullable(query.fetch())
                .orElseGet(() -> {
                    throw new RuntimeException("暂无数据");
                });

        List<AdminModel> adminModels = result.stream().map(a -> {
            AdminModel adminModel = new AdminModel();
            BeanUtils.copyProperties(a, adminModel);
            return adminModel;
        }).collect(Collectors.toList());
        List<Long> ids = result
                .stream()
                .map(AdminEntity::getId)
                .collect(Collectors.toList());

        List<AdminRoleModel> adminRoleModels = getAdminRoleModels(ids);

        if (adminRoleModels != null || adminRoleModels.size() > 0) {
            Map<Long, List<AdminRoleModel>> adminRoleMap = adminRoleModels.stream()
                    .collect(Collectors.groupingBy(AdminRoleModel::getAdminId));
            adminModels.forEach(a -> {
                Long adminId = a.getId();
                List<AdminRoleModel> models = adminRoleMap.get(adminId);
                if (models != null && models.size() > 0) {
                    a.setAdminRoles(models);
                }
            });

        }


        return new PageImpl(adminModels, pageable, count);
    }

    @Override
    public List<PermissionNodeModel> getPermissionByAdminId(Long adminId) {

        if (adminId == null || adminId < 1) {
            adminId = (Long) securityAuditor.getCurrentAuditor().orElseGet(() -> {
                throw new RuntimeException("no login");
            });
        }
        Optional<List<AdminRoleEntity>> adminRoles = adminRoleRepository.findByAdminIdAndDeleted(adminId, 0);

        List<PermissionNodeModel> result = new ArrayList<>();
        adminRoles.ifPresent(a -> {
            if (a.size() > 0) {
                List<Long> roles = a.stream().map(AdminRoleEntity::getRoleId).collect(Collectors.toList());
                Optional<List<RolePermissionEntity>> rolePermissionEntities = rolePermissionRepository.findByRoleIdInAndDeleted(roles, 0);
                rolePermissionEntities.ifPresent(r -> {
                    if (r.size() > 0) {
                        List<Long> permissions = r.stream().map(RolePermissionEntity::getPermissionId).distinct().collect(Collectors.toList());
                        Optional<List<PermissionEntity>> permissionEntities = permissionRepository.findByIdInAndDeleted(permissions, 0);
                        permissionEntities.ifPresent(per -> {
                            if (per.size() > 0) {
                                List<PermissionNodeModel> all = per.stream()
                                        .map(p -> {
                                            PermissionNodeModel model = new PermissionNodeModel();
                                            BeanUtils.copyProperties(p, model);
                                            return model;
                                        }).collect(Collectors.toList());

                                List<PermissionNodeModel> parent = all.stream().filter(p -> p.getPid() == 0)
                                        .map(
                                                (m) -> {
                                                    m.setChildList(getChildrens(m, all));
                                                    return m;
                                                }
                                        ).collect(Collectors.toList());

                                result.addAll(parent);
                            }
                        });


                    }
                });
            }

        });

        return result;
    }

    private List<AdminRoleModel> getAdminRoleModels(List<Long> ids) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QAdminRoleEntity adminRoleEntity = QAdminRoleEntity.adminRoleEntity;
        QRoleEntity roleEntity = QRoleEntity.roleEntity;
        return jpaQueryFactory
                .select(Projections.bean(AdminRoleModel.class,
                        adminRoleEntity.adminId,
                        adminRoleEntity.roleId,
                        roleEntity.name)
                ).from(adminRoleEntity, roleEntity)
                .where(adminRoleEntity.roleId.eq(roleEntity.id))
                .where(adminRoleEntity.adminId.in(ids))
                .where(adminRoleEntity.deleted.eq(0))
                .where(roleEntity.deleted.eq(0))
                .fetch();
    }


    private AdminUserLoginModel buildUserLogin(AdminEntity adminUser) throws Exception {
        UserAuthModel userAuthModel = token(adminUser.getId().toString(), adminUser.getPassword());

        if (userAuthModel == null || StringUtils.isEmpty(userAuthModel.getToken())) {
            throw new Exception("登录失败");
        }
        AdminUserLoginModel adminUserLoginModel = new AdminUserLoginModel();
        Optional<List<AdminRoleEntity>> adminRoleEntities = adminRoleRepository.findByAdminIdAndDeleted(adminUser.getId(), 0);
        adminRoleEntities.ifPresent(a -> {
            List<Long> roles = a.stream().map(AdminRoleEntity::getRoleId).collect(Collectors.toList());
            List<RoleEntity> roleEntities = roleRepository.findAllById(roles);
            if (roleEntities != null) {
                adminUserLoginModel.setRoles(roleEntities);
            }
            Optional<List<RolePermissionEntity>> rolePermissionEntities = rolePermissionRepository.findByRoleIdInAndDeleted(roles, 0);
            rolePermissionEntities.ifPresent(r -> {
                List<Long> permissions = r.stream().map(RolePermissionEntity::getPermissionId).collect(Collectors.toList());
                List<PermissionEntity> permissionEntities = permissionRepository.findAllById(permissions);
                if (permissions != null) {
                    adminUserLoginModel.setPerminssion(permissionEntities);

                }
            });

        });

        adminUser.setPassword("");
        adminUserLoginModel.setAdmin(adminUser)
                .setToken(userAuthModel);
        return adminUserLoginModel;

    }

    // 默认过期7天
    public UserAuthModel token(String uid, String password) {
        long backendId = NumberUtils.toLong(uid, 0L);
        String token = SecurityUtil.getBackendToken(backendId);

        return new UserAuthModel()
                .setToken(SecurityConst.prefix + token)
                .setExpire(3600 * 24 * 7);
    }


    public List<PermissionNodeModel> getChildrens(PermissionNodeModel
                                                          root, List<PermissionNodeModel> all) {
        List<PermissionNodeModel> children = all.stream().filter(m -> {
            return Objects.equals(m.getPid(), root.getId());
        }).map(
                (m) -> {
                    m.setChildList(getChildrens(m, all));
                    return m;
                }
        ).collect(Collectors.toList());
        return children;
    }
}
