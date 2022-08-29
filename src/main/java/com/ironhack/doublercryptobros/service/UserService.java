package com.ironhack.doublercryptobros.service;

import com.ironhack.doublercryptobros.dto.UserDTO;
import com.ironhack.doublercryptobros.model.CryptoFav;
import com.ironhack.doublercryptobros.model.User;
import com.ironhack.doublercryptobros.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserDTO register(String username, String password) {
        List<CryptoFav> favsList = new ArrayList<>();
        // codificar el password en base64
        User userToRegister = new User(username, password, favsList);
        User userSaved = userRepository.save(userToRegister);
        return UserDTO.fromEntity(userSaved);
    }

    public Boolean authenticate(String username, String password) {
        Optional<User> OptionalUser = userRepository.findByUsername(username);
        if(OptionalUser.isEmpty()) {
            return false;
        }
        // decodificar el password en base64
        User user = OptionalUser.get();
        return user.getPassword().equals(password);
    }
}
