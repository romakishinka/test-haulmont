package com.example.demo.dao;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String telephone;
    private String email;
    private String document;
    @OneToMany(mappedBy = "client", targetEntity = Offer.class, fetch = FetchType.LAZY)
    private List<Offer> offers;
}
