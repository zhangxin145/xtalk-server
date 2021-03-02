package com.xtalk.server.xtalkserver.repository;

import com.xtalk.server.xtalkserver.entity.AdminEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author xin.z
 * @date 2021/1/22 5:08 下午
 */
@Repository
public interface AdminUserRepository
        extends BaseRepository<AdminEntity, Long> {
    Optional<AdminEntity> findByAccountAndDeleted(String account, int i);
}
