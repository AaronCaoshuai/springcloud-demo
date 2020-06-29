package com.aaron;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient //开启服务发现功能
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
