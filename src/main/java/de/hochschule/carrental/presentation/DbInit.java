//package de.hochschule.carrental.persistence;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.Statement;
//
//public class DbInit {
//
//    public static void main(String[] args) throws Exception {
//
//        try (Connection con = DriverManager.getConnection("jdbc:sqlite:RentalDB.db");
//             Statement st = con.createStatement()) {
//
//            st.executeUpdate("""
//                CREATE TABLE IF NOT EXISTS cars (
//                  id TEXT PRIMARY KEY,
//                  brand TEXT NOT NULL,
//                  model TEXT NOT NULL,
//                  category TEXT NOT NULL,
//                  price INTEGER NOT NULL,
//                  availability INTEGER NOT NULL
//                );
//            """);
//
//            st.executeUpdate("""
//                CREATE TABLE IF NOT EXISTS customers (
//                  id TEXT PRIMARY KEY,
//                  name TEXT NOT NULL,
//                  dlnNumber TEXT NOT NULL,
//                  email TEXT NOT NULL
//                );
//            """);
//
//            st.executeUpdate("""
//                CREATE TABLE IF NOT EXISTS contracts (
//                  id TEXT PRIMARY KEY,
//                  customer_id TEXT NOT NULL,
//                  car_id TEXT NOT NULL,
//                  begin_date TEXT NOT NULL,
//                  end_date TEXT NOT NULL,
//                  price INTEGER NOT NULL,
//                  FOREIGN KEY(customer_id) REFERENCES customers(id),
//                  FOREIGN KEY(car_id) REFERENCES cars(id)
//                );
//            """);
//        }
//
//        System.out.println("Database initialized successfully.");
//    }
//}
