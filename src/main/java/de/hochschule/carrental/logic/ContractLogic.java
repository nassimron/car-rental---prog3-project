package de.hochschule.carrental.logic;

import de.hochschule.carrental.data.Car;
import de.hochschule.carrental.data.Contract;
import de.hochschule.carrental.data.Customer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class ContractLogic extends Contract{

    ArrayList<String> contractIds = new ArrayList<>();

    public Contract create(String ID, Customer customer, Car car, LocalDate BeginDate, LocalDate EndDate, int Price){
        car.setAvailability(false);
        return new Contract(ID,customer,car,BeginDate,EndDate,Price);
    }

    public void createContract(){






    }

    public void loadIdList(){
        contractIds.add("V1");
        contractIds.add("V2");
        contractIds.add("V3");
        contractIds.add("V4");
    }

    public void delete(Contract contract){

    }

    public void saveToDb(){

    }

    public String generateID(){
        int Id = Integer.parseInt(contractIds.getLast().replace("V", "")) + 1;
        return "V" + Id;
    }

    public int calcPrice(int carPrice, LocalDate beginDate, LocalDate endDate){
        int contractDurationDays = Math.toIntExact(ChronoUnit.DAYS.between(beginDate, endDate));

        return carPrice * contractDurationDays;
    }

    public void returnCar(Contract contract){ //Availability von Car wird auf "1" gesetzt
        contract.getCar().setAvailability(true);
    }


}
