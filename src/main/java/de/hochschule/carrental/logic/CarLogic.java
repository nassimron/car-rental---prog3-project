package de.hochschule.carrental.logic;

import de.hochschule.carrental.data.Car;
import org.jooq.impl.QOM;

import java.util.ArrayList;
import java.util.List;

public class CarLogic {

    private List<Car> cars;

    public CarLogic(){
        this.cars = new ArrayList<>();
    }

    public Car create(String ID, String Brand, String Model, String Category, int Price, boolean Availability){

        Car car = new Car(ID, Brand, Model, Category, Price, Availability);
        cars.add(car);
        return car;
    }

    public void delete(String id){
        cars.removeIf(car -> car.getID().equals(id));
    }

    public List<Car> getAllCars() {
        return cars;
    }

    public Car getCarById(String id) {
        for(Car car : cars) {
            if (car.getID().equals(id)) {
                return car;
            }
        }
        return null;
    }

    private void saveToDb(){

    }

}

