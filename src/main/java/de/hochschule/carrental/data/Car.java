package de.hochschule.carrental.data;

public class Car {



    private static String ID;
    private static String Brand;
    private static String Model;
    private static String Category;
    private static float Price;
    private static boolean Availability;

    public Car(String ID, String Brand, String Model, String Category, float Price, boolean Availability){
        this.ID = ID;
        this.Brand = Brand;
        this.Model = Model;
        this.Category = Category;
        this.Price = Price;
        this.Availability = Availability;
    }

    public static String getID() {
        return ID;
    }

    public static void setID(String ID) {
        Car.ID = ID;
    }

    public static String getBrand() {
        return Brand;
    }

    public static void setBrand(String brand) {
        Brand = brand;
    }

    public static String getModel() {
        return Model;
    }

    public static void setModel(String model) {
        Model = model;
    }

    public static String getCategory() {
        return Category;
    }

    public static void setCategory(String category) {
        Category = category;
    }

    public static float getPrice() {
        return Price;
    }

    public static void setPrice(float price) {
        Price = price;
    }

    public static boolean isAvailability() {
        return Availability;
    }

    public static void setAvailability(boolean availability) {
        Availability = availability;
    }
}
