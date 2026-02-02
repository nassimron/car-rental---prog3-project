package de.hochschule.carrental.data;

import java.time.LocalDateTime;

public class Contract {

    private String ID;
    private Customer Customer;
    private Car Car;
    private LocalDateTime BeginDate;
    private LocalDateTime EndDate;
    private float Price;

    public Contract(String ID, Customer Customer, Car Car, LocalDateTime BeginDate, LocalDateTime EndDate, int Price) {
        this.ID = ID;
        this.Customer = Customer;
        this.Car = Car;
        this.BeginDate = BeginDate;
        this.EndDate = EndDate;
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

    public LocalDateTime getBeginDate() {
        return BeginDate;
    }

    public void setBeginDate(LocalDateTime beginDate) {
        BeginDate = beginDate;
    }

    public LocalDateTime getEndDate() {
        return EndDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        EndDate = endDate;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float price) {
        Price = price;
    }
}
