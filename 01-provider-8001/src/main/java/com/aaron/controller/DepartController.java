package com.aaron.controller;

import com.aaron.domain.Depart;
import com.aaron.service.IDepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/provider/depart")
public class DepartController {

    @Autowired
    private IDepartService departService;

    @PostMapping
    public boolean saveDepart(@RequestBody Depart depart){
        return departService.saveDepart(depart);
    }

    @PutMapping
    public boolean updateDepart(@RequestBody Depart depart){
        return departService.updateDepart(depart);
    }

    @DeleteMapping("/{id}")
    public boolean deleteDepart(@PathVariable("id")Integer id){
        return departService.deleteDepart(id);
    }

    @GetMapping("/{id}")
    public Depart getDepart(@PathVariable("id")Integer id){
        return departService.get(id);
    }

    @GetMapping
    public List<Depart> getDeparts(){
        return departService.getList();
    }
}
