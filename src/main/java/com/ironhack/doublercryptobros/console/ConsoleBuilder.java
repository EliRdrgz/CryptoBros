package com.ironhack.doublercryptobros.console;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;
import java.util.Scanner;


public class ConsoleBuilder {
        Scanner scanner;

        public ConsoleBuilder(Scanner sc) {
            this.scanner = sc;
        }

        public String textConsoleInput( String message) {
            String result = "";
            boolean isValid = false;
            while(!isValid){
                System.out.println(message);
                result = scanner.nextLine();
                if(result.trim().length() > 0){
                    isValid = true;
                }
            }
            return result.trim();
        }

        public Long numberConsoleInput(String message,Long[] options) {
            int result = 0;
            boolean isValid = false;
            while(!isValid){
                System.out.println(message);
                String choice = scanner.nextLine();
                try{
                    for(Long option : options){
                        if(option == Integer.parseInt(choice)){
                            isValid = true;
                            result  = Integer.parseInt(choice);
                        }
                    }
                }catch (NumberFormatException e){

                }

                if(!isValid){
                    System.out.println("Please select a valid option");            }

            }
            return (long)result;
        }

        public String emailConsoleInput(String message) {
            String result = "";
            boolean isValid = false;
            while(!isValid){
                System.out.println(message);
                String choice = scanner.nextLine();
                if(choice.contains("@")&&choice.contains(".")&&choice.length()>0 && choice.length()<=50){
                    isValid = true;
                    result  = choice;
                }
                if(!isValid){
                    System.out.println("Please select a valid email address");            }

            }
            return result;
        }

        public int numberConsoleInput(String message,int min, int max) {
            int result = 0;
            boolean isValid = false;
            while(!isValid){
                System.out.println(message);
                String choice = scanner.nextLine();
                try{
                    result = Integer.parseInt(choice);
                    if(result >= min && result <= max){
                        isValid = true;
                    }
                    if(!isValid){
                        System.out.println("Please select a valid option");
                    }
                }catch (NumberFormatException e){
                    System.out.println("Please select a valid option");
                }

            }
            return result;
        }
        public int numberConsoleInput(String message) {
            int result = 0;
            boolean isValid = false;
            while(!isValid){
                System.out.println(message);
                String choice = scanner.nextLine();
                try{
                    result = Integer.parseInt(choice);
                    if(result>0){
                        isValid = true;
                    }

                    if(!isValid){
                        System.out.println("Please select a valid option");
                    }
                }catch (NumberFormatException e){
                    System.out.println("Please select a valid option");
                }

            }
            return result;
        }


        public String listConsoleInput( String title, List<String> options) {
            String inputData ;
            String result = "";
            boolean isValid = false;
            while (!isValid) {
                System.out.println(title);
                for (int i = 0; i < options.size(); i++) {
                    System.out.println(i + 1 + " - " + options.get(i));
                }
                System.out.println("Choose an option: ");
                inputData = scanner.nextLine();

                try {
                    int choice = Integer.parseInt(inputData);
                    if (choice > 0 && choice <= options.size()) {
                        isValid = true;
                        result = options.get(choice - 1);
                    } else {
                        System.out.println("Choose a correct option.");
                    }
                } catch (NumberFormatException e) {
                    for(String option : options) {
                        if(option.equalsIgnoreCase(inputData)) {
                            isValid = true;
                            result = option;
                        }

                    }
                    if(!isValid) {
                        System.out.println("Choose a correct option.");
                    }
                }
            }
            return result.toUpperCase();

        }


}


