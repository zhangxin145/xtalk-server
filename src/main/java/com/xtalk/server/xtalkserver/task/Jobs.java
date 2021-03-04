package com.xtalk.server.xtalkserver.task;

import com.xtalk.server.xtalkserver.entity.TaskEntity;
import com.xtalk.server.xtalkserver.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

/**
 * @author xin.z
 * @date 2021/2/27 4:15 下午
 */
@Component
@Slf4j

public class Jobs {

    private final ConcurrentHashMap<Long, ScheduledFuture> jobsMap = new ConcurrentHashMap<>(8);

    @Autowired
    TaskRepository taskRepository;
    // public final static List<TaskEntity> jobs = new ArrayList<>();

    private final ThreadPoolTaskScheduler threadPoolTaskScheduler;

    public Jobs(ThreadPoolTaskScheduler threadPoolTaskScheduler) {
        this.threadPoolTaskScheduler = threadPoolTaskScheduler;
    }

   /* public Jobs(ThreadPoolTaskScheduler threadPoolTaskScheduler) {
        this.threadPoolTaskScheduler = threadPoolTaskScheduler;
    }*/

    public void start(TaskEntity taskEntity) {
        if (jobsMap.containsKey(taskEntity.getId())) {
            log.debug("job:{} is running", taskEntity.getId());
            // running...
            return;
        }

        executeJob(taskEntity);
    }


    public void stop(TaskEntity taskEntity) {
        ScheduledFuture scheduledFuture = jobsMap.get(taskEntity.getId());
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
            log.debug("job cancel:{}", scheduledFuture);
        } else
            log.debug("jobId:{} is not running ", taskEntity.getId());
    }

    @PostConstruct
    private void init() {

        List<TaskEntity> jobs = taskRepository.findByDeleted(0);

        if (CollectionUtils.isNotEmpty(jobs)) {
            jobs.forEach(this::executeJob);
        }
    }

    private void executeJob(TaskEntity job) {
        MyRunnable runnable = new MyRunnable();
        runnable.setTaskEntity(job);
        if (Objects.nonNull(runnable)) {
            ScheduledFuture<?> schedule = threadPoolTaskScheduler.schedule(runnable, new CronTrigger(job.getCronTask()));
            jobsMap.put(job.getId(), schedule);
            log.debug("job:{} is running...", job);
        } else {
            log.error("任务调度失败 job Class is null");
        }
    }
}
