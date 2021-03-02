package com.xtalk.server.xtalkserver.config;

/**
 *
 */
/*
@Configuration
public class ScheduledConfig implements SchedulingConfigurer {

    @Autowired
    private ApplicationContext context;
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        for (TaskEntity taskEntity : taskRepository.findAll()) {
            Class<?> clazz;
            //Object task;
            DynamicPrintTask task = new DynamicPrintTask();
            System.out.println("wc--------------");

            try {
                // clazz = Class.forName("com.xtalk.server.xtalkserver.task.DynamicPrintTask");
                //task = context.getBean(clazz);
            } catch (BeansException e) {
                throw new IllegalArgumentException(taskEntity.getCronTask() + "未纳入到spring管理", e);
            }
            Assert.isAssignable(ScheduledOfTask.class, task.getClass(), "定时任务类必须实现ScheduledOfTask接口");
            // 可以通过改变数据库数据进而实现动态改变执行周期
            task.setContent(taskEntity.getContent());
            taskRegistrar.addTriggerTask(((Runnable) task),
                    triggerContext -> {
                        String cronExpression = taskEntity.getCronTask();
                        return new CronTrigger(cronExpression).nextExecutionTime(triggerContext);
                    }
            );
        }

    }

    @Bean
    public Executor taskExecutor() {
        return Executors.newScheduledThreadPool(10);
    }

}*/
