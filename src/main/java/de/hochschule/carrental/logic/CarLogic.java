package de.hochschule.carrental.logic;

import de.hochschule.carrental.data.Car;

public class CarLogic {

    public CarLogic(){
    }

    private Car create(String ID, String Brand, String Model, String Category, float Price, boolean Availability){
        return new Car(ID,Brand,Model,Category,Price,Availability);
    }

    private void delete(Car car){

    }

    private void saveToDb(){

    }


}
