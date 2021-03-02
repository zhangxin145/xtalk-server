package com.xtalk.server.xtalkserver.controller;

import com.xtalk.server.xtalkserver.entity.AdminEntity;
import com.xtalk.server.xtalkserver.model.*;
import com.xtalk.server.xtalkserver.service.AdminUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xin.z
 * @date 2021/1/21 8:18 下午
 */
@Api("后台用户相关")
@RestController
@RequestMapping(value = "/v1/admin")
@Slf4j
public class AdminUserController {

    @Autowired
    AdminUserService adminUserService;

    @ApiOperation(value = "添加用户", notes = "添加用户", produces = "application/json")
        @PostMapping(value = "/add")
        public Response<AdminEntity> add(
                @RequestBody AdminVo adminVo
    ) {
            try {
                return Response.ok(adminUserService.add(adminVo));
            } catch (Exception e) {
                return Response.exception(e);
            }
    }

    @ApiOperation(value = "修改用户", notes = "修改用户", produces = "application/json")
    @PostMapping(value = "/update")
    public Response<AdminEntity> update(
            @RequestBody AdminVo adminVo
    ) {
        try {
            return Response.ok(adminUserService.update(adminVo));
        } catch (Exception e) {
            return Response.exception(e);
        }
    }

    @ApiOperation(value = "删除用户", notes = "删除用户", produces = "application/json")
    @GetMapping(value = "/del")
    public Response<AdminEntity> del(
            @RequestParam Long id
    ) {
        try {
            return Response.ok(adminUserService.del(id));
        } catch (Exception e) {
            return Response.exception(e);
        }
    }

    @ApiOperation(value = "启/禁用", notes = "启/禁用", produces = "application/json")
    @PostMapping(value = "/changeStatus")
    public Response<List<AdminEntity>> changeStatus(
            @RequestBody AdminChangeStatusModel model
    ) {
        try {
            return Response.ok(adminUserService.changeStatus(model));
        } catch (Exception e) {
            return Response.exception(e);
        }
    }


    @ApiOperation(value = "根据id获取用户详情", notes = "根据id获取角色详情", produces = "application/json")
    @GetMapping(value = "/getById")
    public Response<AdminModel> getById(
            @RequestParam Long id
    ) {
        try {
            return Response.ok(adminUserService.getById(id));
        } catch (Exception e) {
            return Response.exception(e);
        }
    }

    @ApiOperation(value = "设置/修改密码", notes = "设置/修改密码", produces = "application/json")
    @GetMapping(value = "/setPass")
    public Response<AdminEntity> setPassById(
            @RequestParam Long id,
            @RequestParam String pass,
            @RequestParam String rePass
    ) {
        try {
            return Response.ok(adminUserService.setPassById(id, pass, rePass));
        } catch (Exception e) {
            return Response.exception(e);
        }
    }

    @ApiOperation(value = "分页获取用户信息", notes = "分页获取用户信息", produces = "application/json")
    @PostMapping(value = "/getResult")
    public Response<Page<List<AdminModel>>> getResultPage(
            @RequestBody AdminQueryVo queryVo
    ) {
        try {
            return Response.ok(adminUserService.getResultPage(queryVo));
        } catch (Exception e) {
            return Response.exception(e);
        }
    }

    @ApiOperation(value = "根据用户id查询对应权限", notes = "根据用户id查询对应权限", produces = "application/json")
    @GetMapping(value = "/getPermissionByAdminId")
    public Response<List<PermissionNodeModel>> getPermissionByAdminId(
            @RequestParam(value = "adminId", required = false) Long adminId
    ) {
        try {
            return Response.ok(adminUserService.getPermissionByAdminId(adminId));
        } catch (Exception e) {
            return Response.exception(e);
        }
    }
}
