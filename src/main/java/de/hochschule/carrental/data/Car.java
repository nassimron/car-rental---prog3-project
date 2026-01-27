package de.hochschule.carrental.data;

public class Car {



    private String ID;
    private String Brand;
    private String Model;
    private String Category;
    private float Price;
    private boolean Availability;

    public Car(String ID, String Brand, String Model, String Category, float Price, boolean Availability){
        this.ID = ID;
        this.Brand = Brand;
        this.Model = Model;
        this.Category = Category;
        this.Price = Price;
        this.Availability = Availability;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float price) {
        Price = price;
    }

    public boolean getAvailability() {
        return Availability;
    }

    public void setAvailability(boolean availability) {
        Availability = availability;
    }
}
