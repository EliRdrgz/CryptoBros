package com.ironhack.doublercryptobros.dto;

import com.ironhack.doublercryptobros.model.CryptoFav;
import com.ironhack.doublercryptobros.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
    private Long id;
    private String username;
    private List<CryptoFav> favs;

    public static UserDTO fromEntity(User entity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(entity.getId());
        userDTO.setUsername(entity.getUsername());
        userDTO.setFavs(entity.getFavs());

        return userDTO;
    }

    public static User fromDTO (UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setFavs(dto.getFavs());

        return user;
    }
}
