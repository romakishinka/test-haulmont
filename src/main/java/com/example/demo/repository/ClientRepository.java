package com.example.demo.repository;

import com.example.demo.dao.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ClientRepository extends JpaRepository <Client, Integer> {
    @Query ("from Client c where c.name like concat('%', :name, '%')")
    List<Client> findByName (@Param("name") String name);
}
