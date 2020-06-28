package com.aaron.dao;

import com.aaron.domain.Depart;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * jpa持久层
 */
public interface DepartRepository extends JpaRepository<Depart,Integer> {

}
