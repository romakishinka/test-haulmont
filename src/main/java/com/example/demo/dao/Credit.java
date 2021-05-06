package com.example.demo.dao;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class Credit {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private Integer limit;
    private Integer percent;
    @OneToMany(mappedBy = "credit", targetEntity = Offer.class, fetch = FetchType.LAZY)
    private List<Offer> offers;
}
