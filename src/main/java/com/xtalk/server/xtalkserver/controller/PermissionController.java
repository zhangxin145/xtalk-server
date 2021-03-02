package com.xtalk.server.xtalkserver.controller;

import com.xtalk.server.xtalkserver.entity.PermissionEntity;
import com.xtalk.server.xtalkserver.model.PermissionNodeModel;
import com.xtalk.server.xtalkserver.model.Response;
import com.xtalk.server.xtalkserver.service.PermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xin.z
 * @date 2021/1/21 8:20 下午
 */
@Api("菜单相关api")
@RestController
@RequestMapping(value = "/v1/permission")
@Slf4j
public class PermissionController {
    @Autowired
    PermissionService permissionService;

    @ApiOperation(value="添加菜单", notes="添加菜单", produces="application/json")
    @PostMapping(value = "/add")
    public Response<PermissionEntity> add(
            @RequestBody PermissionEntity entity
    ) {
        try {
            return Response.ok(permissionService.add(entity));
        } catch (Exception e) {
            return Response.exception(e);
        }
    }

    @ApiOperation(value="修改菜单", notes="修改菜单", produces="application/json")
    @PostMapping(value = "/update")
    public Response<PermissionEntity> update(
            @RequestBody PermissionEntity entity
    ) {
        try {
            return Response.ok(permissionService.update(entity));
        } catch (Exception e) {
            return Response.exception(e);
        }
    }

    @ApiOperation(value="删除菜单", notes="删除菜单", produces="application/json")
    @GetMapping(value = "/del")
    public Response<PermissionEntity> del(
            @RequestParam Long id
    ) {
        try {
            return Response.ok(permissionService.del(id));
        } catch (Exception e) {
            return Response.exception(e);
        }
    }


    @ApiOperation(value="根据id获取菜单详情", notes="根据id获取菜单详情", produces="application/json")
    @GetMapping(value = "/getById")
    public Response<PermissionEntity> getById(
            @RequestParam Long id
    ) {
        try {
            return Response.ok(permissionService.getById(id));
        } catch (Exception e) {
            return Response.exception(e);
        }
    }


    @ApiOperation(value="获取全部菜单信息", notes="获取全部菜单信息", produces="application/json")
    @GetMapping(value = "/getResult")
    public Response<List<PermissionNodeModel>> getResult() {
        try {
            return Response.ok(permissionService.getResult());
        } catch (Exception e) {
            return Response.exception(e);
        }
    }
}
