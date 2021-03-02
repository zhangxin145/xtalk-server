package com.xtalk.server.xtalkserver.repository;

import com.xtalk.server.xtalkserver.entity.RoleEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author xin.z
 * @date 2021/1/22 5:07 下午
 */
@Repository
public interface RoleRepository
        extends BaseRepository<RoleEntity, Long> {
    Optional<List<RoleEntity>> findByNameAndDeleted(String name, int i);
}
