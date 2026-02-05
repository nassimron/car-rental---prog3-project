package de.hochschule.carrental.data;

public class Customer {

    private String id;
    private String name;
    private String driverLicenseNumber;
    private String email;

    public Customer() {

    }

    public Customer(String id,
                    String name,
                    String driverLicenseNumber,
                    String email) {

        this.id = id;
        this.name = name;
        this.driverLicenseNumber = driverLicenseNumber;
        this.email = email;
    }


    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDriverLicenseNumber() {
        return driverLicenseNumber;
    }

    public void setDriverLicenseNumber(String driverLicenseNumber) {
        this.driverLicenseNumber = driverLicenseNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
