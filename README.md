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















