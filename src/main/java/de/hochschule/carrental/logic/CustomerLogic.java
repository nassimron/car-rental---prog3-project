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
        String sql = "SELECT * FROM customers";

        try (Connection conn = Database.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

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
            System.err.println("Fehler beim Laden: " + e.getMessage());
        }
    }

    public Customer create(String ID, String Name, String DLNumber, String Email) {
        if (ID == null || ID.isBlank())
            throw new IllegalArgumentException("Customer ID must not be empty");

        for (Customer c : customers) {
            if (c.getID().equals(ID)) {
                throw new IllegalStateException("Customer with ID " + ID + " already exists");
            }
        }

        Customer customer = new Customer(ID, Name, DLNumber, Email);

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
            System.out.println("Kunde in DB gespeichert: " + customer.getID());

        } catch (SQLException e) {
            System.err.println("Fehler beim Speichern: " + e.getMessage());
        }
    }

    public void delete(String id) {
        String sql = "DELETE FROM customers WHERE id = ?";

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, id);
            pstmt.executeUpdate();

            customers.removeIf(c -> c.getID().equals(id));

        } catch (SQLException e) {
            System.err.println("Fehler beim Löschen: " + e.getMessage());
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