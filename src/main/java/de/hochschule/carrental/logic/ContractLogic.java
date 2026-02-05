package de.hochschule.carrental.logic;

import de.hochschule.carrental.data.Car;
import de.hochschule.carrental.data.Contract;
import de.hochschule.carrental.data.Customer;
import de.hochschule.carrental.data.Database;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class ContractLogic {

    private final List<Contract> contracts = new ArrayList<>();


    private final CarLogic carLogic = new CarLogic();
    private final CustomerLogic customerLogic = new CustomerLogic();

    public ContractLogic() {
        loadFromDatabase();
    }

    public CarLogic getCarLogic() {
        return carLogic;
    }

    public CustomerLogic getCustomerLogic() {
        return customerLogic;
    }

    private void loadFromDatabase() {
        String sql = "SELECT * FROM contracts";

        contracts.clear();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try (Connection conn = Database.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Contract contract = new Contract(
                        rs.getString("id"),
                        customerLogic.getCustomerById(rs.getString("customer_id")),
                        carLogic.getCarById(rs.getString("car_id")),
                        LocalDate.parse(rs.getString("begin_date"), formatter),
                        LocalDate.parse(rs.getString("end_date"), formatter),
                        rs.getInt("price")
                );
                contracts.add(contract);
            }

        } catch (SQLException e) {
            System.err.println("Loading error: " + e.getMessage());
        }
    }


    public Contract createContract(Customer customer, Car car, LocalDate beginDate, LocalDate endDate) {

        if (customer == null || car == null) {
            throw new IllegalArgumentException("Customer and Car must not be empty");
        }


        if (!car.getAvailability())
            throw new IllegalArgumentException("Car is not available");

        if (beginDate == null || endDate == null) {
            throw new IllegalArgumentException("Dates must not be empty");
        }

        if (endDate.isBefore(beginDate)) {
            throw new IllegalArgumentException("End date must be after begin date");
        }

        if (!car.getAvailability()) {
            throw new IllegalArgumentException("Car is not available");
        }

        String Id = "V" + getNextContractNumber();

        int price = calcPrice(car.getPrice(), beginDate, endDate);

        Contract contract = new Contract(Id, customer, car, beginDate, endDate, price);


        saveToDatabase(contract);


        carLogic.updateAvailability(car.getID(), false);

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
            System.out.println("Contract stored in database: " + contract.getID());

        } catch (SQLException e) {
            System.err.println("Error saving: " + e.getMessage());
        }
    }

    public void finishContract(String contractId) {
        Contract contract = getContractById(contractId);
        if (contract == null) return;

        carLogic.updateAvailability(contract.getCar().getID(), true);

        String sql = "DELETE FROM contracts WHERE id = ?";

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, contractId);
            pstmt.executeUpdate();

            contracts.remove(contract);

        } catch (SQLException e) {
            System.err.println("Error saving: " + e.getMessage());
        }
    }

    public int getNextContractNumber() {
        int max = 0;

        for (Contract contract : contracts) {
            String id = contract.getID();

            if (id != null && id.startsWith("V")) {
                try {
                    int num = Integer.parseInt(id.substring(1));
                    if (num > max) {
                        max = num;
                    }
                } catch (NumberFormatException ignored) {
                }
            }
        }

        return max + 1;
    }

    public List<Contract> getAllContracts() {
        return new ArrayList<>(contracts);
    }

    public Contract getContractById(String id) {
        for (Contract c : contracts) {
            if (id != null && id.equals(c.getID())) return c;
        }
        return null;
    }

    public int calcPrice(int carPrice, LocalDate beginDate, LocalDate endDate) {
        long days = ChronoUnit.DAYS.between(beginDate, endDate) + 1;

        if (days <= 0) {
            throw new IllegalArgumentException("Invalid contract duration");
        }

        return Math.toIntExact(days) * carPrice;
    }
}