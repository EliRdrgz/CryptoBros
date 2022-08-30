package com.ironhack.doublercryptobros.controller;

import com.ironhack.doublercryptobros.dto.CryptoDTO;
import com.ironhack.doublercryptobros.dto.SingleCryptoResponse;
import com.ironhack.doublercryptobros.service.CryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cryptos")
public class CryptoController {
    @Autowired
    CryptoService cryptoService;

    @GetMapping
    public void findCryptos(){
        cryptoService.findAllCryptos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CryptoDTO> findCryptos(@PathVariable String id){
        return ResponseEntity.ok(cryptoService.findCryptoById(id));
    }
}
