package com.ironhack.doublercryptobros.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;

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

    @ManyToMany(fetch = EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "crypto_fav_users",
            joinColumns = @JoinColumn(name = "user_id" , referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "crypto_fav_id",  referencedColumnName = "id"))
    private List<CryptoFav> favs;

    public User(String username, String password, List<CryptoFav> favs) {
        if(username.length() < 2){
            System.out.println("The username has to be more than 3 characters.");
        }else{
            setUsername(username);
        }
        if(password.isEmpty()){
            System.out.println("Not valid password. Please enter a correct password.");
        }else {
            setPassword(password);
        }
        setFavs(favs);
    }
}
