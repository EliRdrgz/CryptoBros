package com.ironhack.doublercryptobros.service;

import com.ironhack.doublercryptobros.dto.CryptoDTO;
import com.ironhack.doublercryptobros.repository.CryptoFavRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class CryptoService {

    @Autowired
    CryptoFavRepository cryptoFavRepository;

    private WebClient webClient = WebClient.create("https://api.coincap.io/v2");

    public List<CryptoDTO> findAllCryptos() {
        Mono<List<CryptoDTO>> response = webClient.get()
                .uri("/assets")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<CryptoDTO>>() {});

        List<CryptoDTO> list = response.block();

        System.out.println(list);
        return null;
    }

}
