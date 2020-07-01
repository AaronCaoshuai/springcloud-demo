[TOC]

# SpringCloud

## SpringCloud概述

### 简介

SpringCloud是一系列框架的有序集合,它利用SpringBoot开发的便利性,简化了分布式系统基础设施的开发,例如:服务发现注册,配置中心,消息总线,负载均衡,断路器,数据监控等.都可以用SpringBoot的开发风格做到一键启动和部署.SpringCloud将目前各家公司开发比较成熟的经得起考验的服务框架组合起来,通过SpringBoot风格进行在封装屏蔽掉了复杂的配置和实现原理,最终给开发者提供了一套简单易懂,易于部署和维护的分布式系统开发工具包.

参见:

SpringCloud 官网:

https://projects.spring.io/spring-cloud/

SpringCloud中文网:

https://springcloud.cc/

SpringCloud中国社区:

https://springcloud.cn/

### SpringCloud版本

SpringClooud的版本号并不是我们通常见的数字版本号,而是使用英国伦敦地铁站的站名.同时根据字母表的顺序来对应版本时间顺序.比如:最早的Release版本Angel(天使),第二个Release版本Brixton(英国地名),然后是Camden,Dalston,Edgware,目前使用较多的是Finchley(英国地名)版本,最新版本为Hoxton(英国地名),而我们这里使用的是Greenwich(格林威治)

版本小号:

SNAPSHOT:快照版本,可以使用,但仍然在改进开发中,不建议使用.

M版:里程碑版,其后有时候会再跟一个PRE,preview,预览版.内测版.不建议使用.

RC版:Release Candidate,发行候选版,主要用于修复BUG.其后有时候会再跟一个PRE.

SR版:Service Release版本,服务发行版.正式发布版.后面有时会跟一个GA,General Availability,官方推荐版.

和SpringBoot的对应版本:

| Release Train | Boot Version |
| :------------ | :----------- |
| Hoxton        | 2.2.x        |
| Greenwich     | 2.1.x        |
| Finchley      | 2.0.x        |
| Edgware       | 1.5.x        |
| Dalston       | 1.5.x        |

### SpringCloud与Dubbo技术选型

1.架构完整度:

Dubbo仅提供服务注册于服务治理两个模块

SpringCloud对于微服务的各种模块支持比较完善,包含多个模块

2.社区活跃度:

二者社区均比较活跃,SpringCloud面向范围更加广泛,Dubbo目前也已经交给了Apache基金会.

3.通讯协议

Dubbo服务间的通讯协议使用的是RPC,SpringCloud则采用的是Http Rest.

Dubbo可以作为SpringCloud中的一种通讯协议来使用.

如:SpringCloud gRPC.SpringCloud Dubbo

4.技术改造与微服务开发

对于以前的项目改造的话,Dubbo的改造成本相较于SpringCloud低.

如果以前项目是使用SpringBoot的项目,那么最后改造成SpringCloud.

或者新项目建议选择SpringCloud.

### 初始的服务提供者和消费者项目

数据库使用MySQL,持久化技术使用简单的Spring Data JPA作为持久化技术.SpringBoot版本:2.1.15,用作后续微服务的运行环境.

生产者项目:01-provider-8001

消费者项目:01-consumer-9001



## SpringCloud---Eureka

### Eureka概述

#### 简介:

Eureka是Netflix开发的服务发现框架,本身是一个基于REST的服务,主要用于定位运行在AWS域中的中间层服务,以达到负载均衡和中间层服务故障转移的目的.SpringCloud将它集成在器子项目spring-cloud-netflix中,实现SpringCloud的服务发现功能.

Eureka是一个专门用于服务发现的服务器,一些服务注册到该服务器,而另一些服务通过该服务查找其所需要调用执行的服务.可以充当服务发现服务器的组件很多.例如:Zookeeper,Consul,Eureka等.

#### 体系架构图

![1593430144554](C:\Users\semon\AppData\Roaming\Typora\typora-user-images\1593430144554.png)

### CAP定理

#### 概念

CAP定理指的是在一个分布式系统中,Consistency(一致性),Availability(可用性),Partition tolerance(分区容错性),三者不可兼得.

一致性(C):分布式系统中多个主机之间是否能够保持数据一致的特性.即,当系统数据发生更新操作后,各个主机中的数据仍然处于一致的状态

可用性(A):系统提供的服务必须一直处于可用的状态,即对于用户的每个请求,系统总是可以在有限的时间内对用户做出响应.

