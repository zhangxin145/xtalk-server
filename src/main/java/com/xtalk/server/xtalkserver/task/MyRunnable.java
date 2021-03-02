package com.xtalk.server.xtalkserver.task;

import com.xtalk.server.xtalkserver.entity.TaskEntity;

import java.util.Date;

/**
 * @author xin.z
 * @date 2021/2/27 4:03 下午
 */
public class MyRunnable implements Runnable {

    private TaskEntity taskEntity;

    @Override
    public void run() {
        System.out.println("DynamicTask.MyRunnable.run()，" + new Date());
        //System.out.println(taskEntity.toString() + "========");
    }

    public TaskEntity getTaskEntity() {
        return taskEntity;
    }

    public void setTaskEntity(TaskEntity taskEntity) {
        this.taskEntity = taskEntity;
    }
}
