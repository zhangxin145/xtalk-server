package com.xtalk.server.xtalkserver.repository;

import com.xtalk.server.xtalkserver.entity.AdminRoleEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author xin.z
 * @date 2021/1/22 5:08 下午
 */
@Repository
public interface AdminRoleRepository
        extends BaseRepository<AdminRoleEntity, Long> {
    Optional<List<AdminRoleEntity>> findByRoleIdInAndDeleted(List<Long> ids, int i);


    //Optional<List<AdminRoleEntity>> findByRoleIdAndDeleted();

    Optional<List<AdminRoleEntity>> findByAdminIdAndDeleted(Long adminId,int del);

    Optional<List<AdminRoleEntity>> findByRoleIdAndDeleted(Long id, int i);
}
