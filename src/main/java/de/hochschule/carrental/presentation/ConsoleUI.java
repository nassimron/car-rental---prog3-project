package de.hochschule.carrental.presentation;

import de.hochschule.carrental.data.Car;
import de.hochschule.carrental.data.Contract;
import de.hochschule.carrental.data.Customer;
import de.hochschule.carrental.logic.CarLogic;
import de.hochschule.carrental.logic.ContractLogic;
import de.hochschule.carrental.logic.CustomerLogic;

import java.lang.classfile.instruction.ReturnInstruction;
import java.util.List;
import java.util.Scanner;

import static de.hochschule.carrental.presentation.UIHelper.isValidContractID;
import static de.hochschule.carrental.presentation.UIHelper.isValidInt;

public class ConsoleUI {

    Scanner scanner = new Scanner(System.in);


    //Erstellen von Instanzen der Logik-Klassen
    CarLogic carLogic = new CarLogic();
    CustomerLogic customerLogic = new CustomerLogic();
    ContractLogic contractLogic = new ContractLogic();



    //Startmethode des UI
    public void startUI(){
        mainPage();
    }

    //Hilfe: immer Zeile lesen und trimmen
    private String read() {
        return scanner.nextLine().trim();
    }

    //Hilfe "press enter"
    private void pressEnterToContinue(String text) {
        System.out.println(text);
        System.out.println("(Press Enter)");
        scanner.nextLine();
    }


    //Hauptseite des UI
    private void mainPage(){
        System.out.println("---------Car-Rental---------");
        System.out.println("0 - Exit");
        System.out.println("1 - Manage Cars");
        System.out.println("2 - Manage Customers");
        System.out.println("3 - Manage Contracts");
        System.out.println("Choose option 0-3: ");

        String input = read();

            if (UIHelper.isValidInt(input)) {
                switch (input) {
                    case "0":
                        return;
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
                        System.out.println("Not a valid option! Please choose 0-3");
                        break;
                }
            } else {
                System.out.println("Not a valid option! Please choose 0-3");
            }
            mainPage();
    }

    //Hauptseite für Autoverwaltung
    private void carsMainPage(){
        System.out.println("--------Manage-Cars--------");
        System.out.println("0 - Back to Main Page");
        System.out.println("1 - List Cars");
        System.out.println("2 - List Available Cars");
        System.out.println("3 - Add Car");
        System.out.println("Choose option 0-3: ");

        String input = read();

        if(isValidInt(input)){
            switch(input) {
                case "0":
                    mainPage();
                    return;
                case "1":
                    listCars(false);
                    break;
                case "2":
                    listCars(true);
                    break;
                case "3":
                    addCar();
                    return;
                default:
                    System.out.println("Not a valid option! Please choose 1-2");
                    break;
            }
        } else {
            System.out.println("Not a valid option! Please choose 0-3");
        }

        carsMainPage();
    }

    //Hauptseite für Kundenverwaltung
    private void customersMainPage(){
        System.out.println("------Manage-Customers-----");
        System.out.println("0 - Back to Main Page");
        System.out.println("1 - List Customers");
        System.out.println("2 - Add Customer");
        System.out.println("Choose option 0-2: ");

        String input = read();

        if(isValidInt(input)){
            switch(input) {
                case "0":
                    mainPage();
                    return;
                case "1":
                    listCustomers();
                    return;
                case "2":
                    addCustomer();
                    return;
                default:
                    System.out.println("Not a valid option! Please choose 0-2");
                    break;
            }
        } else {
            System.out.println("Not a valid option! Please choose 0-2");
        }

        customersMainPage();
    }

    //Hauptseite für Vertragsverwaltung
    private void contractsMainPage(){
        System.out.println("------Manage-Contracts-----");
        System.out.println("0 - Back to Main Page");
        System.out.println("1 - List Contracts");
        System.out.println("2 - Create Contract");
        System.out.println("3 - End Contract");
        System.out.println("Choose option 0-3: ");

        String input = read();

        if(isValidInt(input)){
            switch(input) {
                case "0":
                    mainPage();
                    return;
                case "1":
                    listContracts();
                    return;
                case "2":
                    setupContract();
                    return;
                case "3":
                    endContract();
                    return;
                default:
                    System.out.println("Not a valid option! Please choose 0-3");
                    break;
            }
        }
        else {
            System.out.println("Not a valid option! Please choose 0-3");
        }

        contractsMainPage();
    }

