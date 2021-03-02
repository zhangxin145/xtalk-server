package com.xtalk.server.xtalkserver.repository;

import com.xtalk.server.xtalkserver.entity.ChatroomsEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author xin.z
 * @date 2021/1/31 1:42 下午
 */
@Repository
public interface ChatRoomRepository
        extends BaseRepository<ChatroomsEntity, Long> {
    Optional<ChatroomsEntity> findFirstByChatroomName(String chatroomName);

    Optional<List<ChatroomsEntity>> findByChatroomNameLikeAndDeletedAt(String s, int i);
}
