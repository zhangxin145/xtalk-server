package com.xtalk.server.xtalkserver.controller;

import com.xtalk.server.xtalkserver.entity.RoleEntity;
import com.xtalk.server.xtalkserver.model.*;
import com.xtalk.server.xtalkserver.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xin.z
 * @date 2021/1/21 8:19 下午
 */
@Api("角色相关api")
@RestController
@RequestMapping(value = "/v1/role")
@Slf4j
public class RoleController {

    @Autowired
    RoleService roleService;

    @ApiOperation(value = "添加角色", notes = "添加角色", produces = "application/json")
    @PostMapping(value = "/add")
    public Response<RoleEntity> add(
            @RequestBody RoleEntity entity
    ) {
        try {
            return Response.ok(roleService.add(entity));
        } catch (Exception e) {
            return Response.exception(e);
        }
    }

    @ApiOperation(value = "修改角色", notes = "修改角色", produces = "application/json")
    @PostMapping(value = "/update")
    public Response<RoleEntity> update(
            @RequestBody RoleEntity entity
    ) {
        try {
            return Response.ok(roleService.update(entity));
        } catch (Exception e) {
            return Response.exception(e);
        }
    }

    @ApiOperation(value = "编辑角色权限", notes = "编辑角色权限", produces = "application/json")
    @PostMapping(value = "/editRole")
    public Response<RoleEntity> editRole(
            @RequestBody RoleEditModel model
    ) {
        try {
            return Response.ok(roleService.editRole(model));
        } catch (Exception e) {
            return Response.exception(e);
        }
    }

    @ApiOperation(value = "删除角色", notes = "删除角色", produces = "application/json")
    @GetMapping(value = "/del")
    public Response<RoleEntity> del(
            @RequestParam Long id
    ) {
        try {
            return Response.ok(roleService.del(id));
        } catch (Exception e) {
            return Response.exception(e);
        }
    }

    @ApiOperation(value = "启/禁用", notes = "启/禁用", produces = "application/json")
    @PostMapping(value = "/changeStatus")
    public Response<List<RoleEntity>> changeStatus(
            @RequestBody RoleChangeStatusModel model
    ) {
        try {
            return Response.ok(roleService.changeStatus(model));
        } catch (Exception e) {
            return Response.exception(e);
        }
    }


    @ApiOperation(value = "根据id获取角色详情", notes = "根据id获取角色详情", produces = "application/json")
    @GetMapping(value = "/getById")
    public Response<RoleModel> getById(
            @RequestParam Long id
    ) {
        try {
            return Response.ok(roleService.getById(id));
        } catch (Exception e) {
            return Response.exception(e);
        }
    }

    @ApiOperation(value = "获取所有角色，在添加用时做选择", notes = "获取所有角色，在添加用时做选择", produces = "application/json")
    @GetMapping(value = "/getAllRole")
    public Response<List<RoleEntity>> getAllRole(
            @RequestParam(value = "status", required = false, defaultValue = "-1") Integer status
    ) {

        try {
            return Response.ok(roleService.getAllRole(status));
        } catch (Exception e) {
            return Response.exception(e);
        }
    }


    @ApiOperation(value = "分页获取角色信息", notes = "分页获取角色信息", produces = "application/json")
    @PostMapping(value = "/getResult")
    public Response<Page<RoleModel>> getResultPage(
            @RequestBody RoleQueryVo queryVo
    ) {
        try {
            return Response.ok(roleService.getResultPage(queryVo));
        } catch (Exception e) {
            return Response.exception(e);
        }
    }
}
