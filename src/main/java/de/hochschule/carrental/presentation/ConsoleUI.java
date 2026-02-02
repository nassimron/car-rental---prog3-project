package de.hochschule.carrental.presentation;

import org.jooq.meta.derby.sys.Sys;

import java.util.Scanner;

public class ConsoleUI {

    Scanner scanner = new Scanner(System.in);

    public void startUI(){
        mainPage();
    }

    public void mainPage(){
        System.out.println("---------Car-Rental---------");
        System.out.println("0 - Exit");
        System.out.println("1 - Manage Cars");
        System.out.println("2 - Manage Customers");
        System.out.println("3 - Manage Contracts");
        System.out.println("Choose option 0-3: ");

        String input = scanner.next();

        if(isInt(input)){
            switch(input) {
                case "0":
                    break;
                case "1":
                    carsMainPage();
                    break;
                case "2":
                    customersMainPage();
                    break;
                case "3":
                    contractsMainPage();
                    break;
                default:
                    System.out.println("Not a valid option! Please choose 1-3");
                    mainPage();
                    break;
            }
        } else {
            System.out.println("Not a valid option! Please choose 1-3");
            mainPage();
        }
    }

    public void carsMainPage(){
        System.out.println("--------Manage-Cars--------");
        System.out.println("0 - Back to Main Page");
        System.out.println("1 - List Cars");
        System.out.println("2 - Add Car");
        System.out.println("Choose option 0-2: ");

        String input = scanner.next();

        if(isInt(input)){
            switch(input) {
                case "0":
                    mainPage();
                    break;
                case "1":
                    break;
                case "2":
                    break;
                default:
                    System.out.println("Not a valid option! Please choose 1-2");
                    carsMainPage();
            }
        }
    }

    public void customersMainPage(){
        System.out.println("------Manage-Customers-----");
        System.out.println("0 - Back to Main Page");
        System.out.println("1 - List Customers");
        System.out.println("2 - Add Customer");
        System.out.println("Choose option 0-2: ");

        String input = scanner.next();

        if(isInt(input)){
            switch(input) {
                case "0":
                    mainPage();
                    break;
                case "1":
                    break;
                case "2":
                    break;
                default:
                    System.out.println("Not a valid option! Please choose 1-2");
                    customersMainPage();
            }
        }

    }

    public void contractsMainPage(){
        System.out.println("------Manage-Contracts-----");
        System.out.println("0 - Back to Main Page");
        System.out.println("1 - List Contracts");
        System.out.println("2 - Create Contract");
        System.out.println("3 - End Contract");
        System.out.println("Choose option 0-3: ");

        String input = scanner.next();

        if(isInt(input)){
            switch(input) {
                case "0":
                    mainPage();
                    break;
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    break;
                default:
                    System.out.println("Not a valid option! Please choose 1-3");
                    contractsMainPage();
            }
        }

    }


    public boolean isInt(String input){
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e){
            return false;
        }
        return true;
    }
}
