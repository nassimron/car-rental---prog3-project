package de.hochschule.carrental.presentation;

import de.hochschule.carrental.data.Car;
import de.hochschule.carrental.data.Contract;
import de.hochschule.carrental.data.Customer;
import de.hochschule.carrental.logic.CarLogic;
import de.hochschule.carrental.logic.ContractLogic;
import de.hochschule.carrental.logic.CustomerLogic;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;

import static de.hochschule.carrental.presentation.UIHelper.*;

public class ConsoleUI {

    Scanner scanner = new Scanner(System.in);

    ContractLogic contractLogic = new ContractLogic();
    CarLogic carLogic = contractLogic.getCarLogic();
    CustomerLogic customerLogic = contractLogic.getCustomerLogic();


    public void startUI(){
        mainPage();
    }

    // ===== Helper =====

    private String read() {
        return scanner.nextLine().trim();
    }

    private void pressEnterToContinue(String text) {
        System.out.println(text);
        System.out.println("(Press Enter)");
        scanner.nextLine();
    }

    // ===== MAIN =====

    private void mainPage(){

        while (true) {

            System.out.println("---------Car-Rental---------");
            System.out.println("0 - Exit");
            System.out.println("1 - Manage Cars");
            System.out.println("2 - Manage Customers");
            System.out.println("3 - Manage Contracts");
            System.out.print("Choose option 0-3: ");

            String input = read();

            if(isValidInt(input)) {

                switch (input) {

                    case "0":
                        System.out.println("Program closed");
                        System.exit(0);

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
                        System.out.println("Not a valid option!");
                }

            } else {
                System.out.println("Not a valid option!");
            }
        }
    }

    // ===== CARS =====

    private void carsMainPage(){

        while (true) {

            System.out.println("--------Manage-Cars--------");
            System.out.println("0 - Back to Main Page");
            System.out.println("1 - List Cars");
            System.out.println("2 - List Available Cars");
            System.out.println("3 - Add Car");
            System.out.print("Choose option 0-3: ");

            String input = read();

            if(isValidInt(input)){

                switch(input) {

                    case "0":
                        return;

                    case "1":
                        listCars(false);
                        break;

                    case "2":
                        listCars(true);
                        break;

                    case "3":
                        addCar();
                        break;

                    default:
                        System.out.println("Not a valid option!");
                }

            } else {
                System.out.println("Not a valid option!");
            }
        }
    }

    private void listCars(boolean onlyAvailable){

        System.out.println("---------Cars-List---------");

        List<Car> carList = carLogic.getAllCars();

        for(Car car : carList) {

            if(!onlyAvailable || car.getAvailability()) {

                System.out.println(
                        car.getID() + " | " +
                                car.getBrand() + " | " +
                                car.getModel() + " | " +
                                car.getCategory() + " | " +
                                car.getPrice() + " | " +
                                car.getAvailability()
                );
            }
        }

        pressEnterToContinue("Return to Car-Management");
    }

    private void addCar(){

        System.out.print("Brand: ");
        String brand = read();

        while(brand.isEmpty()){
            System.out.print("Brand must not be empty: ");
            brand = read();
        }

        System.out.print("Model: ");
        String model = read();

        while(model.isEmpty()){
            System.out.print("Model must not be empty: ");
            model = read();
        }

        System.out.print("Category: ");
        String category = read();

        while(category.isEmpty()){
            System.out.print("Category must not be empty: ");
            category = read();
        }

        System.out.print("Price: ");
        String priceString = read();

        while(!isValidInt(priceString)){
            System.out.print("Enter valid price: ");
            priceString = read();
        }

        int price = Integer.parseInt(priceString);

        carLogic.create(brand, model, category, price, true);


        pressEnterToContinue("Car created.");
    }

    // ===== CUSTOMERS =====

    private void customersMainPage(){

        while (true) {

            System.out.println("------Manage-Customers-----");
            System.out.println("0 - Back to Main Page");
            System.out.println("1 - List Customers");
            System.out.println("2 - Add Customer");
            System.out.print("Choose option 0-2: ");

            String input = read();

            if(isValidInt(input)){

                switch(input){

                    case "0":
                        return;

                    case "1":
                        listCustomers();
                        break;

                    case "2":
                        addCustomer();
                        break;

                    default:
                        System.out.println("Not a valid option!");
                }

            } else {
                System.out.println("Not a valid option!");
            }
        }
    }

    private void listCustomers(){

        System.out.println("-------Customer-List-------");

        List<Customer> customerList = customerLogic.getAllCustomers();

        for(Customer customer : customerList){

            System.out.println(
                    customer.getID() + " | " +
                            customer.getName() + " | " +
                            customer.getDLNumber() + " | " +
                            customer.getEmail()
            );
        }

        pressEnterToContinue("Return to Customer-Management");
    }

