package com.ironhack.doublercryptobros.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    @ManyToMany(mappedBy = "users")
    private List<CryptoFav> favs;

    public User(String username, String password, List<CryptoFav> favs) {
        this.username = username;
        this.password = password;
        this.favs = favs;
    }
}
