package de.hochschule.carrental.logic;

import de.hochschule.carrental.data.Car;
import de.hochschule.carrental.data.Contract;
import de.hochschule.carrental.data.Customer;

public class ContractLogic extends Contract{

    public ContractLogic(){
        super();

    }

    public Contract create(String ID, Customer customer, Car car, String BeginDate, String EndDate, float Price){
        car.setAvailability(false);
        return new Contract(ID,customer,car,BeginDate,EndDate,Price);
    }

    public void delete(Contract contract){

    }

    public void saveToDb(){

    }

    public void returnCar(Contract contract){ //Availability von Car wird auf "1" gesetzt
        contract.getCar().setAvailability(true);
    }


}
