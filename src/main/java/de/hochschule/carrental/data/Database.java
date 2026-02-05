package de.hochschule.carrental.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    private static final String DB_URL = "jdbc:sqlite:data/carrental.db";

    public static Connection connect() {
        try {
            return DriverManager.getConnection(DB_URL);
        } catch (SQLException e) {

            throw new RuntimeException("Verbindungsfehler zur Datenbank", e);
        }
    }

    public static void init() {

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {

            stmt.execute("PRAGMA foreign_keys = ON");

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS cars (
                    id TEXT PRIMARY KEY,
                    brand TEXT NOT NULL,
                    model TEXT NOT NULL,
                    category TEXT,
                    price INTEGER NOT NULL,
                    available BOOLEAN NOT NULL DEFAULT 1
                )
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS customers (
                    id TEXT PRIMARY KEY,
                    name TEXT NOT NULL,
                    dl_number TEXT NOT NULL UNIQUE,
                    email TEXT NOT NULL
                )
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS contracts (
                    id TEXT PRIMARY KEY,
                    customer_id TEXT NOT NULL,
                    car_id TEXT NOT NULL,
                    begin_date TEXT NOT NULL,
                    end_date TEXT NOT NULL,
                    price INTEGER NOT NULL,
                    FOREIGN KEY (customer_id) REFERENCES customers(id),
                    FOREIGN KEY (car_id) REFERENCES cars(id)
                )
            """);

            System.out.println("Datenbank initialisiert!");

        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim Initialisieren der Datenbank", e);
        }
    }
}
