package de.hochschule.carrental.logic;

import de.hochschule.carrental.data.Car;
import de.hochschule.carrental.data.Contract;
import de.hochschule.carrental.data.Customer;
import de.hochschule.carrental.data.Database;

import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class ContractLogic {
    private final List<Contract> contracts = new ArrayList<>();
    private int nextId = 1;

    public ContractLogic() {
        loadFromDatabase();
        calculateNextId();
    }

    private void loadFromDatabase() {
        String sql = "SELECT * FROM contracts";

        try (Connection conn = Database.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                // Hier musst du CarLogic und CustomerLogic verwenden
                // um Car und Customer Objekte zu holen
                // (Das ist etwas komplexer, aber machbar)

                // Fürs erste speichern wir nur die IDs
                // Oder wir laden gleich alles zusammen
            }

        } catch (SQLException e) {
            System.err.println("Fehler beim Laden: " + e.getMessage());
        }
    }

    // Einfacher Ansatz: ContractLogic braucht Referenzen auf CarLogic und CustomerLogic
    private CarLogic carLogic;
    private CustomerLogic customerLogic;

    public void setCarLogic(CarLogic carLogic) {
        this.carLogic = carLogic;
    }

    public void setCustomerLogic(CustomerLogic customerLogic) {
        this.customerLogic = customerLogic;
    }

    public Contract createContract(Customer customer, Car car, LocalDate beginDate, LocalDate endDate) {
        // Validierung
        if (customer == null || car == null)
            throw new IllegalArgumentException("Customer and Car must not be empty");

        if (!car.getAvailability())
            throw new IllegalArgumentException("Car is not available");

        // Preis berechnen
        int price = calcPrice(car.getPrice(), beginDate, endDate);
        String id = "V" + nextId++;

        Contract contract = new Contract(id, customer, car, beginDate, endDate, price);

        // In DB speichern
        saveToDatabase(contract);

        // Auto als vermietet markieren
        carLogic.updateAvailability(car.getID(), false);

        // In Liste speichern
        contracts.add(contract);
        return contract;
    }

    private void saveToDatabase(Contract contract) {
        String sql = "INSERT INTO contracts(id, customer_id, car_id, begin_date, end_date, price) VALUES(?,?,?,?,?,?)";

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, contract.getID());
            pstmt.setString(2, contract.getCustomer().getID());
            pstmt.setString(3, contract.getCar().getID());
            pstmt.setString(4, contract.getBeginDate().toString());
            pstmt.setString(5, contract.getEndDate().toString());
            pstmt.setInt(6, contract.getPrice());

            pstmt.executeUpdate();
            System.out.println("Vertrag in DB gespeichert: " + contract.getID());

        } catch (SQLException e) {
            System.err.println("Fehler beim Speichern: " + e.getMessage());
        }
    }

    public void finishContract(String contractId) {
        Contract contract = getContractById(contractId);
        if (contract == null) return;

        // Auto wieder verfügbar machen
        carLogic.updateAvailability(contract.getCar().getID(), true);

        // Aus DB löschen
        String sql = "DELETE FROM contracts WHERE id = ?";

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, contractId);
            pstmt.executeUpdate();

            // Aus Liste entfernen
            contracts.remove(contract);

        } catch (SQLException e) {
            System.err.println("Fehler beim Löschen: " + e.getMessage());
        }
    }

    private void calculateNextId() {
        // Einfache ID-Berechnung
        nextId = contracts.size() + 1;
    }

    // Restliche Methoden bleiben gleich
    public List<Contract> getAllContracts() {
        return contracts;
    }

    public Contract getContractById(String id) {
        for (Contract c : contracts) {
            if (c.getID().equals(id)) return c;
        }
        return null;
    }

    public int calcPrice(int carPrice, LocalDate beginDate, LocalDate endDate) {
        long days = ChronoUnit.DAYS.between(beginDate, endDate) + 1;
        if (days <= 0) throw new IllegalArgumentException("Invalid contract duration");
        return Math.toIntExact(days) * carPrice;
    }
}