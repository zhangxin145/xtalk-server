package com.xtalk.server.xtalkserver.service;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.xtalk.server.xtalkserver.entity.QVipEntity;
import com.xtalk.server.xtalkserver.entity.UserPermissionEntity;
import com.xtalk.server.xtalkserver.entity.UsersEntity;
import com.xtalk.server.xtalkserver.entity.VipEntity;
import com.xtalk.server.xtalkserver.model.VipEditModel;
import com.xtalk.server.xtalkserver.model.VipModel;
import com.xtalk.server.xtalkserver.model.VipQueryVo;
import com.xtalk.server.xtalkserver.repository.UserPermissionRepository;
import com.xtalk.server.xtalkserver.repository.UserRepository;
import com.xtalk.server.xtalkserver.repository.VipRepository;
import com.xtalk.server.xtalkserver.security.SecurityAuditor;
import org.apache.commons.lang3.StringUtils;
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
import java.util.stream.Collectors;

/**
 * @author xin.z
 * @date 2021/1/28 7:02 下午
 */
@Service
public class VipServiceImpl implements VipService {

    @Autowired
    SecurityAuditor securityAuditor;

    @Autowired
    UserPermissionRepository userPermissionRepository;

    @Autowired
    VipRepository vipRepository;

    @Autowired
    EntityManager entityManager;

    @Autowired
    UserRepository userRepository;


    @Override
    public UserPermissionEntity addPermission(UserPermissionEntity entity) {
        checkParam(entity);
        Long currentAdminId = (Long) securityAuditor.getCurrentAuditor().orElseGet(() -> {
            throw new RuntimeException("no login");
        });
        String name = entity.getName();
        Optional<UserPermissionEntity> check = userPermissionRepository.findByNameAndDeleted(name, 0);
        if (check.isPresent()) {
            throw new IllegalArgumentException("name 不可重复");
        }

        entity.setCid(currentAdminId);
        entity.setUid(currentAdminId);
        entity.setCtime(new Date());
        entity.setUtime(new Date());
        entity.setDeleted(0);
        return userPermissionRepository.save(entity);
    }

    private void checkParam(UserPermissionEntity entity) {
        Assert.isTrue(StringUtils.isNotBlank(entity.getName()), "权限名称不能为空");
    }

    @Override
    public UserPermissionEntity updatePermission(UserPermissionEntity entity) {
        checkParam(entity);
        Long id = entity.getId();
        UserPermissionEntity userPermissionEntity = userPermissionRepository.findById(id).orElseGet(() -> {
            throw new IllegalArgumentException("id err");
        });
        Optional<UserPermissionEntity> check = userPermissionRepository.findByNameAndDeleted(entity.getName(), 0);
        if (check.isPresent()) {
            if (check.get().getId() != entity.getId()) {
                throw new IllegalArgumentException("name 不可重复");
            }
        }
        Long currentAdminId = (Long) securityAuditor.getCurrentAuditor().orElseGet(() -> {
            throw new RuntimeException("no login");
        });
        entity.setUid(currentAdminId);
        entity.setUtime(new Date());
        return userPermissionRepository.save(entity);
    }

    @Override
    public UserPermissionEntity delPermission(Long id) {
        UserPermissionEntity update = userPermissionRepository.findById(id).orElseGet(() -> {
            throw new IllegalArgumentException("id err");
        });
        update.setDeleted(1);
        update.setUtime(new Date());
        return userPermissionRepository.save(update);
    }

    @Override
    public Optional<List<UserPermissionEntity>> getPermission() {
        return userPermissionRepository.findByDeleted(0);

    }

    @Override
    public VipEntity addVip(VipEditModel model) {
        checkVipParam(model);
        Long currentAdminId = (Long) securityAuditor.getCurrentAuditor().orElseGet(() -> {
            throw new RuntimeException("no login");
        });
        VipEntity vipEntity = new VipEntity();

        BeanUtils.copyProperties(model, vipEntity);
        vipEntity.setStatus(0);
        vipEntity.setCid(currentAdminId);
        vipEntity.setUid(currentAdminId);
        vipEntity.setCtime(new Date());
        vipEntity.setUtime(new Date());
        vipEntity.setDeleted(0);
        List<Long> permissionIds = model.getPermissionIds();
        String permission = permissionIds.stream().map(String::valueOf).collect(Collectors.joining("_", "", ""));

        vipEntity.setPermission(permission);

        return vipRepository.save(vipEntity);
    }

