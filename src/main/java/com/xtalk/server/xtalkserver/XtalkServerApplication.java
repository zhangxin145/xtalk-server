package com.xtalk.server.xtalkserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing
@EntityScan(basePackages = {
        "com.xtalk.server.xtalkserver.entity",
})
@EnableJpaRepositories(basePackages = {
        "com.xtalk.server.xtalkserver.repository",
})

public class XtalkServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(XtalkServerApplication.class, args);
    }

}
