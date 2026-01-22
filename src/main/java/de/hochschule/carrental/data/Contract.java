package de.hochschule.carrental.data;

public class Contract {

    private static String ID;
    private static Customer Customer;
    private static Car Car;
    private static String BeginDate;
    private static String EndDate;
    private static float Price;

    public Contract(String ID, Customer Customer, Car Car, String BeginDate, String EndDate, float Price) {
        this.ID = ID;
        this.Customer = Customer;
        this.Car = Car;
        this.BeginDate = BeginDate;
        this.EndDate = EndDate;

    }

    public static String getID() {
        return ID;
    }

    public static void setID(String ID) {
        Contract.ID = ID;
    }

    public static Car getCar() {
        return Car;
    }

    public static void setCar(Car car) {
        Car = car;
    }

    public static Customer getCustomer() {
        return Customer;
    }

    public static void setCustomer(Customer customer) {
        Customer = customer;
    }

    public static String getBeginDate() {
        return BeginDate;
    }

    public static void setBeginDate(String beginDate) {
        BeginDate = beginDate;
    }

    public static String getEndDate() {
        return EndDate;
    }

    public static void setEndDate(String endDate) {
        EndDate = endDate;
    }

    public static float getPrice() {
        return Price;
    }

    public static void setPrice(float price) {
        Price = price;
    }
}
