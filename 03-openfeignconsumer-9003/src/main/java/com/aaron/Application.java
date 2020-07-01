package com.aaron;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient //开启服务发现功能
@EnableFeignClients //开启Feign客户端 可以指定service接口所在包
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
