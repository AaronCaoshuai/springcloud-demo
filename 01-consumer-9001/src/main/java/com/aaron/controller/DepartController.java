package com.aaron.controller;

import com.aaron.domain.Depart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * 消费者控制器
 */
@RestController
@RequestMapping("/consumer/depart")
public class DepartController {

    @Autowired
    private RestTemplate restTemplate;

    private static final String SERVICE_PROVIDER = "http://localhost:8001";

    private static final String SERVICE_DEPART_URL = SERVICE_PROVIDER + "/provider/depart/";

    @PostMapping
    public boolean saveDepart(@RequestBody Depart depart){
        return restTemplate.postForObject(SERVICE_DEPART_URL,depart,Boolean.class);
    }

    @PutMapping
    public boolean updateDepart(@RequestBody Depart depart){
        return restTemplate.postForObject(SERVICE_DEPART_URL,depart,Boolean.class);
    }

    @DeleteMapping("/{id}")
    public void deleteDepart(@PathVariable("id")Integer id){
         restTemplate.delete(SERVICE_DEPART_URL + id);
    }

    @GetMapping
    public List<Depart> getDeparts(){
        return restTemplate.getForObject(SERVICE_DEPART_URL,List.class);
    }

    @GetMapping("/{id}")
    public Depart getDepart(@PathVariable("id")Integer id){
        return restTemplate.getForObject(SERVICE_DEPART_URL + id,Depart.class);
    }



}
