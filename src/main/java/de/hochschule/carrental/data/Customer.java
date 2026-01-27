package de.hochschule.carrental.data;

public class Customer {

    private String ID;
    private String Name;
    private String DLNumber;
    private String Email;

    public Customer(String ID, String Name, String DLNumber, String Email){
        this.ID = ID;
        this.Name = Name;
        this.DLNumber = DLNumber;
        this.Email = Email;
    }


    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDLNumber() {
        return DLNumber;
    }

    public void setDLNumber(String DLNumber) {
        this.DLNumber = DLNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }
}
