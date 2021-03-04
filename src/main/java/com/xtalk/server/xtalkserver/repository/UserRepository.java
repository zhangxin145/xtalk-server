package com.xtalk.server.xtalkserver.repository;

import com.xtalk.server.xtalkserver.entity.UsersEntity;

import java.util.List;
import java.util.Optional;

/**
 * @author xin.z
 * @date 2021/1/26 9:56 下午
 */
public interface UserRepository
        extends BaseRepository<UsersEntity, Long> {
    Optional<List<UsersEntity>> findByVipIdIn(List<Long> vipIds);

    Optional<UsersEntity> findFirstByAccount(String account);
}
