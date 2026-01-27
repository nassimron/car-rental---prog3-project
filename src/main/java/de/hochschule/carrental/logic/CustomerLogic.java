package de.hochschule.carrental.logic;

import de.hochschule.carrental.data.Customer;

public class CustomerLogic {

    public CustomerLogic(){
    }

    private Customer create(String ID, String Name, String DLNumber, String Email){
        return new Customer(ID,Name,DLNumber,Email);
    }

    private void delete(Customer customer){

    }

    private void saveToDb(){

    }


}
