package com.xtalk.server.xtalkserver.controller;

import com.xtalk.server.xtalkserver.entity.UsersEntity;
import com.xtalk.server.xtalkserver.model.Response;
import com.xtalk.server.xtalkserver.model.UserQueryVo;
import com.xtalk.server.xtalkserver.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xin.z
 * @date 2021/1/26 9:51 下午
 */
@Api("前台用户相关接口")
@RestController
@RequestMapping(value = "/v1/user")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation(value = "分页获取前台用户信息", notes = "分页获取前台用户信息", produces = "application/json")
    @PostMapping(value = "/getResultPage")
    public Response<Page<UsersEntity>> getResultPage(
            @RequestBody UserQueryVo queryVo
    ) {
        try {
            return Response.ok(userService.getResultPage(queryVo));
        } catch (Exception e) {
            return Response.exception(e);
        }
    }


    @ApiOperation(value = "设置等级---支持批量 ", notes = "设置等级---支持批量 ", produces = "application/json")
    @PostMapping(value = "/updateUserVip")
    public Response<List<UsersEntity>> updateUserVip(
            @RequestParam List<Long> ids,
            @RequestParam Long vipId
    ) {
        try {
            return Response.ok(userService.updateUserVip(ids, vipId));
        } catch (Exception e) {
            return Response.exception(e);
        }
    }

    @ApiOperation(value = "锁定---支持批量 ", notes = "设置等级---支持批量 ", produces = "application/json")
    @PostMapping(value = "/lock")
    public Response<List<UsersEntity>> lock(
            @RequestParam List<Long> ids,
            @RequestParam Integer lockStatus
    ) {
        try {
            return Response.ok(userService.lock(ids, lockStatus));
        } catch (Exception e) {
            return Response.exception(e);
        }
    }

/*
    @ApiOperation(value = "添加前台用户", notes = "添加前台用户", produces = "application/json")
    @PostMapping(value = "/add")
    public Response<List<UsersEntity>> updateUserVip(
            @RequestBody UsersEntity usersEntity
    ) {
        try {
            return Response.ok(userService.add(usersEntity));
        } catch (Exception e) {
            return Response.exception(e);
        }
    }

    @ApiOperation(value = "编辑前台用户", notes = "编辑前台用户", produces = "application/json")
    @PostMapping(value = "/update")
    public Response<List<UsersEntity>> updateUserVip(
            @RequestBody UsersEntity usersEntity
    ) {
        try {
            return Response.ok(userService.update(usersEntity));
        } catch (Exception e) {
            return Response.exception(e);
        }
    }*/
}
