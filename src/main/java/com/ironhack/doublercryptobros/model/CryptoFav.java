package com.ironhack.doublercryptobros.model;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;

@NoArgsConstructor
@Entity
public class CryptoFav {

    @Id
    private String id;

    @ManyToMany
    private List<User> users;

    public CryptoFav(String id) {
        this.id = id;
    }

    // grafico del historico de la moneda

}
