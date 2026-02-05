package de.hochschule.carrental.data;

public class Car {


    private String id;
    private String brand;
    private String model;
    private String category;
    private int price;
    private boolean availability;

    public Car(String id,
               String brand,
               String model,
               String category,
               int price,
               boolean available) {

        this.id = id;
        this.brand = brand;
        this.model = model;
        this.category = category;
        this.price = price;
        this.availability = available;
    }

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean getAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
