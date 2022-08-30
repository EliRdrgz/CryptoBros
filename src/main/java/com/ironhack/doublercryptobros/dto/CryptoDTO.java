package com.ironhack.doublercryptobros.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CryptoDTO {
    private String id;
    private String name;

    @JsonProperty(value = "marketCapUsd")
    private String marketCap;

    private String rank;
    private String priceUsd;
}
