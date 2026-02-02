package de.hochschule.carrental.logic;

import de.hochschule.carrental.data.Car;
import de.hochschule.carrental.data.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerLogic {

    private final List<Customer> customers;

    public CustomerLogic() {
        this.customers = new ArrayList<>();
    }

    private Customer create(String ID, String Name, String DLNumber, String Email) {
        if (ID == null || ID.isBlank())
            throw new IllegalArgumentException("Customer must not be empty");

        if (Name == null || Name.isBlank())
            throw new IllegalArgumentException("Name must not be empty");

        if (DLNumber == null || DLNumber.isBlank())
            throw new IllegalArgumentException("DLN must not be empty");

        if (Email == null || Email.isBlank())
            throw new IllegalArgumentException("Email must not be empty");

        Customer customer = new Customer(ID, Name, DLNumber, Email);
        customers.add(customer);
        return customer;
    }

    public void delete(String id){
        customers.removeIf(c -> c.getID().equals(id));
    }

    public List<Customer> getAllCustomers() {
        return customers;
    }

    public Customer getCustomerById(String id) {
        for (Customer c : customers) {
            if (c.getID().equals(id)) {
                return c;
            }
        }
        return null;
    }

    private void saveToDb(){

    }
}
