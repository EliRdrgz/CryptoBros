package com.ironhack.doublercryptobros.menu;



import com.ironhack.doublercryptobros.console.ConsoleBuilder;
import com.ironhack.doublercryptobros.console.ConsoleColors;
import com.ironhack.doublercryptobros.dto.CryptoDTO;
import com.ironhack.doublercryptobros.dto.ListCryptoResponse;
import com.ironhack.doublercryptobros.dto.UserDTO;
import com.ironhack.doublercryptobros.service.CryptoService;
import com.ironhack.doublercryptobros.service.UserService;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Getter
@Setter
public class Menu {

    Scanner scanner;
    ConsoleBuilder consoleBuilder;
    CryptoService cryptoService;
    UserService userService;

    private UserDTO userLoggedIn;

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
            List<String> options = Arrays.asList("\uD83D\uDD13 Log In", "\uD83C\uDD95 Sign Up", "\uD83D\uDC65 About Us", "\uD83D\uDD19 Exit");
            option = consoleBuilder.listConsoleInput(ConsoleColors.BLUE_BACKGROUND_BRIGHT + ConsoleColors.WHITE_BOLD_BRIGHT +"⚡⚡⚡ \uD83D\uDCC8\uD83D\uDCC9 Welcome to CryptoBros Application. What would you like to do?\uD83D\uDCC8\uD83D\uDCC9 ⚡⚡⚡", options);
            switch (option) {
                case "\uD83D\uDD13 LOG IN" -> logIn();
                case "\uD83C\uDD95 SIGN UP" -> signUp();
                case "\uD83D\uDC65 ABOUT US" -> System.out.println("Somos los crypto bro");
                case "\uD83D\uDD19 EXIT" -> exit = true;
                default -> System.out.println("Choose a correct option.");
            }
        }
    }

    private void logIn() throws InterruptedException {
        System.out.println("Enter your username: ");
        String user = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();
        var authenticationResponse = userService.authenticate(user, password);
        if(authenticationResponse.getAuthorized()) {
            setUserLoggedIn(authenticationResponse.getUser());
            System.out.println("Login successfully! Welcome, " + authenticationResponse.getUser().getUsername());
            loginMenu();
        } else {
            System.out.println("Authentication failed. Try again");
            logIn();
        }
    }

    public void loginMenu() throws InterruptedException {
        boolean exit = false;

        while (!exit) {
            List<String> options = Arrays.asList("Show all cryptos", "Search by name", "Exit");
            option = consoleBuilder.listConsoleInput("Choose what you want to do: ", options);
            switch (option) {
                case "SHOW ALL CRYPTOS" -> findCryptos();
                case "SEARCH BY NAME" -> findCryptoByName();
                case "EXIT" -> exit = true;
                default -> System.out.println("Choose a correct option.");
            }
        }
    }

    private void findCryptoByName() {
        boolean exit = false;

        System.out.println("Which crypto do you want to see?");
        String id = scanner.nextLine();
        System.out.println("Loading...");
        System.out.println("------------------------");
        CryptoDTO cryptoById = cryptoService.findCryptoById(id);
        System.out.println(cryptoById);

        while (!exit) {
            List<String> options = Arrays.asList("Add to favs", "Remove from favs", "Back");
            option = consoleBuilder.listConsoleInput("Choose what you want to do: ", options);
            switch (option) {
                case "ADD TO FAVS" -> addToFavs();
                case "REMOVE FROM FAVS" -> removeFromFavs();
                case "BACK" -> exit = true;
                default -> System.out.println("Choose a correct option.");
            }
        }
    }

    private void removeFromFavs() {
    }

    private void addToFavs() {

    }

    private void findCryptos() {
        System.out.println("Loading...");
        List<CryptoDTO> list = cryptoService.findAllCryptos().getData();
        System.out.println("----------------------------------------");
        System.out.println("------------------ALL-------------------");
        System.out.println("----------------CRYPTOS-----------------");
        System.out.println("-------------------₿--------------------");
        System.out.println("----------------------------------------");


        //Añadir título a la lista y formatear price Usd y market cap a 2 decimales.
        list.forEach((System.out::println));
//      System.out.println(cryptoService.findAllCryptos().getData());
    }

    private void signUp() throws InterruptedException {
        //Console console = System.console();
        System.out.println("Enter you username: ");
        String username = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();
        System.out.println("Enter your password again: ");
        String password1 = scanner.nextLine();
        if (password.equals(password1)) {
            try {
                userService.register(username, password);
                System.out.println("User successfully registered");
                loginMenu();
            } catch (Exception error) {
                System.out.println("Invalid username, try again");
            }
            //String password = new String(System.console().readPassword("Enter your password: "));
            //char[] password = console.readPassword("%s", "Enter your password");
            //String password = new jline.ConsoleReader().readLine(new Character('*'));

        } else {
            System.out.println("The passwords does not equals.");
        }

    }

}