分区容错性(P):分布式系统在遇到任何网络分区故障时,仍能够保证对外提供满足一致性和可用性的服务.

CAP定理的内容是:对于分布式系统,网络环境相对是不可控的,出现网络分区是不可避免的,因此系统必须具备分区容错性,但系统不能同时保证一致性与可用性.即要么CP,要么AP.

#### 简单对比

Eureka与Zookeeper都可以充当服务中心,主要区别在于对于CAP原则的支持不同

Eureka:AP,Zk:CP

### Eureka实战

#### 入门案例

Eureka服务中心 02-eurekaserver-7001 **单节点Eureka Server**

服务提供者:02-eurekaprovider-8002,02-eurekaprovider-8003,02-eurekaprovider-8004

服务消费者:02-eurekaconsumer-9002

注意:对于F版本以上的SpringCloud对应的Eureka版本,

对于多个生产者的选择使用轮询机制进行访问.

#### Eureka的自我保护机制(高可用)

![1593437389254](C:\Users\semon\AppData\Roaming\Typora\typora-user-images\1593437389254.png)

图中红色字体表示eureka进入了自我保护模式.

翻译:紧急情况,当微服务主机联系不上时,Eureka不能够准确判断他们是否处于up状态.当续约数量(指收到的微服务主机的心跳)小于阈值时,为了安全,微服务主机将不再失效.

默认情况下,EurekaServer在90秒内没有检测到服务列表中的某微服务,则会自动将该微服务从服务列表中删除,但很多情况并不是该微服务节点(主机)出了问题,而是由于网络抖动等原因是该微服务无法被EurekaServer发现,即无法检测到该微服务主机的心跳.若在短暂时间内网络恢复正常,但由于EurekaServer的服务列表找那个已经没有该微服务,所以该微服务已经无法提供服务了.

在短时间内若EurekaServer丢失较多的微服务,即EurekaServer收到的心跳数量小于阈值,为了保证系统的可用性(AP),给那些由于网络抖动而被认为宕机的客户端"重新复活"的机会,Eureka会自动进入自我保护模式,**服务列表只可以读取,写入,不可执行删除操作**.当EurekaServer收到的心跳数量恢复到阈值以上时,其会自动退出Self Preservation模式.

启动自我保护的阈值因子默认值为0.85,即85%,EurekaServer收到的心跳数量若小于应该收到的数量的85%时,会启动自我保护机制.

自我保护机制默认是开启的,可以通过修改EurekaServer中的配置文件来关闭,但不建议关闭.

自我保护启动阈值

![1593438308133](C:\Users\semon\AppData\Roaming\Typora\typora-user-images\1593438308133.png)

Renews threshold:Eureka Server期望每分钟收到的Client的续约总数.

假设最近15分钟收到的续约数量为count,根据设置的阈值因子(默认为0.85),计算出这15分钟应该收到的续约数量count*0.85,然后在平均到每分钟count * 0.85 / 15,就是当前这个值,若正真收到的续约数量小于改值,则启动自我保护.

Renews(last min):Eureka Server实际在最后一分钟收到的Client的续约数量.

可以设置自我保护相关的配置项来改变Eureka Server的自我保护行为.

![1593439455649](C:\Users\semon\AppData\Roaming\Typora\typora-user-images\1593439455649.png)

可以设置关闭自我保护机制,不建议关闭

可以根据自己的需要来更改阈值大小.

#### Eureka集群(高可用)

![1593441829770](C:\Users\semon\AppData\Roaming\Typora\typora-user-images\1593441829770.png)

修改hosts文件设置域名映射。

C:\Windows\System32\drivers\etc

127.0.0.1 eurekaserver7001.com
127.0.0.1 eurekaserver7002.com
127.0.0.1 eurekaserver7003.com

为什么不用IP地址而是用域名?

**是因为我把集群部署在同一个IP上，而eureka通过主机名判断是否可用，如果我们的IP地址相同它的主机名将会是相同的，eureka就认为我们的不可用。所以我们使用域名区分。 如果我们的eureka不是在不同的主机上，就可以不同配置。**

  主机名： 如果我们在application-xxx.yml文件中的配置了eureka.instance.hostname主机名就是它的值。

  如果我们没有配置我们可以在eureka控制台Instances currently registered with Eureka的Status中看到，如： DESKTOP-6JLT72M.mshome.net 这个值就是主机名

 当我们启动第一个项目后，控制台报错如下错误，不必担心，因为我们设计的注册中心是三台集群，这三台Eureka会相互注册，一个Eureka会把其他两个Eureka注册进来，但是我们只启动了一台，所以启动时因为无法将剩下两个Eureka注册进来，所以就会报错，等我们依次启动三个Eureka，在最后一个项目里就不会报错。前两个报错的Eureka，也会在稍后将报错的没有注册进来的Eureka注册进来 

