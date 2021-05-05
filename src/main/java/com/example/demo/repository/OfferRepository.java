package com.example.demo.repository;

import com.example.demo.dao.KeyForOffer;
import com.example.demo.dao.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, KeyForOffer> {
    @Query ("from Offer o where o.client.name like concat('%', :name, '%')")
    List<Offer> findByClientName (@Param("name") String name);

    @Query ("from Offer o where o.credit.name like concat('%', :name, '%')")
    List<Offer> findByCreditName (@Param("name") String name);
}
