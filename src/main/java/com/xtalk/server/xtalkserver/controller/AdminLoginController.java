package com.xtalk.server.xtalkserver.controller;

import com.xtalk.server.xtalkserver.model.AdminUserLoginModel;
import com.xtalk.server.xtalkserver.model.Response;
import com.xtalk.server.xtalkserver.service.AdminUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xin.z
 * @date 2021/1/22 8:42 下午
 */
@Api("后台用户登陆")
@RestController
@RequestMapping(value = "/admin/login")
@Slf4j
public class AdminLoginController {

    @Autowired
    AdminUserService adminUserService;

    @ApiOperation(value = "后台用户登陆", notes = "后台用户登陆", produces = "application/json")
    @PostMapping(value = "/login")
    public Response<AdminUserLoginModel> login(
            @RequestParam String account,
            @RequestParam String password) {
        try {
            return Response.ok(adminUserService.loginByPwd(account, password));
        } catch (Exception e) {
            return Response.exception(e);
        }
    }

}
