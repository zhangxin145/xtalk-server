package com.xtalk.server.xtalkserver.service;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.xtalk.server.xtalkserver.entity.QTaskEntity;
import com.xtalk.server.xtalkserver.entity.TaskEntity;
import com.xtalk.server.xtalkserver.model.TaskQueryVo;
import com.xtalk.server.xtalkserver.repository.TaskRepository;
import com.xtalk.server.xtalkserver.security.SecurityAuditor;
import com.xtalk.server.xtalkserver.task.Jobs;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.support.CronSequenceGenerator;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author xin.z
 * @date 2021/2/27 11:19 上午
 */
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    SecurityAuditor securityAuditor;

    @Autowired
    EntityManager entityManager;

    @Autowired
    Jobs jobs;

    @Override
    public TaskEntity add(TaskEntity entity) {
        checkParam(entity);
        Long currentAdminId = (Long) securityAuditor.getCurrentAuditor().orElseGet(() -> {
            throw new RuntimeException("no login");
        });
        String taskName = entity.getTaskName();
        Optional<List<TaskEntity>> tasks = taskRepository.findByTaskName(taskName);
        if (!tasks.isPresent()) {
            List<TaskEntity> taskEntities = tasks.get();
            if (taskEntities != null && taskEntities.size() > 0) {
                throw new IllegalArgumentException("任务名称已存在");
            }
        }
        String cronTask = entity.getCronTask();
        if (!CronSequenceGenerator.isValidExpression(cronTask)) {
            throw new IllegalArgumentException("corn 表达式有误");
        }
        entity.setCid(currentAdminId);
        entity.setCtime(new Date());
        entity.setDeleted(0);
        TaskEntity taskEntity = taskRepository.save(entity);
        jobs.start(taskEntity);
        return taskEntity;
    }

    private void checkParam(TaskEntity entity) {
        Assert.isTrue(StringUtils.isNotBlank(entity.getTaskName()), "名称不能为空");
        Assert.isTrue(StringUtils.isNotBlank(entity.getCronTask()), "cron 表达式不能为空");
        Assert.isTrue(StringUtils.isNotBlank(entity.getContent()), "内容不能为空");

    }

    @Override
    public TaskEntity edit(TaskEntity entity) {
        checkParam(entity);
        Long id = entity.getId();
        Assert.isTrue(id != null && id > 0, "id 不能为空");
        TaskEntity taskEntity = taskRepository.findById(id).orElseGet(() -> {
            throw new IllegalArgumentException("id 有误");
        });
        jobs.stop(taskEntity);
        taskEntity.append(entity);
        taskRepository.save(taskEntity);
        jobs.start(taskEntity);
        return taskEntity;
    }

    @Override
    public TaskEntity del(Long id) {
        Assert.isTrue(id != null && id > 0, "id 不能为空");
        TaskEntity taskEntity = taskRepository.findById(id).orElseGet(() -> {
            throw new IllegalArgumentException("id 有误");
        });
        // todo 已绑定在机器人身上执行的任务不可以进行删除，需要解绑之后才能删除
        jobs.stop(taskEntity);
        taskEntity.setDeleted(1);
        return taskRepository.save(taskEntity);
    }

    @Override
    public Page<TaskEntity> getResultPage(TaskQueryVo queryVo) {
        int pageNumber = queryVo.getPageNumber() == null || queryVo.getPageNumber() < 1 ? 1 : queryVo.getPageNumber();
        int pageSize = queryVo.getPageSize() == null || queryVo.getPageSize() < 1 ? 20 : queryVo.getPageSize();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QTaskEntity taskEntity = QTaskEntity.taskEntity;
        JPAQuery<TaskEntity> query = jpaQueryFactory.selectFrom(taskEntity);
        String name = queryVo.getTaskName();
        if (StringUtils.isNotBlank(name)) {
            query.where(taskEntity.taskName.like("%" + name + "%"));
        }
        query.where(taskEntity.deleted.eq(0));
        long count = query.fetchCount();
        OrderSpecifier<Long> orderBy = taskEntity.id.desc();
        query.orderBy(orderBy)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());
        List<TaskEntity> result = query.fetch();
        return new PageImpl(result, pageable, count);
    }
}
