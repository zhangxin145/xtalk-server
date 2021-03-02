package com.xtalk.server.xtalkserver.repository;

import com.xtalk.server.xtalkserver.entity.UserPermissionEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author xin.z
 * @date 2021/1/27 11:08 下午
 */
@Repository
public interface UserPermissionRepository
        extends BaseRepository<UserPermissionEntity, Long> {
    Optional<List<UserPermissionEntity>> findByDeleted(int i);

    Optional<UserPermissionEntity> findByNameAndDeleted(String name, int i);
}