    //Auflisten aller Autos
    private void listCars(boolean onlyAvailable){
        System.out.println("---------Cars-List---------");
        List<Car> carList = carLogic.getAllCars();

        for(Car car : carList) {
            if (onlyAvailable) {
                if(car.getAvailability()) {
                    System.out.println(car.getID() + " | " + car.getBrand() + " | " + car.getModel() + " | " + car.getCategory() + " | " + car.getPrice() + " | " + car.getAvailability());
                }
            } else {
                System.out.println(car.getID() + " | " + car.getBrand() + " | " + car.getModel() + " | " + car.getCategory() + " | " + car.getPrice() + " | " + car.getAvailability());
            }
        }

        pressEnterToContinue("Return to Car-Management");
        carsMainPage();

    }

    //Anlegen neuer Autos
    private void addCar(){
        System.out.println("Please provide the Brand of the Car: ");
        String brand = read();

        while(brand.isEmpty()){
            System.out.println("Brand must not be empty!");
            System.out.println("Please provide the Brand of the Car: ");
            brand = read();
        }

        System.out.println("Please provide the Model of the Car: ");
        String model = read();

        while(model.isEmpty()){
            System.out.println("Model must not be empty!");
            System.out.println("Please provide the Model of the Car: ");
            model = read();
        }

        System.out.println("Please provide the Category of the Car: ");
        String category = read();

        while(category.isEmpty()){
            System.out.println("Category must not be empty!");
            System.out.println("Please provide the Category of the Car: ");
            category = read();
        }

        System.out.println("Please provide the Price of the Car: ");
        String priceString = read();

        while(!(isValidInt(priceString))){
            System.out.println("Price must be a valid Integer");
            System.out.println("Please provide the Price of the Car: ");
            priceString = read();
        }

        int price = Integer.parseInt(priceString);

        carLogic.create("test",brand,model,category,price,false);

        pressEnterToContinue("Car created. Return to Car-Management");
        carsMainPage();
    }

    //Auflisten aller Kunden
    private void listCustomers(){
        System.out.println("-------Customer-List-------");
        List<Customer> customerList = customerLogic.getAllCustomers();

        for(Customer customer : customerList){
            System.out.println(customer.getID() + " | " + customer.getName() + " | " + customer.getDLNumber() + " | " + customer.getEmail());
        }

        pressEnterToContinue("Car created. Return to Car-Management");
        customersMainPage();
    }

    //Anlegen neuer Kunden
    private void addCustomer(){
        System.out.println("Please provide Name of the Customer");
        String name = read();

        while(name.isEmpty()){
            System.out.println("Name must not be empty!");
            System.out.println("Please provide the Name of the Customer: ");
            name = read();
        }

        System.out.println("Please provide Driver License Number of the Customer");
        String dlNumber = read();

        while(dlNumber.trim().isEmpty()){
            System.out.println("Driver License Number must not be empty!");
            System.out.println("Please provide the Driver License Number of the Customer: ");
            dlNumber = read();
        }

        System.out.println("Please provide Email-Address of the Customer");
        String email = read();

        while(email.isEmpty()){
            System.out.println("Email-Address must not be empty!");
            System.out.println("Please provide the Email-Address of the Customer: ");
            email = read();
        }

        customerLogic.create("test",name,dlNumber,email);
        pressEnterToContinue("Customer created. Return to Customer-Management");
        customersMainPage();
    }

    //Auflisten aller Verträge
    private void listContracts(){
        System.out.println("--------Contracts----------");
        List<Contract> contractList = contractLogic.getAllContracts();

        for(Contract contract : contractList){
            System.out.println(contract.getID() + " | " + contract.getCustomer().getID() + " | " + contract.getCar().getID() + " | " + contract.getBeginDate() + " | " + contract.getEndDate() + " | " + contract.getPrice());
        }
        pressEnterToContinue("Return to Contract-Management");
        contractsMainPage();
    }

    //Anlegen eines neuen Vertrages
    private void setupContract(){
        System.out.println("Not implemented yet.");
        pressEnterToContinue("Return to Contract-Management");
        contractsMainPage();

    }

    //Beenden eines Vertrages
    private void endContract(){
        System.out.println("Please provide ID of the contract that should be ended: ");
        String input = read();

        if(isValidContractID(input)){
            if(contractLogic.getContractById(input) != null){
                contractLogic.finishContract(input);
                pressEnterToContinue("Contract ended. Return to Contract-Management");
                contractsMainPage();
            } else {
                System.out.println("The provided ID is not associated with a Contract!");
                endContract();
            }
        } else {
            System.out.println("The provided ID is not a contract ID!");
            endContract();
        }
    }
}
