package com.xtalk.server.xtalkserver.controller;

import com.xtalk.server.xtalkserver.entity.TaskEntity;
import com.xtalk.server.xtalkserver.model.Response;
import com.xtalk.server.xtalkserver.model.TaskQueryVo;
import com.xtalk.server.xtalkserver.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @author xin.z
 * @date 2021/2/23 5:52 下午
 */
@Api("任务相关接口")
@RestController
@RequestMapping(value = "/v1/task")
@Slf4j
public class TaskController {

    @Autowired
    TaskService taskService;

    @ApiOperation(value = "添加任务", notes = "添加任务", produces = "application/json")
    @PostMapping(value = "/add")
    public Response<TaskEntity> add(
            @RequestBody TaskEntity entity
    ) {
        try {
            return Response.ok(taskService.add(entity));
        } catch (Exception e) {
            return Response.exception(e);
        }
    }


    @ApiOperation(value = "编辑任务", notes = "编辑任务", produces = "application/json")
    @PostMapping(value = "/edit")
    public Response<TaskEntity> edit(
            @RequestBody TaskEntity entity
    ) {
        try {
            return Response.ok(taskService.edit(entity));
        } catch (Exception e) {
            return Response.exception(e);
        }
    }


    @ApiOperation(value = "删除任务", notes = "删除任务", produces = "application/json")
    @GetMapping(value = "/del")
    public Response<TaskEntity> del(
            @RequestParam Long id
    ) {
        try {
            return Response.ok(taskService.del(id));
        } catch (Exception e) {
            return Response.exception(e);
        }
    }


    @ApiOperation(value = "分页查询任务", notes = "分页查询任务", produces = "application/json")
    @PostMapping(value = "/query")
    public Response<Page<TaskEntity>> getResultPage(
            @RequestBody TaskQueryVo queryVo
    ) {
        try {
            return Response.ok(taskService.getResultPage(queryVo));
        } catch (Exception e) {
            return Response.exception(e);
        }
    }

}
