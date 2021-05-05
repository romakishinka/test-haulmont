package com.example.demo.repository;


import com.example.demo.dao.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CreditRepository extends JpaRepository<Credit, Integer> {
    @Query("from Credit c where c.name like concat('%', :name, '%')")
    List<Credit> findByName (@Param("name") String name);
}
