package com.xtalk.server.xtalkserver.repository;

import com.xtalk.server.xtalkserver.entity.VipEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author xin.z
 * @date 2021/1/28 11:08 下午
 */
@Repository
public interface VipRepository
        extends BaseRepository<VipEntity, Long> {
    Optional<List<VipEntity>> findByDeleted(int i);
}
