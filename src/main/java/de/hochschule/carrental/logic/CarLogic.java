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

    public Car create(String id, String brand, String model, String category, int price, boolean availability){

        if (id == null || id.isBlank())
            throw new IllegalArgumentException("Car ID must not be empty");

        if (brand == null || brand.isBlank())
            throw new IllegalArgumentException("Brand must not be empty");

        if (model == null || model.isBlank())
            throw new IllegalArgumentException("Model must not be empty");

        if (price <= 0)
            throw new IllegalArgumentException("Price must be greater than 0");

        if (getCarById(id) != null)
            throw new IllegalStateException("Car with ID " + id + " already exists");


        Car car = new Car(id, brand, model, category, price, availability);
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

