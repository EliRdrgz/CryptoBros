package com.ironhack.doublercryptobros.service;

import com.ironhack.doublercryptobros.dto.UserDTO;
import com.ironhack.doublercryptobros.model.CryptoFav;
import com.ironhack.doublercryptobros.model.User;
import com.ironhack.doublercryptobros.repository.UserRepository;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@ToString
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserDTO register(String username, String password) {
        List<CryptoFav> favsList = new ArrayList<>();
        // codificar el password en base64
        User userToRegister = new User(username, password, favsList);
        User userSaved = userRepository.save(userToRegister);
        System.out.println("User registered.");
        return UserDTO.fromEntity(userSaved);
    }

    public Boolean authenticate(String username, String password) {
        Optional<User> OptionalUser = userRepository.findByUsername(username);
        if(OptionalUser.isEmpty()) {
            System.out.println("The credentials are incorrect.");
            return false;
        }
        // decodificar el password en base64
        User user = OptionalUser.get();
        System.out.println("You are in");
        return user.getPassword().equals(password);
    }
}
