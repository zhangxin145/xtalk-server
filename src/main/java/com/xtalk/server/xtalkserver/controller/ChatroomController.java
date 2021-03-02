package com.xtalk.server.xtalkserver.controller;

import com.xtalk.server.xtalkserver.entity.ChatroomsEntity;
import com.xtalk.server.xtalkserver.model.*;
import com.xtalk.server.xtalkserver.service.ChatRoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xin.z
 * @date 2021/1/31 1:38 下午
 */
@Api("聊天室相关接口")
@RestController
@RequestMapping(value = "/v1/chatRoom")
@Slf4j
public class ChatroomController {

    @Autowired
    ChatRoomService chatRoomService;

    @ApiOperation(value = "添加聊天室", notes = "添加聊天室", produces = "application/json")
    @PostMapping(value = "/add")
    public Response<ChatroomsEntity> add(
            @RequestBody ChatRoomVo chatRoomVo
    ) {
        try {
            return Response.ok(chatRoomService.add(chatRoomVo));
        } catch (Exception e) {
            return Response.exception(e);
        }
    }

    @ApiOperation(value = "修改聊天室", notes = "修改聊天室", produces = "application/json")
    @PostMapping(value = "/update")
    public Response<ChatroomsEntity> update(
            @RequestBody ChatRoomVo chatRoomVo
    ) {
        try {
            return Response.ok(chatRoomService.update(chatRoomVo));
        } catch (Exception e) {
            return Response.exception(e);
        }
    }

    @ApiOperation(value = "删除聊天室", notes = "删除聊天室", produces = "application/json")
    @GetMapping(value = "/del")
    public Response<ChatroomsEntity> del(
            @RequestParam Long id
    ) {
        try {
            return Response.ok(chatRoomService.del(id));
        } catch (Exception e) {
            return Response.exception(e);
        }
    }

    @ApiOperation(value = "对整个聊天室禁言/取消禁言", notes = "对整个聊天室禁言/取消禁言", produces = "application/json")
    @GetMapping(value = "/mute")
    public Response<ChatroomsEntity> mute(
            @RequestParam("chatRoomId") Long chatRoomId,
            @RequestParam("isMute") Long isMute)

    {
        try {
            return Response.ok(chatRoomService.mute(chatRoomId, isMute));
        } catch (Exception e) {
            return Response.exception(e);
        }
    }

    @ApiOperation(value = "对聊天室成员禁言/取消禁言-支持批量", notes = "对聊天室成员禁言/取消禁言-支持批量", produces = "application/json")
    @PostMapping(value = "/muteMember")
    public Response<ChatroomsEntity> muteMember(
            @RequestBody MuteModel muteModel

    ) {
        try {
            return Response.ok(chatRoomService.muteMember(muteModel));
        } catch (Exception e) {
            return Response.exception(e);
        }
    }

    @ApiOperation(value = "对聊天室添加成员-支持批量", notes = "对聊天室添加成员-支持批量", produces = "application/json")
    @GetMapping(value = "/addMember")
    public Response<ChatroomsEntity> addMember(
            @RequestParam Long chatRoomId,
            @RequestParam List<Long> usrIds
    ) {
        try {
            return Response.ok(chatRoomService.addMember(chatRoomId, usrIds));
        } catch (Exception e) {
            return Response.exception(e);
        }
    }

    @ApiOperation(value = "对聊天室移除成员-支持批量", notes = "对聊天室移除成员-支持批量", produces = "application/json")
    @GetMapping(value = "/delMember")
    public Response<ChatroomsEntity> delMember(
            @RequestParam Long chatRoomId,
            @RequestParam List<Long> usrIds
    ) {
        try {
            return Response.ok(chatRoomService.delMember(chatRoomId, usrIds));
        } catch (Exception e) {
            return Response.exception(e);
        }
    }


    @ApiOperation(value = "根据id获取详情", notes = "根据id获取详情", produces = "application/json")
    @GetMapping(value = "/getById")
    public Response<ChatRoomModel> getById(
            @RequestParam Long id
    ) {
        try {
            return Response.ok(chatRoomService.getById(id));
        } catch (Exception e) {
            return Response.exception(e);
        }
    }


    @ApiOperation(value = "分页获取聊天室列表", notes = "分页获取聊天室列表", produces = "application/json")
    @PostMapping (value = "/getPageResult")
    public Response<Page<ChatRoomModel>> getPageResult(
            @RequestBody ChatRoomQueryVo queryVo
    ) {
        try {
            return Response.ok(chatRoomService.getPageResult(queryVo));
        } catch (Exception e) {
            return Response.exception(e);
        }
    }

}
