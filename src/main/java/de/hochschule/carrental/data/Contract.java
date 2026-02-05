package de.hochschule.carrental.data;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Contract {

    private String id;
    private Customer customer;
    private Car car;
    private LocalDate beginDate;
    private LocalDate endDate;
    private int price;

    public Contract() {

    }

    public Contract(String id,
                    Customer customer,
                    Car car,
                    LocalDate beginDate,
                    LocalDate endDate,
                    int price) {

        this.id = id;
        this.customer = customer;
        this.car = car;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.price = price;
    }


    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
