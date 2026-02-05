package de.hochschule.carrental.logic;

import de.hochschule.carrental.data.Customer;
import de.hochschule.carrental.data.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerLogic {

    private final List<Customer> customers = new ArrayList<>();

    public CustomerLogic() {
        loadFromDatabase();
    }


    private void loadFromDatabase() {
        String sql = "SELECT id, name, dl_number, email FROM customers";

        try (Connection conn = Database.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            customers.clear();

            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("dl_number"),
                        rs.getString("email")
                );
                customers.add(customer);
            }

        } catch (SQLException e) {
            System.out.println("Fehler beim Laden der Kunden: " + e.getMessage());
        }
    }


    public int getNextCustomerNumber() {
        int max = 0;

        for (Customer c : customers) {
            String id = c.getID();

            if (id != null && id.startsWith("K")) {
                try {
                    int number = Integer.parseInt(id.substring(1));
                    if (number > max) {
                        max = number;
                    }
                } catch (NumberFormatException ignored) {

                }
            }
        }

        return max + 1;
    }

    public Customer create( String name, String dlNumber, String email) {

        String id = "K" + getNextCustomerNumber();

        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Customer ID must not be empty");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name must not be empty");
        }
        if (dlNumber == null || dlNumber.isBlank()) {
            throw new IllegalArgumentException("Driver license must not be empty");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email must not be empty");
        }

        if (getCustomerById(id) != null) {
            throw new IllegalStateException("Customer with ID " + id + " already exists");
        }

        Customer customer = new Customer(id, name, dlNumber, email);

        saveToDatabase(customer);
        customers.add(customer);

        return customer;
    }

    private void saveToDatabase(Customer customer) {
        String sql = "INSERT INTO customers(id, name, dl_number, email) VALUES(?,?,?,?)";

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, customer.getID());
            pstmt.setString(2, customer.getName());
            pstmt.setString(3, customer.getDLNumber());
            pstmt.setString(4, customer.getEmail());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Fehler beim Speichern des Kunden: " + e.getMessage());
        }
    }

    public List<Customer> getAllCustomers() {
        return customers;
    }

    public Customer getCustomerById(String id) {
        for (Customer c : customers) {
            if (c.getID().equals(id)) {
                return c;
            }
        }
        return null;
    }
}
