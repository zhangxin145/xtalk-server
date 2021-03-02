package com.xtalk.server.xtalkserver.controller;

import com.xtalk.server.xtalkserver.entity.UsersEntity;
import com.xtalk.server.xtalkserver.model.Response;
import com.xtalk.server.xtalkserver.model.RobotChangeStatusModel;
import com.xtalk.server.xtalkserver.model.RobotQueryVo;
import com.xtalk.server.xtalkserver.model.RobotVo;
import com.xtalk.server.xtalkserver.service.RobotService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xin.z
 * @date 2021/2/27 8:28 下午
 */
@Api("机器人相关接口")
@RestController
@RequestMapping(value = "/v1/robot")
@Slf4j
public class RobotController {

    @Autowired
    RobotService robotService;

    @ApiOperation(value = "添加机器人", notes = "添加机器人", produces = "application/json")
    @PostMapping(value = "/add")
    public Response<UsersEntity> add(
            @RequestBody RobotVo robotVo
    ) {
        try {
            return Response.ok(robotService.add(robotVo));
        } catch (Exception e) {
            return Response.exception(e);
        }
    }

    @ApiOperation(value = "编辑机器人", notes = "编辑机器人", produces = "application/json")
    @PostMapping(value = "/edit")
    public Response<Object> edit(
            @RequestBody RobotVo robotVo
    ) {
        try {
            return Response.ok(robotService.edit(robotVo));
        } catch (Exception e) {
            return Response.exception(e);
        }
    }



    @ApiOperation(value = "启/禁用", notes = "启/禁用", produces = "application/json")
    @PostMapping(value = "/changeStatus")
    public Response<List<UsersEntity>> changeStatus(
            @RequestBody RobotChangeStatusModel model
    ) {
        try {
            return Response.ok(robotService.changeStatus(model));
        } catch (Exception e) {
            return Response.exception(e);
        }
    }


    @ApiOperation(value = "分页获取机器人信息", notes = "分页获取机器人信息", produces = "application/json")
    @PostMapping(value = "/getResult")
    public Response<Object> getResultPage(
            @RequestBody RobotQueryVo queryVo
    ) {
        try {
            return Response.ok(robotService.getResultPage(queryVo));
        } catch (Exception e) {
            return Response.exception(e);
        }
    }

}
