package com.xtalk.server.xtalkserver.repository;

import com.xtalk.server.xtalkserver.entity.PermissionEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author xin.z
 * @date 2021/1/21 9:00 下午
 */
@Repository
public interface PermissionRepository
        extends BaseRepository<PermissionEntity, Long> {
    Optional<List<PermissionEntity>> findByCodeAndDeleted(String code, int i);

    Optional<PermissionEntity> findByIdAndDeleted(Long id,int del);

    Optional<List<PermissionEntity>> findByDeleted(int del);

    Optional<List<PermissionEntity>> findByPidAndDeleted(Long parentId, int del);

    Optional<List<PermissionEntity>> findByIdInAndDeleted(List<Long> permissionIds, int i);
}
