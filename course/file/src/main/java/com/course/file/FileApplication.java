package com.course.file;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan("com.course")
@MapperScan("com.course.server.mapper")
public class FileApplication {

    private static final Logger LOG = LoggerFactory.getLogger((FileApplication.class));

    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(FileApplication.class);
        Environment env = app.run(args).getEnvironment();
        LOG.info("Successfully started");
        LOG.info("File address: \thttp:/127.0.0.1:{}", env.getProperty("server.port"));
    }
}
