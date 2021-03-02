package com.xtalk.server.xtalkserver.repository;

import com.xtalk.server.xtalkserver.entity.TaskEntity;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author xin.z
 * @date 2021/2/27 11:34 上午
 */
@Repository
@Order(1)
public interface TaskRepository extends BaseRepository<TaskEntity, Long> {

    List<TaskEntity> findByDeleted(int i);

    Optional<List<TaskEntity>> findByTaskName(String taskName);
}
