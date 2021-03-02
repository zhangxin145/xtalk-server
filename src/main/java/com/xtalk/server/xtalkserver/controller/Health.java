package com.xtalk.server.xtalkserver.controller;

import com.xtalk.server.xtalkserver.model.Response;
import com.xtalk.server.xtalkserver.repository.BaseRepository;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xin.z
 * @date 2021/1/20 4:08 下午
 */
@Api("健康检查")
@RestController
public class Health {

    @GetMapping(value = "/check")
    public Response<String> healthCheck() {
        return Response.ok();
    }

}
