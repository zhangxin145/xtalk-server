package com.xtalk.server.xtalkserver.controller;

import com.xtalk.server.xtalkserver.entity.FriendsterWebsiteEntity;
import com.xtalk.server.xtalkserver.model.Response;
import com.xtalk.server.xtalkserver.service.WebsiteService;
import com.xtalk.server.xtalkserver.utils.DateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.UUID;

/**
 * @author xin.z
 * @date 2021/1/20 4:58 下午
 */
@Api("网站相关API")
@RestController
@RequestMapping(value = "/v1/website")
@Slf4j
public class WebsiteController<SimpleDateFormat> {

    @Value("${file-save-path}")
    private String fileSavePath;

    @Autowired
    WebsiteService websiteService;

    @ApiOperation(value="上传文件", notes="上传文件", produces="application/json")
    @PostMapping(path = "/upload")
    public Response<String> uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) {

        String directory = DateUtils.getTime() + "/";

        File dir = new File(fileSavePath + directory);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        log.info("图片上传，保存位置：" + fileSavePath + directory);
        String suffix = file.getOriginalFilename()
                .substring(file.getOriginalFilename().lastIndexOf("."));
        String newFileName = UUID.randomUUID()
                .toString().replaceAll("-", "") + suffix;
        File newFile = new File(fileSavePath + directory + newFileName);
        try {
            file.transferTo(newFile);
            String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/images/" + directory + newFileName;
            log.info("图片上传，访问URL：" + url);
            return Response.ok(url);
        } catch (Exception e) {
            return Response.exception(e);
        }
    }
    @ApiOperation(value="新增", notes="新增", produces="application/json")
    @PostMapping(value = "/create")
    public Response<FriendsterWebsiteEntity> create(
            @RequestBody FriendsterWebsiteEntity entity) {
        try {
            return Response.ok(websiteService.create(entity));
        } catch (Exception e) {
            return Response.exception(e);
        }
    }

    @ApiOperation(value="修改", notes="修改", produces="application/json")
    @PostMapping(value = "/update")
    public Response<FriendsterWebsiteEntity> update(
            @RequestBody FriendsterWebsiteEntity entity) {
        try {
            return Response.ok(websiteService.update(entity));
        } catch (Exception e) {
            return Response.exception(e);
        }
    }

    @ApiOperation(value="删除", notes="删除", produces="application/json")
    @GetMapping(value = "/del")
    public Response<FriendsterWebsiteEntity> del(
            @RequestParam long id) {
        try {
            return Response.ok(websiteService.del(id));
        } catch (Exception e) {
            return Response.exception(e);
        }
    }

    @ApiOperation(value="获取列表", notes="获取列表", produces="application/json")
    @PostMapping(value = "/get")
    public Response<List<FriendsterWebsiteEntity>> getResult(
            @RequestBody FriendsterWebsiteEntity entity
    ) {
        try {
            return Response.ok(websiteService.get(entity));
        } catch (Exception e) {
            return Response.exception(e);
        }
    }
}
