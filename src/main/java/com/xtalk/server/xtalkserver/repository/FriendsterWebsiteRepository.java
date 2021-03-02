package com.xtalk.server.xtalkserver.repository;

import com.xtalk.server.xtalkserver.entity.FriendsterWebsiteEntity;
import org.springframework.stereotype.Repository;

/**
 * @author xin.z
 * @date 2021/1/19 10:56 下午
 */
@Repository
public interface FriendsterWebsiteRepository
        extends BaseRepository<FriendsterWebsiteEntity,Long>{
    //Optional<FriendsterWebsiteEntity> findByIdAndIsDeleted(Long id, int i);
}
