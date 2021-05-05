package com.example.demo.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Setter
@Getter
@Table(name = "Offer")
@AllArgsConstructor
@NoArgsConstructor
public class Offer {
    @EmbeddedId
    private KeyForOffer keyForOffer;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "creditID", referencedColumnName = "id", insertable = false, updatable = false)
    private Client client;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "creditID", referencedColumnName = "id", insertable = false, updatable = false)
    private Credit credit;
    private Integer amount;
}


