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
