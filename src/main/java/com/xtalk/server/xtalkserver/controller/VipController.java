package com.xtalk.server.xtalkserver.controller;

import com.xtalk.server.xtalkserver.entity.UserPermissionEntity;
import com.xtalk.server.xtalkserver.entity.VipEntity;
import com.xtalk.server.xtalkserver.model.Response;
import com.xtalk.server.xtalkserver.model.VipEditModel;
import com.xtalk.server.xtalkserver.model.VipModel;
import com.xtalk.server.xtalkserver.model.VipQueryVo;
import com.xtalk.server.xtalkserver.service.VipService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xin.z
 * @date 2021/1/28 7:01 下午
 */
@Api("vip相关api")
@RestController
@RequestMapping(value = "/v1/vip")
@Slf4j
public class VipController {

    @Autowired
    VipService vipService;

    @ApiOperation(value = "添加权限（Vip 相关）", notes = "添加权限（Vip 相关", produces = "application/json")
    @PostMapping(value = "/addPermission")
    public Response<UserPermissionEntity> addPermission(
            @RequestBody UserPermissionEntity entity
    ) {
        try {
            return Response.ok(vipService.addPermission(entity));
        } catch (Exception e) {
            return Response.exception(e);
        }
    }

    @ApiOperation(value = "修改权限（Vip 相关）", notes = "修改权限（Vip 相关", produces = "application/json")
    @PostMapping(value = "/updatePermission")
    public Response<UserPermissionEntity> updatePermission(
            @RequestBody UserPermissionEntity entity
    ) {
        try {
            return Response.ok(vipService.updatePermission(entity));
        } catch (Exception e) {
            return Response.exception(e);
        }
    }

    @ApiOperation(value = "删除权限（Vip 相关）", notes = "删除权限（Vip 相关", produces = "application/json")
    @GetMapping(value = "/delPermission")
    public Response<UserPermissionEntity> delPermission(
            @RequestParam Long id
    ) {
        try {
            return Response.ok(vipService.delPermission(id));
        } catch (Exception e) {
            return Response.exception(e);
        }
    }

    @ApiOperation(value = "获取所有权限（Vip 相关）", notes = "获取所有权限（Vip 相关", produces = "application/json")
    @GetMapping(value = "/getPermission")
    public Response<List<UserPermissionEntity>> getPermission() {
        try {
            return Response.ok(vipService.getPermission());
        } catch (Exception e) {
            return Response.exception(e);
        }
    }

    @ApiOperation(value = "添加Vip等级", notes = "添加Vip等级", produces = "application/json")
    @PostMapping(value = "/addVip")
    public Response<VipEntity> addVip(
            @RequestBody VipEditModel model
    ) {
        try {
            return Response.ok(vipService.addVip(model));
        } catch (Exception e) {
            return Response.exception(e);
        }
    }

    @ApiOperation(value = "修改vip等级", notes = "修改vip等级", produces = "application/json")
    @PostMapping(value = "/updateVip")
    public Response<VipEntity> updateVip(
            @RequestBody VipEditModel model
    ) {
        try {
            return Response.ok(vipService.updateVip(model));
        } catch (Exception e) {
            return Response.exception(e);
        }
    }

    @ApiOperation(value = "删除Vip ）", notes = "删除Vip ", produces = "application/json")
    @GetMapping(value = "/delVip")
    public Response<VipEntity> delVip(
            @RequestParam Long id
    ) {
        try {
            return Response.ok(vipService.delVip(id));
        } catch (Exception e) {
            return Response.exception(e);
        }
    }

    @ApiOperation(value = "批量启/禁用 vip ", notes = "批量启/禁用 vip  ", produces = "application/json")
    @GetMapping(value = "/updateVipStatus")
    public Response<List<VipEntity>> updateVipStatus(
            @RequestParam List<Long> vipIds,
            @RequestParam Integer status
    ) {
        try {
            return Response.ok(vipService.updateVipStatus(vipIds, status));
        } catch (Exception e) {
            return Response.exception(e);
        }
    }

    @ApiOperation(value = "分页获取Vip）", notes = "分页获取Vip", produces = "application/json")
    @PostMapping(value = "/getVipPage")
    public Response<Page<VipModel>> getVipPage(@RequestBody VipQueryVo queryVo) {
        try {
            return Response.ok(vipService.getVipPage(queryVo));
        } catch (Exception e) {
            return Response.exception(e);
        }
    }

    @ApiOperation(value = "获取全部vip）", notes = "获取全部vip", produces = "application/json")
    @GetMapping(value = "/getAllVip")
    public Response<List<VipEntity>> getAllVip() {
        try {
            return Response.ok(vipService.getAllVip());
        } catch (Exception e) {
            return Response.exception(e);
        }
    }

}
