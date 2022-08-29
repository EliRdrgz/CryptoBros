package com.ironhack.doublercryptobros.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CryptoDTO {
    private String id;
    private String name;
    private String marketCap;
    private String rank;
    private String priceUsd;

}
