package com.imooc;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

//标明此项目是springboot的项目
@SpringBootApplication
public class ImoocBilibiliApp {
    public static void main(String[] args) {
        ApplicationContext app = SpringApplication.run(ImoocBilibiliApp.class, args);
    }
}
