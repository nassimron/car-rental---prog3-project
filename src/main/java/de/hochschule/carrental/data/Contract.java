package de.hochschule.carrental.data;

public class Contract {

    private String ID;
    private Customer Customer;
    private Car Car;
    private String BeginDate;
    private String EndDate;
    private float Price;

    public Contract(String ID, Customer Customer, Car Car, String BeginDate, String EndDate, float Price) {
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

    public String getBeginDate() {
        return BeginDate;
    }

    public void setBeginDate(String beginDate) {
        BeginDate = beginDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float price) {
        Price = price;
    }
}
