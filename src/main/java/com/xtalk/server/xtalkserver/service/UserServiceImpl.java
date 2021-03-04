package com.xtalk.server.xtalkserver.service;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.xtalk.server.xtalkserver.entity.*;
import com.xtalk.server.xtalkserver.model.UserModel;
import com.xtalk.server.xtalkserver.model.UserQueryVo;
import com.xtalk.server.xtalkserver.repository.ChatRoomMemberRepository;
import com.xtalk.server.xtalkserver.repository.UserRepository;
import com.xtalk.server.xtalkserver.security.SecurityAuditor;
import com.xtalk.server.xtalkserver.utils.DateUtils;
import com.xtalk.server.xtalkserver.utils.MD5Utils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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

    @Autowired
    ChatRoomMemberRepository chatRoomMemberRepository;

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

        if (StringUtils.isNoneBlank(queryVo.getCreateTimeBegin())) {
            String openTimeBegin = queryVo.getCreateTimeBegin();
            Date localDateTime = DateUtils.parseDate(openTimeBegin, "yyyy-MM-dd HH:mm:ss");
            query.where(usersEntity.createdAt.goe(localDateTime));
        }
        if (StringUtils.isNoneBlank(queryVo.getCreateTimeEnd())) {
            String openTimeEnd = queryVo.getCreateTimeEnd();
            Date localDateTime = DateUtils.parseDate(openTimeEnd, "yyyy-MM-dd HH:mm:ss");
            query.where(usersEntity.createdAt.loe(localDateTime));
        }
        Long chatRoomId = queryVo.getChatRoomId();
        if (chatRoomId != null && chatRoomId > 0) {
            Optional<List<ChatroomMembersEntity>> membersEntities = chatRoomMemberRepository.findByChatroomIdInAndIsDeletedAndChatroomRole(Arrays.asList(chatRoomId), 0, 1L);
            if (membersEntities.isPresent()) {
                List<Long> ids = membersEntities.get().stream().map(ChatroomMembersEntity::getMemberId).collect(Collectors.toList());
                if (ids.size() > 0) {
                    query.where(usersEntity.id.in(ids));
                }
            }
        }
        //query.where(usersEntity.type.eq(0));
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
            throw new IllegalArgumentException("ids err");
        }

        users.stream().forEach(a -> {
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
            throw new IllegalArgumentException("ids err");
        }

        users.stream().forEach(a -> {
            a.setLockStatus(lockStatus);
            a.setUpdatedAt(new Date());
        });
        List<UsersEntity> usersEntities = userRepository.saveAll(users);
        return Optional.ofNullable(usersEntities);

    }

    @Override
    public UsersEntity add(UserModel userModel) {
        checkParam(userModel);
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.append(userModel);
        usersEntity.setCreatedAt(new Date());
        usersEntity.setType(0);
        usersEntity.setFlag(0L);
        String account = userModel.getAccount();
        String password = "123456";
        usersEntity.setLockStatus(0);
        usersEntity.setAccount(account);
        int salt = (int) (Math.random() * 9000) + 1000;
        String hashStr = MD5Utils.hash(password, salt);
        usersEntity.setPasswordSalt(salt + "");
        usersEntity.setPasswordHash(hashStr);
        // 默认值
        addDefaultValue(usersEntity);
        UsersEntity result = userRepository.save(usersEntity);
        return result;
    }


    private void addDefaultValue(UsersEntity usersEntity) {
        usersEntity.setRongCloudToken("");
        usersEntity.setGender("");
        usersEntity.setStAccount("");
        usersEntity.setPhone("");
        usersEntity.setPhoneVerify(1L);
        usersEntity.setStSearchVerify(1L);
        usersEntity.setFriVerify(1L);
        usersEntity.setGroupVerify(1L);
        usersEntity.setPokeStatus(1L);
        usersEntity.setGroupCount(0L);
        usersEntity.setTimestamp(0L);
        usersEntity.setUpdatedAt(new Date());
        usersEntity.setCreatedAt(new Date());
    }

    private void checkParam(UserModel userModel) {
        String account = userModel.getAccount();
        String check = "^(\\d+[A-Za-z]+[A-Za-z0-9]*)|([A-Za-z]+\\d+[A-Za-z0-9]*)$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(account);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("账户格式有误，请重新输入");
        }
        Optional<UsersEntity> entity = userRepository.findFirstByAccount(account);
        if (entity.isPresent()) {
            Long id = userModel.getId();
            if (id == null || id <= 0) {
                throw new IllegalArgumentException("账户已存在");
            }
        }

        String nickname = userModel.getNickname();
        Assert.isTrue(StringUtils.isNotBlank(nickname), "昵称不能为空");
        String url = userModel.getPortraitUri();
        Assert.isTrue(StringUtils.isNotBlank(url), "头像不能为空");

    }

    @Override
    public UsersEntity update(UserModel userModel) {
        checkParam(userModel);
        Long id = userModel.getId();
        if (id == null || id < 1) {
            throw new IllegalArgumentException("id 不能为空");
        }
        UsersEntity usersEntity = userRepository.findById(userModel.getId()).orElseGet(() -> {
            throw new IllegalArgumentException("id 有误");
        });
        usersEntity.append(userModel);
        usersEntity.setUpdatedAt(new Date());
        UsersEntity result = userRepository.save(usersEntity);
        return result;
    }
}
