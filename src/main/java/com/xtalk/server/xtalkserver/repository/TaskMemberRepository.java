package com.xtalk.server.xtalkserver.repository;

import com.xtalk.server.xtalkserver.entity.TaskMembersEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author xin.z
 * @date 2021/2/28 11:47 上午
 */
@Repository
public interface TaskMemberRepository
        extends BaseRepository<TaskMembersEntity, Long> {
    Optional<List<TaskMembersEntity>> findByTaskIdAndDeleted(Long id, int i);

    Optional<List<TaskMembersEntity>> findByMemberIdAndDeleted(Long id, int i);
}
