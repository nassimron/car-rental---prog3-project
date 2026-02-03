package de.hochschule.carrental.presentation;

import de.hochschule.carrental.data.Car;
import de.hochschule.carrental.data.Contract;
import de.hochschule.carrental.data.Customer;
import de.hochschule.carrental.logic.CarLogic;
import de.hochschule.carrental.logic.ContractLogic;
import de.hochschule.carrental.logic.CustomerLogic;
import org.jooq.meta.derby.sys.Sys;

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


    //Hauptseite des UI
    private void mainPage(){
        System.out.println("---------Car-Rental---------");
        System.out.println("0 - Exit");
        System.out.println("1 - Manage Cars");
        System.out.println("2 - Manage Customers");
        System.out.println("3 - Manage Contracts");
        System.out.println("Choose option 0-3: ");

        String input = scanner.nextLine().trim();
            if (UIHelper.isValidInt(input)) {
                switch (input) {
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

    //Hauptseite für Autoverwaltung
    private void carsMainPage(){
        System.out.println("--------Manage-Cars--------");
        System.out.println("0 - Back to Main Page");
        System.out.println("1 - List Cars");
        System.out.println("2 - List Available Cars");
        System.out.println("3 - Add Car");
        System.out.println("Choose option 0-3: ");

        String input = scanner.nextLine().trim();

        if(isValidInt(input)){
            switch(input) {
                case "0":
                    mainPage();
                    break;
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
                    System.out.println("Not a valid option! Please choose 1-2");
                    carsMainPage();
            }
        }
    }

    //Hauptseite für Kundenverwaltung
    private void customersMainPage(){
        System.out.println("------Manage-Customers-----");
        System.out.println("0 - Back to Main Page");
        System.out.println("1 - List Customers");
        System.out.println("2 - Add Customer");
        System.out.println("Choose option 0-2: ");

        String input = scanner.nextLine().trim();

        if(isValidInt(input)){
            switch(input) {
                case "0":
                    mainPage();
                    break;
                case "1":
                    listCustomers();
                    break;
                case "2":
                    addCustomer();
                    break;
                default:
                    System.out.println("Not a valid option! Please choose 1-2");
                    customersMainPage();
            }
        }

    }

    //Hauptseite für Vertragsverwaltung
    private void contractsMainPage(){
        System.out.println("------Manage-Contracts-----");
        System.out.println("0 - Back to Main Page");
        System.out.println("1 - List Contracts");
        System.out.println("2 - Create Contract");
        System.out.println("3 - End Contract");
        System.out.println("Choose option 0-3: ");

        String input = scanner.nextLine().trim();

        if(isValidInt(input)){
            switch(input) {
                case "0":
                    mainPage();
                    break;
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
                    System.out.println("Not a valid option! Please choose 1-3");
                    contractsMainPage();
            }
        }

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

        System.out.println("Press any button to return to Car-Management");
        String input = scanner.next();
        carsMainPage();
    }

    //Anlegen neuer Autos
    private void addCar(){
        System.out.println("Please provide the Brand of the Car: ");
        String brand = scanner.next();

        while(brand.trim().isEmpty()){
            System.out.println("Brand must not be empty!");
            System.out.println("Please provide the Brand of the Car: ");
            brand = scanner.next();
        }

        System.out.println("Please provide the Model of the Car: ");
        String model = scanner.next();

        while(model.trim().isEmpty()){
            System.out.println("Model must not be empty!");
            System.out.println("Please provide the Model of the Car: ");
            model = scanner.next();
        }

        System.out.println("Please provide the Category of the Car: ");
        String category = scanner.next();

        while(category.trim().isEmpty()){
            System.out.println("Category must not be empty!");
            System.out.println("Please provide the Category of the Car: ");
            category = scanner.next();
        }

        System.out.println("Please provide the Price of the Car: ");
        String priceString = scanner.next();

        while(!(isValidInt(priceString.trim()))){
            System.out.println("Price must be a valid Integer");
            System.out.println("Please provide the Price of the Car: ");
            priceString = scanner.next();
        }

        int price = Integer.parseInt(priceString);

        carLogic.create("test",brand,model,category,price,false);

        carsMainPage();
    }

    //Auflisten aller Kunden
    private void listCustomers(){
        System.out.println("-------Customer-List-------");
        List<Customer> customerList = customerLogic.getAllCustomers();

        for(Customer customer : customerList){
            System.out.println(customer.getID() + " | " + customer.getName() + " | " + customer.getDLNumber() + " | " + customer.getEmail());
        }

        System.out.println("Press any button to return to Customer-Management");
        String input = scanner.next();
        customersMainPage();
    }

    //Anlegen neuer Kunden
    private void addCustomer(){
        System.out.println("Please provide Name of the Customer");
        String name = scanner.next();

        while(name.trim().isEmpty()){
            System.out.println("Name must not be empty!");
            System.out.println("Please provide the Name of the Customer: ");
            name = scanner.next();
        }

        System.out.println("Please provide Driver License Number of the Customer");
        String dlNumber = scanner.next();

        while(dlNumber.trim().isEmpty()){
            System.out.println("Driver License Number must not be empty!");
            System.out.println("Please provide the Driver License Number of the Customer: ");
            dlNumber = scanner.next();
        }

        System.out.println("Please provide Email-Address of the Customer");
        String email = scanner.next();

        while(email.trim().isEmpty()){
            System.out.println("Email-Address must not be empty!");
            System.out.println("Please provide the Email-Address of the Customer: ");
            email = scanner.next();
        }

        customerLogic.create("test",name,dlNumber,email);
    }

    //Auflisten aller Verträge
    private void listContracts(){
        List<Contract> contractList = contractLogic.getAllContracts();

        for(Contract contract : contractList){
            System.out.println(contract.getID() + " | " + contract.getCustomer().getID() + " | " + contract.getCar().getID() + " | " + contract.getBeginDate() + " | " + contract.getEndDate() + " | " + contract.getPrice());
        }
    }

    //Anlegen eines neuen Vertrages
    private void setupContract(){

    }

    //Beenden eines Vertrages
    private void endContract(){
        System.out.println("Please provide ID of the contract that should be ended: ");
        String input = scanner.nextLine();

        if(isValidContractID(input)){
            if(contractLogic.getContractById(input) != null){
                contractLogic.finishContract(input);
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
