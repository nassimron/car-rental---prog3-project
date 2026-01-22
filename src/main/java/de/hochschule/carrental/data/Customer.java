package de.hochschule.carrental.data;

public class Customer {

    private static String ID;
    private static String Name;
    private static String DLNumber;
    private static String Email;

    public Customer(String ID, String Name, String DLNumber, String Email){
        this.ID = ID;
        this.Name = Name;
        this.DLNumber = DLNumber;
        this.Email = Email;
    }


    public static String getID() {
        return ID;
    }

    public static void setID(String ID) {
        Customer.ID = ID;
    }

    public static String getName() {
        return Name;
    }

    public static void setName(String name) {
        Name = name;
    }

    public static String getDLNumber() {
        return DLNumber;
    }

    public static void setDLNumber(String DLNumber) {
        Customer.DLNumber = DLNumber;
    }

    public static String getEmail() {
        return Email;
    }

    public static void setEmail(String email) {
        Email = email;
    }
}
