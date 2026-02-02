package de.hochschule.carrental.logic;

import de.hochschule.carrental.data.Car;
import de.hochschule.carrental.data.Contract;
import de.hochschule.carrental.data.Customer;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class ContractLogic {

    private final List<Contract> contracts = new ArrayList<>();
    private int nextId = 1;


    public Contract createContract(Customer customer, Car car, LocalDate beginDate, LocalDate endDate) {
        if (customer == null)
            throw new IllegalArgumentException("Customer must not be empty");

        if (car == null)
            throw new IllegalArgumentException("Car must not be empty");

        if (beginDate == null || endDate == null)
            throw new IllegalArgumentException("Dates must not be empty");

        if (endDate.isBefore(beginDate))
            throw new IllegalArgumentException("End date must be >= begin date");

        if (!car.getAvailability()) {
            throw new IllegalArgumentException("Car is not available");
        }

        int price = calcPrice(car.getPrice(), beginDate, endDate);

        String ID = generateId();
        Contract contract = new Contract(ID, customer, car, beginDate, endDate, price);
        contracts.add(contract);

        car.setAvailability(false);

        return contract;
    }

    public void finishContract(String contractId) {
        Contract contract = getContractById(contractId);
        if (contract == null) {
            throw new IllegalArgumentException("Contract not found: " + contractId);
        }

        contract.getCar().setAvailability(true);
        contracts.remove(contract);
    }
        public List<Contract> getAllContracts() {
            return contracts;
        }

    public Contract getContractById(String id) {
        for (Contract c : contracts) {
            if (c.getID().equals(id)) return c;
        }
        return null;
    }

    private String generateId() {
        return "V" + (nextId++);
    }

    public int calcPrice(int carPrice, LocalDate beginDate, LocalDate endDate) {
        long days = ChronoUnit.DAYS.between(beginDate, endDate) + 1;
        if (days <= 0) throw new IllegalArgumentException("Invalid contract duration");
        return Math.toIntExact(days) * carPrice;
    }

    public void saveToDb() {

    }
}
