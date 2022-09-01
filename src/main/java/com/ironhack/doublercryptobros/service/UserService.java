package com.ironhack.doublercryptobros.service;

import com.ironhack.doublercryptobros.dto.UserDTO;
import com.ironhack.doublercryptobros.model.CryptoFav;
import com.ironhack.doublercryptobros.model.User;
import com.ironhack.doublercryptobros.repository.UserRepository;
import com.ironhack.doublercryptobros.utils.AuthenticationResponse;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
@ToString
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserDTO register(String username, String password) {
        List<CryptoFav> favsList = new ArrayList<>();
        String encodedPassword = Base64.getEncoder().encodeToString(password.getBytes());
        User userToRegister = new User(username, encodedPassword, favsList);
        User userSaved = userRepository.save(userToRegister);

        return UserDTO.fromEntity(userSaved);
    }

    public AuthenticationResponse authenticate(String username, String password) {
        Optional<User> OptionalUser = userRepository.findByUsername(username);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();

        if(OptionalUser.isPresent()) {
            User user = OptionalUser.get();
            String decodedPassword = new String(Base64.getDecoder().decode(user.getPassword()));

            if(decodedPassword.equals(password)){
                authenticationResponse.setUser(UserDTO.fromEntity(user));
                authenticationResponse.setAuthorized(true);
                return authenticationResponse;
            } else {
                authenticationResponse.setUser(null);
                authenticationResponse.setAuthorized(false);
            }
        } else {
            authenticationResponse.setUser(null);
            authenticationResponse.setAuthorized(false);
        }

        return authenticationResponse;
    }

    private UserDTO addToFavs(UserDTO user, String cryptoId) {
        User userToUpdate = UserDTO.fromDTO(user);
        List<CryptoFav> favs = new ArrayList<>();
        favs.add(new CryptoFav(cryptoId));
        userToUpdate.setFavs(favs);

        User updatedUser = userRepository.save(userToUpdate);
        return UserDTO.fromEntity(updatedUser);
    }


}
