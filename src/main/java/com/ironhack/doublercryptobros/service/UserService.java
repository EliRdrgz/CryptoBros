package com.ironhack.doublercryptobros.service;

import com.ironhack.doublercryptobros.dto.UserDTO;
import com.ironhack.doublercryptobros.model.CryptoFav;
import com.ironhack.doublercryptobros.model.User;
import com.ironhack.doublercryptobros.repository.CryptoFavRepository;
import com.ironhack.doublercryptobros.repository.UserRepository;
import com.ironhack.doublercryptobros.utils.AuthenticationResponse;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
@ToString
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CryptoFavRepository cryptoFavRepository;

    public UserDTO register(String username, String password) {
        String encodedPassword = Base64.getEncoder().encodeToString(password.getBytes());
        User userToRegister = new User(username, encodedPassword);
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
    public UserDTO addToFavs(Long userId, String cryptoId) {
        User userToUpdate = userRepository.findById(userId).get();
        CryptoFav cryptoFav = getCryptoFav(cryptoId);
        userToUpdate.getFavs().add(cryptoFav);
        User updatedUser = userRepository.save(userToUpdate);
        return UserDTO.fromEntity(updatedUser);
    }

    private CryptoFav getCryptoFav(String cryptoId) {
        Optional<CryptoFav> optionalCryptoFav = cryptoFavRepository.findById(cryptoId);
        if(optionalCryptoFav.isPresent()) {
            return optionalCryptoFav.get();
        }
        CryptoFav cryptoFav = new CryptoFav(cryptoId);
        return cryptoFavRepository.save(cryptoFav);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public List<CryptoFav> checkFavs(Long userId) {
        var user = userRepository.findById(userId).get();
        return user.getFavs();
    }

    public UserDTO removeFav(Long userId, String cryptoId) {
        User userToUpdate = userRepository.findById(userId).get();

        var favsList = userToUpdate.getFavs();
        int indexFavToRemove = 0;
        for (CryptoFav fav : favsList) {
            if (fav.getId().equals(cryptoId)) {
                favsList.remove((indexFavToRemove));
                break;
            } else indexFavToRemove++;
        }

        User updatedUser = userRepository.save(userToUpdate);
        return UserDTO.fromEntity(updatedUser);
    }

    public List<CryptoFav> getUserFavList(Long id) {
        var user = userRepository.findById(id).get();
        return user.getFavs();
    }
}
