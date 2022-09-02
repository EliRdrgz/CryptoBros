package com.ironhack.doublercryptobros.menu;



import com.ironhack.doublercryptobros.DoubleRCryptoBrosApplication;
import com.ironhack.doublercryptobros.console.ConsoleBuilder;
import com.ironhack.doublercryptobros.dto.CryptoDTO;
import com.ironhack.doublercryptobros.model.CryptoFav;
import com.ironhack.doublercryptobros.service.CryptoService;
import com.ironhack.doublercryptobros.service.UserService;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.Console;
import java.util.*;

@Component
@Getter
@Setter
public class Menu {

    Scanner scanner;
    Console console = System.console();
    ConsoleBuilder consoleBuilder;
    CryptoService cryptoService;
    UserService userService;

    private Long userId;
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
            option = consoleBuilder.listConsoleInput("⚡⚡⚡ \uD83D\uDCC8\uD83D\uDCC9 Welcome to CryptoBros Application. What would you like to do?\uD83D\uDCC8\uD83D\uDCC9 ⚡⚡⚡", options);
            switch (option) {
                case "\uD83D\uDD13 LOG IN" -> logIn();
                case "\uD83C\uDD95 SIGN UP" -> signUp();
                case "\uD83D\uDC65 ABOUT US" -> System.out.println("Somos los crypto bro");
                case "\uD83D\uDD19 EXIT" -> System.exit(0);
                default -> System.out.println("Choose a correct option.");
            }
        }
    }

    private void signUp() throws InterruptedException {
        System.out.println("Enter you username: ");
        String username = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = getPassword();
        System.out.println("Enter your password again: ");
        String password1 = getPassword();

        if (password.equals(password1)) {
            try {
                var user = userService.register(username, password);
                System.out.println("User successfully registered");
                setUserId(user.getId());
                loginMenu();
            } catch (Exception error) {
                System.out.println("Invalid username, try again");
            }
        } else {
            System.out.println("The passwords does not equals.");
        }
    }

    private String getPassword() {
        String password;
        if (console != null) {
            password = new String(console.readPassword());
        } else {
            password = scanner.nextLine();
        }
        return password;
    }


    private void logIn() throws InterruptedException {
        System.out.println("Enter your username: ");
        String user = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password;
        if (console != null) {
            password = new String(console.readPassword());
        } else {
            password = scanner.nextLine();
        }
        var authenticationResponse = userService.authenticate(user, password);
        if (authenticationResponse.getAuthorized()) {
            setUserId(authenticationResponse.getUser().getId());
            System.out.println("Login successfully! Welcome, " + authenticationResponse.getUser().getUsername());
            loginMenu();
        } else {
            System.out.println("Authentication failed. Try again");
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
        try{
            CryptoDTO cryptoById = cryptoService.findCryptoById(id);
            System.out.println(cryptoById);
            while (!exit) {
                List<String> options = new ArrayList<>();
                var favs = userService.checkFavs(userId);
                boolean isFav = false;
                if (favs.size() > 0) {
                    for (CryptoFav fav : favs) {
                        if (fav.getId().equals(cryptoById.getId())) {
                            isFav = true;
                            break;
                        }
                    }
                    if (isFav) options.add(("Remove from favs"));
                    else options.add("Add to favs");
                } else {
                    options.add("Add to favs");
                }
                options.add("Back");
                option = consoleBuilder.listConsoleInput("Choose what you want to do: ", options);
                switch (option) {
                    case "ADD TO FAVS" -> addToFavs(id);
                    case "REMOVE FROM FAVS" -> removeFromFavs(id);
                    case "BACK" -> exit = true;
                    default -> System.out.println("Choose a correct option.");
                }
            }
        } catch (Error err) {
            System.out.println("Unable to found this coin. Please try again");
            findCryptoByName();
        }
    }

    private void removeFromFavs(String id) {
        var userUpdated = userService.removeFav(userId, id);
        System.out.println("List of favs: "+ "\n" + userUpdated.getFavs());
    }

    private void addToFavs(String id) {
        var userUpdated = userService.addToFavs(userId, id);
        System.out.println("List of favs: " + "\n" + userUpdated.getFavs());
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
}

