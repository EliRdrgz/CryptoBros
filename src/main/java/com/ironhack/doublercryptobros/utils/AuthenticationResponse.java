package com.ironhack.doublercryptobros.utils;

import com.ironhack.doublercryptobros.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {

    private UserDTO user;
    private Boolean authorized;
}
