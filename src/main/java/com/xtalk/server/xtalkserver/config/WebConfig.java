package com.xtalk.server.xtalkserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author xin.z
 * @date 2021/1/20 5:25 下午
 * 图片资源映与本地服务器映射
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${file-save-path}")
    private String fileSavePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:"+fileSavePath);
    }
}
