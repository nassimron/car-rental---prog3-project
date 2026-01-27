package de.hochschule.carrental;

import de.hochschule.carrental.data.Car;
import de.hochschule.carrental.data.Contract;
import de.hochschule.carrental.data.Customer;
import de.hochschule.carrental.logic.ContractLogic;


import java.util.ArrayList;

public class TestLogic {

    ArrayList<Car> carList = new ArrayList<Car>();
    Car car = new Car("A1", "VW", "Polo","Kleinwagen", 40, true);
    Customer customer = new Customer("K1","Max Mustermann", "L24662546724762", "max.mustermann@gmail.com");
    ContractLogic clogic = new ContractLogic();
    Contract contract = clogic.create("V1", customer, car, "22-01-2026","23-01-2026",40);

    public void printAvailability(){
        carList.add(car);
        System.out.println(car.getAvailability());
        clogic.returnCar(contract);
        System.out.println(car.getAvailability());
    }
}