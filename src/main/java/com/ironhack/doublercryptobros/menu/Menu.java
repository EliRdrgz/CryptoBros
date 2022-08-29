package com.ironhack.doublercryptobros.menu;



import com.ironhack.doublercryptobros.console.ConsoleBuilder;
import com.ironhack.doublercryptobros.service.CryptoService;
import com.ironhack.doublercryptobros.service.UserService;
import org.springframework.stereotype.Component;

import java.io.Console;

import java.util.*;

@Component
public class Menu {

    Scanner scanner;
    ConsoleBuilder consoleBuilder;
    CryptoService cryptoService;
    UserService userService;

    Console console;

    private String option;

    public Menu(CryptoService cryptoService, UserService userService) {
        this.scanner = new Scanner(System.in);
        this.consoleBuilder = new ConsoleBuilder(scanner);
        this.cryptoService = cryptoService;
        this.userService = userService;
    }

    public void start() throws InterruptedException {
        boolean exit = false;

        while (!exit) {
            List<String> options = Arrays.asList("Log In", "Sign In", "About Us", "Exit");
            option = consoleBuilder.listConsoleInput("Welcome to CryptoBros Application. What would you like to do?", options);
            switch (option) {
                case "LOG IN" -> logIn();
                case "SIGN IN" -> signIn();
                case "ABOUT US" -> System.out.println("Somos los crypto bro");
                case "EXIT" -> exit = true;
                default -> System.out.println("Choose a correct option.");
            }
        }
    }

    private void logIn() {
        System.out.println("Enter you username: ");
        String user = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = String.valueOf(console.readPassword("Enter your password: "));
        userService.authenticate(user, String.valueOf(password));
    }



    private void signIn() {
        System.out.println("Enter you username: ");
        String user = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = String.valueOf(console.readPassword("Enter your password: "));
        userService.register(user,password);

    }

}
