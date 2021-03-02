package com.xtalk.server.xtalkserver.service;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.xtalk.server.xtalkserver.entity.QUsersEntity;
import com.xtalk.server.xtalkserver.entity.UsersEntity;
import com.xtalk.server.xtalkserver.model.UserQueryVo;
import com.xtalk.server.xtalkserver.repository.UserRepository;
import com.xtalk.server.xtalkserver.security.SecurityAuditor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author xin.z
 * @date 2021/1/26 9:55 下午
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManager entityManager;

    @Autowired
    SecurityAuditor securityAuditor;

    @Override
    public Page<UsersEntity> getResultPage(UserQueryVo queryVo) {
        int pageNumber = queryVo.getPageNumber() == null || queryVo.getPageNumber() < 1 ? 1 : queryVo.getPageNumber();
        int pageSize = queryVo.getPageSize() == null || queryVo.getPageSize() < 1 ? 20 : queryVo.getPageSize();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QUsersEntity usersEntity = QUsersEntity.usersEntity;
        JPAQuery<UsersEntity> query = jpaQueryFactory.selectFrom(usersEntity);
        Long id = queryVo.getId();
        if (id != null && id > 0) {
            query.where(usersEntity.id.eq(id));
        }
        query.where(usersEntity.type.eq(0));
        String nickName = queryVo.getNickName();
        if (StringUtils.isNotBlank(nickName)) {
            query.where(usersEntity.nickname.like("%" + nickName + "%"));
        }
        String phone = queryVo.getPhone();
        if (StringUtils.isNotBlank(phone)) {
            query.where(usersEntity.phone.like("%" + nickName + "%"));
        }
        long count = query.fetchCount();
        OrderSpecifier<Long> orderBy = usersEntity.id.asc();
        query.orderBy(orderBy)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());
        List<UsersEntity> result = query.fetch();


        return new PageImpl(result, pageable, count);
    }

    @Override
    public Optional<List<UsersEntity>> updateUserVip(List<Long> ids, Long vipId) {

        List<UsersEntity> users = userRepository.findAllById(ids);
        if (users == null || users.size() < 1) {
                throw  new IllegalArgumentException("ids err");
        }

        users.stream().forEach(a->{
            a.setVipId(vipId);
            a.setUpdatedAt(new Date());
        });
        List<UsersEntity> usersEntities = userRepository.saveAll(users);
        return Optional.ofNullable(usersEntities);
    }

    @Override
    public Optional<List<UsersEntity>> lock(List<Long> ids, Integer lockStatus) {
        List<UsersEntity> users = userRepository.findAllById(ids);
        if (users == null || users.size() < 1) {
            throw  new IllegalArgumentException("ids err");
        }

        users.stream().forEach(a->{
            a.setLockStatus(lockStatus);
            a.setUpdatedAt(new Date());
        });
        List<UsersEntity> usersEntities = userRepository.saveAll(users);
        return Optional.ofNullable(usersEntities);

    }

    @Override
    public UsersEntity add(UsersEntity usersEntity) {
        usersEntity.setPasswordHash("");
        usersEntity.setPasswordSalt("");
       // usersEntity.s
        return null;
    }

    @Override
    public UsersEntity update(UsersEntity usersEntity) {
        return null;
    }
}