    private void addCustomer(){

        System.out.print("Name: ");
        String name = read();

        while(name.isEmpty()){
            System.out.print("Name must not be empty: ");
            name = read();
        }

        System.out.print("Driver License: ");
        String dlNumber = read();

        while(dlNumber.isEmpty()){
            System.out.print("Driver License must not be empty: ");
            dlNumber = read();
        }

        System.out.print("Email: ");
        String email = read();

        while(email.isEmpty()){
            System.out.print("Email must not be empty: ");
            email = read();
        }

        customerLogic.create(name, dlNumber, email);

        pressEnterToContinue("Customer created.");
    }

    // ===== CONTRACTS =====

    private void contractsMainPage(){

        while (true) {

            System.out.println("------Manage-Contracts-----");
            System.out.println("0 - Back to Main Page");
            System.out.println("1 - List Contracts");
            System.out.println("2 - Create Contract");
            System.out.println("3 - End Contract");
            System.out.print("Choose option 0-3: ");

            String input = read();

            if(isValidInt(input)){

                switch(input){

                    case "0":
                        return;

                    case "1":
                        listContracts();
                        break;

                    case "2":
                        setupContract();
                        break;

                    case "3":
                        endContract();
                        break;

                    default:
                        System.out.println("Not a valid option!");
                }

            } else {
                System.out.println("Not a valid option!");
            }
        }
    }

    private void listContracts(){

        System.out.println("--------Contracts----------");

        List<Contract> contractList = contractLogic.getAllContracts();

        for(Contract contract : contractList){

            System.out.println(
                    contract.getID() + " | " +
                            contract.getCustomer().getID() + " | " +
                            contract.getCar().getID() + " | " +
                            contract.getBeginDate() + " | " +
                            contract.getEndDate() + " | " +
                            contract.getPrice()
            );
        }

        pressEnterToContinue("Return to Contract-Management");
    }

    private void setupContract(){

        System.out.println("Please provide a Customer ID to assign the Contract to: ");

        String customerID = read();

        while(!isValidCustomerID(customerID) || customerLogic.getCustomerById(customerID) == null){
            System.out.println("The provided ID is not a valid Customer ID! ");
            System.out.println("Please provide a valid Customer ID: ");
            customerID = read();
        }

        System.out.println("Please provide a Car ID to assign a Car to the Contract: ");

        String carID = read();

        while(true){
            if(!isValidCarID(carID) || carLogic.getCarById(carID) == null) {
                System.out.println("The provided ID is not a valid Car ID! ");
                System.out.println("Please provide a valid Car ID: ");
            } else {
                if(carLogic.getCarById(carID).getAvailability()) {
                    break;
                }
            }
            System.out.println("The provided Car is not available right now!");
            System.out.println("Please provide a Car ID of an available Car: ");
            carID = read();
        }

        LocalDate beginDate = null; //Definierung von Datum-Variablen zur Nutzung außerhalb der while-Schleife
        LocalDate endDate = null;

        //while-Schleife zur Überprüfung von nicht negativer Anzahl an Tagen
        while(true) {
            System.out.println("Please provide the beginning Date of the contract: ");
            String beginDateString = read();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            while (!isValidDate(beginDateString)) {
                System.out.println("The provided Date is not in a valid format!");
                System.out.println("Please provide a date in the correct format (yyyy-MM-dd): ");
                beginDateString = read();
            }

            beginDate = LocalDate.parse(beginDateString, formatter);

            System.out.println("Please provide the ending Date of the contract: ");
            String endDateString = read();

            while (!isValidDate(endDateString)) {
                System.out.println("The provided Date is not in a valid format!");
                System.out.println("Please provide a date in the correct format (yyyy-MM-dd): ");
                endDateString = read();
            }

           endDate = LocalDate.parse(endDateString, formatter);

            if(ChronoUnit.DAYS.between(beginDate,endDate) + 1 <= 0) {
                System.out.println("The provided beginning and ending dates result in a negative amount of days");
            } else {
                break;
            }
        }

        contractLogic.createContract(customerLogic.getCustomerById(customerID),carLogic.getCarById(carID),beginDate,endDate);

        pressEnterToContinue("Return to Contract-Management");
    }

    private void endContract(){

        System.out.print("Contract ID: ");
        String input = read();

        if(isValidContractID(input)){

            if(contractLogic.getContractById(input) != null){

                contractLogic.finishContract(input);
                pressEnterToContinue("Contract ended.");

            } else {
                System.out.println("Contract not found.");
            }

        } else {
            System.out.println("Invalid contract ID.");
        }
    }
}
