package com.ironhack.doublercryptobros.service;

import com.ironhack.doublercryptobros.dto.CryptoDTO;
import com.ironhack.doublercryptobros.dto.ListCryptoResponse;
import com.ironhack.doublercryptobros.dto.SingleCryptoResponse;
import com.ironhack.doublercryptobros.repository.CryptoFavRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;

@Service
public class CryptoService {

    @Autowired
    CryptoFavRepository cryptoFavRepository;

    private WebClient webClient = WebClient.create("https://api.coincap.io/v2");

    public ListCryptoResponse findAllCryptos() {
        return webClient.get()
                .uri("/assets")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(ListCryptoResponse.class)
                .block();
    }

    public CryptoDTO findCryptoById(String id) {
        SingleCryptoResponse response = webClient.get()
                .uri(URI.create("https://api.coincap.io/v2/assets/" + id))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(SingleCryptoResponse.class)
                .block();

        return response.getData();
    }
}
