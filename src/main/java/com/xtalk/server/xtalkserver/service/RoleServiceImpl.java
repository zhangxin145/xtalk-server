package com.xtalk.server.xtalkserver.service;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.xtalk.server.xtalkserver.constant.RoleStatusEnum;
import com.xtalk.server.xtalkserver.entity.*;
import com.xtalk.server.xtalkserver.model.RoleChangeStatusModel;
import com.xtalk.server.xtalkserver.model.RoleEditModel;
import com.xtalk.server.xtalkserver.model.RoleModel;
import com.xtalk.server.xtalkserver.model.RoleQueryVo;
import com.xtalk.server.xtalkserver.repository.AdminRoleRepository;
import com.xtalk.server.xtalkserver.repository.RolePermissionRepository;
import com.xtalk.server.xtalkserver.repository.RoleRepository;
import com.xtalk.server.xtalkserver.security.SecurityAuditor;
import com.xtalk.server.xtalkserver.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author xin.z
 * @date 2021/1/21 8:44 下午
 */
@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    RolePermissionRepository rolePermissionRepository;

    @Autowired
    AdminRoleRepository adminRoleRepository;

    @Autowired
    EntityManager entityManager;

    @Autowired
    SecurityAuditor securityAuditor;

    @Override
    public Page<RoleModel> getResultPage(RoleQueryVo queryVo) {
        int pageNumber = queryVo.getPageNumber() == null || queryVo.getPageNumber() < 1 ? 1 : queryVo.getPageNumber();
        int pageSize = queryVo.getPageSize() == null || queryVo.getPageSize() < 1 ? 20 : queryVo.getPageSize();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QRoleEntity roleEntity = QRoleEntity.roleEntity;
        JPAQuery<RoleEntity> query = jpaQueryFactory.selectFrom(roleEntity);
        String name = queryVo.getName();
        if (StringUtils.isNotBlank(name)) {
            query.where(roleEntity.name.like("%" + name + "%"));
        }
        Integer status = queryVo.getStatus();
        if (status != null) {
            query.where(roleEntity.status.eq(status));
        }
        if (StringUtils.isNoneBlank(queryVo.getCreateTimeBegin())) {
            String openTimeBegin = queryVo.getCreateTimeBegin();
            Date localDateTime = DateUtils.parseDate(openTimeBegin, "yyyy-MM-dd HH:mm:ss");
            query.where(roleEntity.ctime.goe(localDateTime));
        }
        if (StringUtils.isNoneBlank(queryVo.getCreateTimeEnd())) {
            String openTimeEnd = queryVo.getCreateTimeEnd();
            Date localDateTime = DateUtils.parseDate(openTimeEnd, "yyyy-MM-dd HH:mm:ss");
            query.where(roleEntity.ctime.loe(localDateTime));
        }

        query.where(roleEntity.deleted.eq(0));
        long count = query.fetchCount();
        OrderSpecifier<Long> orderBy = roleEntity.id.asc();
        query.orderBy(orderBy)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());
        List<RoleEntity> result = Optional.ofNullable(query.fetch())
                .orElseGet(() -> {
                    throw new RuntimeException("暂无数据");
                });
        List<RoleModel> roleModels = result.stream().map(a -> {
            RoleModel roleModel = new RoleModel();
            BeanUtils.copyProperties(a, roleModel);
            return roleModel;
        }).collect(Collectors.toList());
        List<Long> ids = result
                .stream()
                .map(RoleEntity::getId)
                .collect(Collectors.toList());
        Optional<List<RolePermissionEntity>> rolePermissionEntityList = rolePermissionRepository.findByRoleIdInAndDeleted(ids, 0);

        rolePermissionEntityList.ifPresent(a -> {
            Map<Long, List<RolePermissionEntity>> resultMap = a
                    .stream()
                    .collect(Collectors.groupingBy(RolePermissionEntity::getRoleId));
            if (resultMap.size() > 0) {
                roleModels.forEach(r -> {
                    Long id = r.getId();
                    List<RolePermissionEntity> rolePermissionEntities = resultMap.get(id);
                    if (rolePermissionEntities != null && rolePermissionEntities.size() > 0) {
                        r.setRolePermissions(rolePermissionEntities);
                    }
                });
            }
        });

        Optional<List<AdminRoleEntity>> adminRoleEntities = adminRoleRepository.findByRoleIdInAndDeleted(ids, 0);
        adminRoleEntities.ifPresent(a -> {
            Map<Long, List<AdminRoleEntity>> adminRolesMap = a.stream()
                    .collect(Collectors.groupingBy(AdminRoleEntity::getRoleId));
            if (adminRolesMap.size() > 0) {
                roleModels.forEach(r -> {
                    Long id = r.getId();
                    List<AdminRoleEntity> adminRoleEntityList = adminRolesMap.get(id);
                    Integer adminNUmber = adminRoleEntityList != null && adminRoleEntityList.size() > 0 ? adminRoleEntityList.size() : 0;
                    r.setAdminNumber(adminNUmber);
                });
            }

        });
        return new PageImpl(roleModels, pageable, count);
    }

    @Override
    public RoleEntity add(RoleEntity entity) {
        checkParam(entity);
        Optional<List<RoleEntity>> roles = roleRepository.findByNameAndDeleted(entity.getName(), 0);
        roles.ifPresent(a -> {
            if (a.size() > 0) {
                throw new IllegalArgumentException("name 有误，请重新输入");
            }
        });
        Long currentAdminId = (Long) securityAuditor.getCurrentAuditor().orElseGet(() -> {
            throw new RuntimeException("no login");
        });
        entity.setStatus(RoleStatusEnum.ROLE_STATUS_NORMAL.getStatus());
        entity.setCtime(new Date());
        entity.setCid(currentAdminId);
        entity.setUtime(new Date());
        entity.setUid(currentAdminId);
        entity.setDeleted(0);
        return roleRepository.save(entity);
    }

    private void checkParam(RoleEntity entity) {
        Assert.isTrue(StringUtils.isNotBlank(entity.getName()),
                "角色名称不能为空");
        Assert.isTrue(StringUtils.isNotBlank(entity.getIntro()),
                "角色介绍不能为空");
    }

    @Override
    public RoleEntity update(RoleEntity entity) {
        checkParam(entity);
        Assert.isTrue(entity.getId() != null && entity.getId() > 0,
                "id 不能为空");
        RoleEntity update = roleRepository.findById(entity.getId())
                .orElseGet(() -> {
                    throw new IllegalArgumentException("id有误");
                });
        Optional<List<RoleEntity>> roles = roleRepository.findByNameAndDeleted(entity.getName(), 0);
        roles.ifPresent(a -> {
            if (a.size() > 0) {
                Long id = a.get(0).getId();
                if (id != entity.getId()) {
                    throw new IllegalArgumentException("name 有误，请重新输入");
                }
            }
        });
        Long currentAdminId = (Long) securityAuditor.getCurrentAuditor().orElseGet(() -> {
            throw new RuntimeException("no login");
        });
        update.setName(entity.getName());
        update.setIntro(entity.getIntro());
        update.setUid(currentAdminId);
        update.setUtime(new Date());
        return roleRepository.save(update);
    }

    @Override
    public RoleEntity del(Long id) {
        RoleEntity update = roleRepository.findById(id)
                .orElseGet(() -> {
                    throw new IllegalArgumentException("id有误");
                });
        Long currentAdminId = (Long) securityAuditor.getCurrentAuditor().orElseGet(() -> {
            throw new RuntimeException("no login");
        });
        update.setDeleted(1);
        update.setUid(currentAdminId);
        Optional<List<AdminRoleEntity>> adminRoleEntities = adminRoleRepository.findByRoleIdAndDeleted(id, 0);
        adminRoleEntities.ifPresent(a -> {
            a.forEach(adminRoleEntity -> {
                adminRoleEntity.setDeleted(1);
            });
            adminRoleRepository.saveAll(a);
        });
        return roleRepository.save(update);
    }

    @Override
    @Transactional
    public RoleEntity editRole(RoleEditModel model) {
        Assert.isTrue(model.getRoleId() != null && model.getRoleId() > 0,
                "roleId 不能为空");
        RoleEntity role = roleRepository.findById(model.getRoleId())
                .orElseGet(() -> {
                    throw new IllegalArgumentException("id有误");
                });
        List<Long> permissionIds = model.getPermissionIds();
        Assert.isTrue(permissionIds != null && permissionIds.size() > 0,
                "权限不能为空");
        Optional<List<RolePermissionEntity>> rolePermissionResult = rolePermissionRepository.findByRoleIdAndDeleted(model.getRoleId(), 0);
        rolePermissionResult.ifPresent(r -> {
            if (r.size() > 0) {
                r.stream().forEach(a -> {
                    a.setDeleted(1);
                });
                rolePermissionRepository.saveAll(r);
            }
        });

        List<RolePermissionEntity> result = permissionIds
                .stream()
                .map(a -> buildRolePermissionEntity(a, model.getRoleId()))
                .collect(Collectors.toList());
        rolePermissionRepository.saveAll(result);
        return role;
    }

    private RolePermissionEntity buildRolePermissionEntity(Long permissionId, Long roleId) {
        Long currentAdminId = (Long) securityAuditor.getCurrentAuditor().orElseGet(() -> {
            throw new RuntimeException("no login");
        });
        RolePermissionEntity entity = new RolePermissionEntity();
        entity.setRoleId(roleId);
        entity.setPermissionId(permissionId);
        entity.setCtime(new Date());
        entity.setUtime(new Date());
        entity.setCid(currentAdminId);
        entity.setUid(currentAdminId);
        entity.setDeleted(0);
        return entity;
    }

    @Override
    public List<RoleEntity> changeStatus(RoleChangeStatusModel model) {
        List<Long> ids = model.getRoleIds();
        Assert.isTrue(ids != null && ids.size() > 0,
                "roleIds不能为空");
        Integer status = model.getStatus();
        Assert.isTrue(status != null && (status == 0 || status == 1),
                "status 有误");
        List<RoleEntity> roles = roleRepository.findAllById(ids);
        if (roles != null && roles.size() > 0) {
            roles.stream().forEach(a -> {
                a.setStatus(status);
            });
            return roleRepository.saveAll(roles);
        }
        return null;
    }

    @Override
    public RoleModel getById(Long id) {
        RoleEntity role = roleRepository.findById(id)
                .orElseGet(() -> {
                    throw new IllegalArgumentException("roleId 有误");
                });
        RoleModel roleModel = new RoleModel();
        BeanUtils.copyProperties(role, roleModel);
        Optional<List<RolePermissionEntity>> rolePermissions = rolePermissionRepository
                .findByRoleIdAndDeleted(id, 0);
        rolePermissions.ifPresent(r -> {
            if (r.size() > 0) {
                roleModel.setRolePermissions(r);
            }
        });
        return roleModel;
    }

    @Override
    public List<RoleEntity> getAllRole(Integer status) {
        QRoleEntity roleEntity = QRoleEntity.roleEntity;
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        JPAQuery<RoleEntity> query = jpaQueryFactory.selectFrom(roleEntity)
                .where(roleEntity.deleted.eq(0));
        if (status != -1) {
            query.where(roleEntity.status.eq(status));
        }
        return query.fetch();
    }
}
