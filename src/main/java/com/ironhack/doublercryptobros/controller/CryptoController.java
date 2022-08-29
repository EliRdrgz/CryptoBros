package com.ironhack.doublercryptobros.controller;

import com.ironhack.doublercryptobros.model.User;
import com.ironhack.doublercryptobros.service.CryptoService;
import org.springframework.beans.factory.annotation.Autowired;
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


}