对于Eureka集群除了配置文件中的，server.port(端口),eureka.instance.hostname(域名),和client.service-url.defaultZone不同其他均保持不变.

对于Eureka集群一定要保证spring.application.name保持一致,否则会出现不可用的副本节点.

Eureka集群是可以使用两两相互注册连完成相互注册.

对于Eureka Client端,只需要把eureka.client.service-url.defaultZone中的其他集群节点加入即可.

集群部署:

**可以创建一个项目,单个配置文件根据多profile配置区分多个项目进行启动,然后进行集群部署.**

```java
---
spring:
  application:
    name: spring-cloud-eureka
  profiles: peer1
server:
  port: 8000
eureka:
  instance:
    hostname: peer1
  client:
    serviceUrl:
      defaultZone: http://peer2:8001/eureka/,http://peer3:8002/eureka/
---
spring:
  application:
    name: spring-cloud-eureka
  profiles: peer2
server:
  port: 8001
eureka:
  instance:
    hostname: peer2
  client:
    serviceUrl:
      defaultZone: http://peer1:8000/eureka/,http://peer3:8002/eureka/
---
spring:
  application:
    name: spring-cloud-eureka
  profiles: peer3
server:
  port: 8002
eureka:
  instance:
    hostname: peer3
  client:
    serviceUrl:
      defaultZone: http://peer1:8000/eureka/,http://peer2:8001/eureka/
```

`nohup java -jar eureka.jar --spring.profiles.active=peer1 > peer1.out &`
 1.nohup 以及最后的 & 表示启动程序的时候在后台运行，执行完此命令后不打出日志，并且关掉ssh后当前进程不会停止。
 2.java -jar eureka.jar 表示用java命令以jar包的形式启动eureka.jar
 3.–spring.profiles.active=peer1 两个减号及后台的参数表示启动的时候加载spring.profiles名字为peer1的一系列参数，在上面的例子中表示加载下面的参数：
 4.> peer1.out表示将日志输出到peer1.out

例子中使用多个项目来进行模拟.

注意对于Eureka集群而言需要使用默认的可以向自己注册并且可以获取注册表,来完成集群间注册表的复制.

#### Eureka平滑上下线

服务下线:即某服务不能对外提供服务了.

除了由于Eureka Client宕机或者网络原因导致Eureka Server长时间没有接收到client的心跳而将其剔除出了注册表外,我们也可以主动是某个服务下线.两种方式:

服务下架和状态修改.这两种方式都是基于Actuator监控器实现的.

添加actuator依赖:

```java
 <dependency>
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-starter-actuator</artifactId>
 </dependency>
```

服务下架:直接停止该服务.

![1593604455954](C:\Users\semon\AppData\Roaming\Typora\typora-user-images\1593604455954.png)

服务下线:指的是在Eureka注册中心该服务状态变更为down

![1593604510538](C:\Users\semon\AppData\Roaming\Typora\typora-user-images\1593604510538.png)

服务上线:Eureka注册中心变成UP的状态.

![1593604562892](C:\Users\semon\AppData\Roaming\Typora\typora-user-images\1593604562892.png)

#### Eureka的常见配置



### Eureka工作原理





## SpringCloud-OpenFegin和Ribbon



### OpenFegin简介

 Feign 是一个声明式的Web服务客户端，让编写Web服务客户端变得非常容易，只需 创建一个接口并在接口上添加注解 即可 

OpenFegin可以将服务提供者提供的Restful服务伪装为接口进行消费,消费者只需使用Feign接口+注解的方式即可调用提供者提供的Restful服务,而无需再使用RestTemplate.

#### OpenFegin和Fegin的区别:

SpringCloud D版本以及之前的版本使用的是Fegin,而该项目已经更新为OpenFegin,所以依赖发生了改变.

#### OpenFegin和Ribbon

Ribbon是Netflix公司的一个开源的负载均衡项目,是一个客户端负载均衡器,运行在消费这端.

OpenFeign也是运行在消费者端的,使用Ribbon进行负载均衡,所以OpenFegin直接内置了Ribbon.即在导入了OpenFeign依赖后,无需再专门导入Ribbon依赖了.

