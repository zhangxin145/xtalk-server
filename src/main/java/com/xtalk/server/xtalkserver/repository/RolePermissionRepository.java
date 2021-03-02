package com.xtalk.server.xtalkserver.repository;

import com.xtalk.server.xtalkserver.entity.RolePermissionEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author xin.z
 * @date 2021/1/22 5:08 下午
 */
@Repository
public interface RolePermissionRepository
        extends BaseRepository<RolePermissionEntity, Long> {
    Optional<List<RolePermissionEntity>> findByRoleIdAndDeleted(Long roleId, int del);

    Optional<List<RolePermissionEntity>> findByRoleIdInAndDeleted(List<Long> ids, int i);
}
