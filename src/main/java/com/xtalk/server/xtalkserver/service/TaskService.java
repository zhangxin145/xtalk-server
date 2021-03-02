package com.xtalk.server.xtalkserver.service;

import com.xtalk.server.xtalkserver.entity.TaskEntity;
import com.xtalk.server.xtalkserver.model.TaskQueryVo;
import org.springframework.data.domain.Page;

/**
 * @author xin.z
 * @date 2021/2/27 11:18 上午
 */
public interface TaskService {
    TaskEntity add(TaskEntity entity);

    TaskEntity edit(TaskEntity entity);

    TaskEntity del(Long id);

    Page<TaskEntity> getResultPage(TaskQueryVo queryVo);
}