### OpenFegin实战

1.在消费端pom中添加openfeign依赖

```
  <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>
```

2.在启动类上添加开启Feign客户端的注解

```java
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

```

3.添加feign的一些配置项

```java
#server config
server:
  port: 9003

#eureka config
eureka:
  client:
    service-url:
      defaultZone: http://eurekaserver7001.com:7001/eureka,http://eurekaserver7002.com:7002/eureka,http://eurekaserver7003.com:7003/eureka
  instance:
    instance-id: 03-consumer-9003

#微服务对外暴露的名称
spring:
  application:
    name: openfeginconsumer

#actuator 配置
management:
  #开启所有终端监控
  endpoints:
    web:
      exposure:
        include: "*"
  #开启shutdown监控终端
  endpoint:
    shutdown:
      enabled: true

#Open Feign config
#超时设置
feign:
  client:
    config:
      feignName:
        connectTimeout: 5000 # 连接超时时间5s
        readTimeout: 5000    # 整个相应超时时间

```



4.创建OpenFeign声明式接口

```java
package com.aaron.service;

import com.aaron.domain.Depart;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 声明为feign接口 指定调用者的spring.application.name 其他类似于springmvc
 */
@FeignClient("eurekaprovider")
@RequestMapping("/provider/depart/")
public interface DepartService {

    @PostMapping
    boolean saveDepart(@RequestBody Depart depart);

    @PutMapping
    boolean updateDepart(@RequestBody Depart depart);

    @DeleteMapping("/{id}")
    boolean deleteDepart(@PathVariable("id") Integer id);

    @GetMapping("/{id}")
    Depart getDepart(@PathVariable("id") Integer id);

    @GetMapping
    List<Depart> getDeparts();
}

```

5.在消费端使用声明的feign接口

```java
package com.aaron.controller;

import com.aaron.domain.Depart;
import com.aaron.service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

/**
 * 消费者控制器 改造成使用feign
 */
@RestController
@RequestMapping("/consumer/depart")
public class DepartController {
    //不再使用RestTemplate
    //    @Autowired
    //    private RestTemplate restTemplate;

    @Autowired
    private DepartService departService;

    @Autowired
    private DiscoveryClient client;
    //修改成使用spring.application.name的方式进行访问
    private static final String SERVICE_PROVIDER = "http://eurekaprovider/";

    //private static final String SERVICE_DEPART_URL = SERVICE_PROVIDER + "/provider/depart/";

    @PostMapping
    public boolean saveDepart(@RequestBody Depart depart) {
//        return restTemplate.postForObject(SERVICE_DEPART_URL, depart, Boolean.class);
        return departService.saveDepart(depart);
    }

    @PutMapping
    public boolean updateDepart(@RequestBody Depart depart) {
//        return restTemplate.postForObject(SERVICE_DEPART_URL, depart, Boolean.class);
        return departService.updateDepart(depart);
    }

    @DeleteMapping("/{id}")
    public boolean deleteDepart(@PathVariable("id") Integer id) {
//        restTemplate.delete(SERVICE_DEPART_URL + id);
        return departService.deleteDepart(id);
    }

    @GetMapping
    public List<Depart> getDeparts() {
//        return restTemplate.getForObject(SERVICE_DEPART_URL, List.class);
        return departService.getDeparts();
    }

    @GetMapping("/{id}")
    public Depart getDepart(@PathVariable("id") Integer id) {
//        return restTemplate.getForObject(SERVICE_DEPART_URL + id, Depart.class);
        return departService.getDepart(id);
    }

    @GetMapping("/discovery")
    public Object discoveryHandler() {
        //获取注册中心中所有的服务名称 即spring.application.name
        List<String> services = client.getServices();
        for (String name : services) {
            //获取每个application.name对应的服务实例
            List<ServiceInstance> instances = client.getInstances(name);
            for (ServiceInstance instance : instances) {
                //获取每个实例的instanceId 即eureka.client.instance.instance.id
                String instanceId = instance.getInstanceId();
                //获取服务提供者的uri,主机名和端口号
                URI uri = instance.getUri();
                String host = instance.getHost();
                int port = instance.getPort();
                System.out.println(instanceId + ":" + uri);
                System.out.println(host + ":" + port);
            }
        }
        return services;
    }

}
```

从原来使用RestTemplate方式调用http接口改造成使用feign的形式,进行调用.

