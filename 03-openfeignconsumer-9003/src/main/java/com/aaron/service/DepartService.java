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
