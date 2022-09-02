package com.ironhack.doublercryptobros.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Entity
@Getter
@Setter
public class CryptoFav {

    @Id
    private String id;

    @ManyToMany(mappedBy = "favs", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<User> users;

    public CryptoFav(String id) {
        this.id = id;
    }

    // grafico del historico de la moneda

}
