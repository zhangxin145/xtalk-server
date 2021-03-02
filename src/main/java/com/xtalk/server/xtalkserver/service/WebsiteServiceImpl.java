package com.xtalk.server.xtalkserver.service;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.xtalk.server.xtalkserver.entity.FriendsterWebsiteEntity;
import com.xtalk.server.xtalkserver.entity.QFriendsterWebsiteEntity;
import com.xtalk.server.xtalkserver.repository.FriendsterWebsiteRepository;
import com.xtalk.server.xtalkserver.security.SecurityAuditor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

/**
 * @author xin.z
 * @date 2021/1/20 5:05 下午
 */
@Service
public class WebsiteServiceImpl implements WebsiteService {

    @Autowired
    FriendsterWebsiteRepository friendsterWebsiteRepository;

    @Autowired
    EntityManager entityManager;

    @Autowired
    SecurityAuditor securityAuditor;

    @Override
    public List<FriendsterWebsiteEntity> get(FriendsterWebsiteEntity entity) {
        String name = entity.getName();
        String url = entity.getUrl();
        String title = entity.getTitle();
        QFriendsterWebsiteEntity friendsterWebsiteEntity = QFriendsterWebsiteEntity.friendsterWebsiteEntity;
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        JPAQuery<FriendsterWebsiteEntity> query = jpaQueryFactory.selectFrom(friendsterWebsiteEntity);
        if (StringUtils.isNotBlank(name)) {
            query.where(friendsterWebsiteEntity.name.like("%" + name + "%"));
        }
        if (StringUtils.isNotBlank(url)) {
            query.where(friendsterWebsiteEntity.url.like("%" + url + "%"));
        }
        if (StringUtils.isNotBlank(title)) {
            query.where(friendsterWebsiteEntity.title.like("%" + title + "%"));
        }
        // query.where(friendsterWebsiteEntity.isDeleted.eq(0));
        OrderSpecifier<Long> orderBy = friendsterWebsiteEntity.id.asc();
        query.orderBy(orderBy);
        return query.fetch();
    }

    @Override
    public FriendsterWebsiteEntity del(long id) {
        Long currentAdminId = (Long) securityAuditor.getCurrentAuditor().orElseGet(() -> {
            throw new RuntimeException("no login");
        });
        FriendsterWebsiteEntity entity = friendsterWebsiteRepository
                .findById(id)
                .orElseGet(() -> {
                    throw new IllegalArgumentException("id有误");
                });
        entity.setIsDeleted(1);
        entity.setDeletedAt(new Date());
        entity.setUpdateId(currentAdminId);
        return friendsterWebsiteRepository.save(entity);
    }

    @Override
    public FriendsterWebsiteEntity update(FriendsterWebsiteEntity entity) {
        Long currentAdminId = (Long) securityAuditor.getCurrentAuditor().orElseGet(() -> {
            throw new RuntimeException("no login");
        });
        FriendsterWebsiteEntity update = friendsterWebsiteRepository
                .findById(entity.getId())
                .orElseGet(() -> {
                    throw new IllegalArgumentException("id有误");
                });
        update.setTitle(entity.getTitle());
        update.setName(entity.getName());
        update.setIcon(entity.getIcon());
        update.setUrl(entity.getUrl());
        update.setIsDeleted(entity.getIsDeleted());
        //todo
        update.setUpdateId(currentAdminId);
        update.setUpdatedAt(new Date());
        return friendsterWebsiteRepository.save(update);
    }

    @Override
    public FriendsterWebsiteEntity create(FriendsterWebsiteEntity entity) {
        checkParam(entity);
        Long currentAdminId = (Long) securityAuditor.getCurrentAuditor().orElseGet(() -> {
            throw new RuntimeException("no login");
        });
        entity.setCreatorId(currentAdminId);
        entity.setUpdateId(currentAdminId);
        entity.setTimestamp(System.currentTimeMillis());
        Date date = new Date();
        entity.setIsDeleted(0);
        entity.setCreatedAt(date);
        entity.setUpdatedAt(date);
        entity.setDeletedAt(date);
        return friendsterWebsiteRepository.save(entity);
    }

    private void checkParam(FriendsterWebsiteEntity entity) {
        Assert.isTrue(StringUtils.isNotBlank(entity.getName()),
                "网站名称不能为空");
        Assert.isTrue(StringUtils.isNotBlank(entity.getTitle()),
                "网站标题不能为空");
        Assert.isTrue(StringUtils.isNotBlank(entity.getUrl()),
                "网站网址不能为空");
        Assert.isTrue(StringUtils.isNotBlank(entity.getIcon()),
                "网站图标不能为空");
    }
}