    private void checkVipParam(VipEditModel model) {
        Assert.isTrue(StringUtils.isNotBlank(model.getName()), "请输入等级名称");
        Assert.isTrue(StringUtils.isNotBlank(model.getIntro()), "请输入等级描述");
        Assert.isTrue(model.getPermissionIds() != null && model.getPermissionIds().size() > 0, "请输入等级名称");

    }

    @Override
    public VipEntity updateVip(VipEditModel model) {
        checkVipParam(model);
        Long currentAdminId = (Long) securityAuditor.getCurrentAuditor().orElseGet(() -> {
            throw new RuntimeException("no login");
        });
        Long id = model.getId();
        VipEntity update = vipRepository.findById(id).orElseGet(() -> {
            throw new IllegalArgumentException("id err");
        });
        update.setName(model.getName());
        update.setIntro(model.getIntro());
        List<Long> permissionIds = model.getPermissionIds();
        String permission = permissionIds.stream().map(String::valueOf).collect(Collectors.joining("_", "", ""));
        update.setPermission(permission);
        Long sort = model.getSort();
        if (sort != null) {
            update.setSort(sort);
        }
        update.setUid(currentAdminId);
        update.setUtime(new Date());
        return vipRepository.save(update);
    }

    @Override
    public VipEntity delVip(Long id) {
        Long currentAdminId = (Long) securityAuditor.getCurrentAuditor().orElseGet(() -> {
            throw new RuntimeException("no login");
        });
        VipEntity update = vipRepository.findById(id).orElseGet(() -> {
            throw new IllegalArgumentException("id err");
        });
        update.setDeleted(1);
        update.setUid(currentAdminId);
        update.setUtime(new Date());
        return vipRepository.save(update);
    }

    @Override
    public List<VipEntity> updateVipStatus(List<Long> vipIds, Integer status) {
        Long currentAdminId = (Long) securityAuditor.getCurrentAuditor().orElseGet(() -> {
            throw new RuntimeException("no login");
        });
        List<VipEntity> vips = vipRepository.findAllById(vipIds);
        Assert.isTrue(vips != null && vips.size() > 0, "ids 有误");
        vips.forEach(a -> {
            a.setStatus(status);
            a.setUid(currentAdminId);
        });
        return vipRepository.saveAll(vips);
    }

    @Override
    public Optional<List<VipEntity>> getAllVip() {
        return vipRepository.findByDeleted(0);
    }


    @Override
    public Page<VipModel> getVipPage(VipQueryVo queryVo) {
        int pageNumber = queryVo.getPageNumber() == null || queryVo.getPageNumber() < 1 ? 1 : queryVo.getPageNumber();
        int pageSize = queryVo.getPageSize() == null || queryVo.getPageSize() < 1 ? 20 : queryVo.getPageSize();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QVipEntity vipEntity = QVipEntity.vipEntity;
        JPAQuery<VipEntity> query = jpaQueryFactory.selectFrom(vipEntity);

        String name = queryVo.getName();
        if (StringUtils.isNotBlank(name)) {
            query.where(vipEntity.name.like("%" + name + "%"));
        }
        Integer status = queryVo.getStatus();
        if (status != null) {
            query.where(vipEntity.status.eq(status));
        }
        query.where(vipEntity.deleted.eq(0));
        long count = query.fetchCount();
        OrderSpecifier<Long> orderBy = vipEntity.id.asc();
        query.orderBy(orderBy)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());
        List<VipEntity> result = query.fetch();
        List<Long> vipIds = result.stream().map(VipEntity::getId).collect(Collectors.toList());
        Optional<List<UsersEntity>> users = userRepository.findByVipIdIn(vipIds);
        Map<Long, List<UsersEntity>> map = new HashMap<>();
        if (users.isPresent()) {
            map = users.get().stream().collect(Collectors.groupingBy(UsersEntity::getVipId));
        }

        Map<Long, List<UsersEntity>> finalMap = map;
        List<VipModel> models = result.stream().map(a -> {
            VipModel vipModel = new VipModel();
            BeanUtils.copyProperties(a, vipModel);
            Long id = a.getId();
            List<UsersEntity> usersEntities = finalMap.get(id);
            if (usersEntities != null) {
                vipModel.setUserNumber(usersEntities.size());
            } else {
                vipModel.setUserNumber(0);
            }
            return vipModel;
        }).collect(Collectors.toList());

        return new PageImpl(models, pageable, count);
    }
}
