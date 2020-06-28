package com.aaron.service;

import com.aaron.domain.Depart;

import java.util.List;

/**
 * depart业务接口
 */
public interface IDepartService {
    /**
     * 保存部门信息
     * @param depart
     * @return
     */
    boolean saveDepart(Depart depart);

    /**
     * 删除部门
     * @param id
     * @return
     */
    boolean deleteDepart(Integer id);

    /**
     * 修改部门信息
     * @param depart
     * @return
     */
    boolean updateDepart(Depart depart);

    /**
     * 根据id获取部门信息
     * @param id
     * @return
     */
    Depart get(Integer id);

    /**
     * 获取部门列表
     * @return
     */
    List<Depart> getList();

}
