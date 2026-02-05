package de.hochschule.carrental.data;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Contract {

    private String ID;
    private Customer Customer;
    private Car Car;
    private LocalDate BeginDate;
    private LocalDate EndDate;
    private int Price;

    public Contract(String ID, Customer Customer, Car Car, LocalDate BeginDate, LocalDate EndDate, int Price) {
        this.ID = ID;
        this.Customer = Customer;
        this.Car = Car;
        this.BeginDate = BeginDate;
        this.EndDate = EndDate;
        this.Price = Price;
    }

    public Contract() {

    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Car getCar() {
        return Car;
    }

    public void setCar(Car car) {
        Car = car;
    }

    public Customer getCustomer() {
        return Customer;
    }

    public void setCustomer(Customer customer) {
        Customer = customer;
    }

    public LocalDate getBeginDate() {
        return BeginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        BeginDate = beginDate;
    }

    public LocalDate getEndDate() {
        return EndDate;
    }

    public void setEndDate(LocalDate endDate) {
        EndDate = endDate;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }
}
