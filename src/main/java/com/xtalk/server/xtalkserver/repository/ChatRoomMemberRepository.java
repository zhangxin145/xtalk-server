package com.xtalk.server.xtalkserver.repository;

import com.xtalk.server.xtalkserver.entity.ChatroomMembersEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author xin.z
 * @date 2021/1/31 1:43 下午
 */
@Repository
public interface ChatRoomMemberRepository
        extends BaseRepository<ChatroomMembersEntity, Long> {
    Optional<List<ChatroomMembersEntity>> findByChatroomIdAndChatroomRoleInAndIsDeleted(Long id, List<Integer> roles, int i);

    Optional<List<ChatroomMembersEntity>> findByChatroomIdAndMemberIdInAndIsDeletedAndChatroomRole(Long chatRoomId, List<Long> userIds, int i, long l);

    Optional<List<ChatroomMembersEntity>> findByMemberIdAndChatroomRoleAndIsDeleted(Long ownerId, Long i, int i1);

    Optional<List<ChatroomMembersEntity>> findByChatroomIdInAndIsDeletedAndChatroomRole(List<Long> chatRoomIds, int i, Long i1);

    Long countByChatroomIdAndIsDeletedAndChatroomRole(Long roomId, int i, Long i1);

    Optional<List<ChatroomMembersEntity>> findByChatroomIdInAndIsDeleted(List<Long> roomIds, int i);

    Optional<List<ChatroomMembersEntity>> findByMemberIdInAndIsDeleted(List<Long> userIds, int i);
}
