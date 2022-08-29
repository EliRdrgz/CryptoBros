package com.ironhack.doublercryptobros;

import com.ironhack.doublercryptobros.menu.Menu;
import com.ironhack.doublercryptobros.service.CryptoService;
import com.ironhack.doublercryptobros.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DoubleRCryptoBrosApplication implements CommandLineRunner {

    @Autowired
    CryptoService cryptoService;
    @Autowired
    UserService userService;


    public static void main(String[] args) {
        SpringApplication.run(DoubleRCryptoBrosApplication.class, args);
    }
    @Override
    public void run(String[] args) throws InterruptedException {
        Menu menu = new Menu(cryptoService,userService);
        menu.start();
    }

}
