package com.xtalk.server.xtalkserver.service;

import com.xtalk.server.xtalkserver.entity.UsersEntity;
import com.xtalk.server.xtalkserver.model.UserQueryVo;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

/**
 * @author xin.z
 * @date 2021/1/26 9:55 下午
 */
public interface UserService {
    Page<UsersEntity> getResultPage(UserQueryVo queryVo);

    Optional<List<UsersEntity>> updateUserVip(List<Long> ids, Long vipId);

    Optional<List<UsersEntity>> lock(List<Long> ids, Integer lockStatus);

    UsersEntity add(UsersEntity usersEntity);

    UsersEntity update(UsersEntity usersEntity);
}
