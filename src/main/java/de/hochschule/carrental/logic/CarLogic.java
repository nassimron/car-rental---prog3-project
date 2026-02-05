package de.hochschule.carrental.logic;

import de.hochschule.carrental.data.Car;
import de.hochschule.carrental.data.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarLogic {
    // In-memory list for cache
    private List<Car> cars;

    public CarLogic() {
        this.cars = new ArrayList<>();
        loadFromDatabase();
    }

    private void loadFromDatabase() {
        String sql = "SELECT * FROM cars";

        cars.clear();

        try (Connection conn = Database.connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Car car = new Car(rs.getString("id"), rs.getString("brand"), rs.getString("model"), rs.getString("category"), rs.getInt("price"), rs.getBoolean("available"));
                cars.add(car);
            }

        } catch (SQLException e) {
            System.err.println("Error loading: " + e.getMessage());
        }
    }

    public Car create(String brand, String model, String category, int price, boolean availability) {

        String id = "A" + getNextCarNumber();

        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Car ID must not be empty");
        }

        for (Car c : cars) {
            if (c.getID().equals(id)) {
                throw new IllegalStateException("Car with ID " + id + " already exists");
            }
        }

        Car car = new Car(id, brand, model, category, price, availability);

        saveToDatabase(car);
        cars.add(car);

        return car;
    }

    private void saveToDatabase(Car car) {
        String sql = "INSERT INTO cars(id, brand, model, category, price, available) VALUES(?,?,?,?,?,?)";

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, car.getID());
            pstmt.setString(2, car.getBrand());
            pstmt.setString(3, car.getModel());
            pstmt.setString(4, car.getCategory());
            pstmt.setInt(5, car.getPrice());
            pstmt.setBoolean(6, car.getAvailability());

            pstmt.executeUpdate();
            System.out.println("Auto in DB gespeichert: " + car.getID());

        } catch (SQLException e) {
            System.err.println("Fehler beim Speichern: " + e.getMessage());
        }
    }

    public void updateAvailability(String carId, boolean available) {
        String sql = "UPDATE cars SET available = ? WHERE id = ?";

        try (Connection conn = Database.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setBoolean(1, available);
            pstmt.setString(2, carId);
            pstmt.executeUpdate();

            for (Car car : cars) {
                if (car.getID().equals(carId)) {
                    car.setAvailability(available);
                    break;
                }
            }

        } catch (SQLException e) {
            System.err.println("Error loading: " + e.getMessage());
        }
    }

    public List<Car> getAllCars() {
        return cars;
    }

    public Car getCarById(String id) {
        for (Car car : cars) {
            if (car.getID().equals(id)) {
                return car;
            }
        }
        return null;
    }

    public List<Car> getAvailableCars() {
        List<Car> availableCars = new ArrayList<>();
        for (Car car : cars) {
            if (car.getAvailability()) {
                availableCars.add(car);
            }
        }
        return availableCars;
    }

    public int getNextCarNumber() {
        int max = 0;

        for (Car car : getAllCars()) {

            String id = car.getID();

            if (id.startsWith("A")) {
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

}