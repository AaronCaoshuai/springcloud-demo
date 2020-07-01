package com.aaron.service.impl;

import com.aaron.dao.DepartRepository;
import com.aaron.domain.Depart;
import com.aaron.service.IDepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Depart 业务实现类
 */
@Service
public class DepartServiceImpl implements IDepartService {

    @Autowired
    private DepartRepository departRepository;

    @Override
    public boolean saveDepart(Depart depart) {
        Depart saveDepart = departRepository.save(depart);
        if(saveDepart != null){
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteDepart(Integer id) {
        if(departRepository.existsById(id)){
            departRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateDepart(Depart depart) {
        Depart saveDepart = departRepository.save(depart);
        if(saveDepart != null){
            return true;
        }
        return false;
    }

    @Override
    public Depart get(Integer id) {
        if(departRepository.existsById(id)){
            return departRepository.getOne(id);
        }
        return null;
    }

    @Override
    public List<Depart> getList() {
        //模拟调用超时 判断Feign的超时设置是否有效
        try {
            System.out.println("模拟超时");
            Thread.sleep(10000);
            System.out.println("模拟超时结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return departRepository.findAll();
    }
}
