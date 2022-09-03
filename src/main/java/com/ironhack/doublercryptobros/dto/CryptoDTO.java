package com.ironhack.doublercryptobros.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class CryptoDTO {
    private String id;
    private String name;

    @JsonProperty(value = "marketCapUsd")
    private String marketCap;

    private String rank;
    private String priceUsd;

    @Override
    public String toString() {
        return  "Id: " + id + '\n'+
                "Name: " + name + '\n' +
                "Market Cap: " + marketCap + '\n' +
                "Rank: " + rank + '\n' +
                "Price Usd: " + priceUsd +'\n' +
                "----------------------------------------";
    }
}
