package de.hochschule.carrental;

import de.hochschule.carrental.data.Car;
import de.hochschule.carrental.data.Contract;
import de.hochschule.carrental.data.Customer;
import de.hochschule.carrental.logic.ContractLogic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static Connection con;
    public static void main( String[] args )
    {
        TestLogic testlogic = new TestLogic();
        testlogic.printAvailability();

        try{
            con = DriverManager.getConnection("jdbc:sqlite:TestDb");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Test test = new Test();

        test.findCars();


        System.out.println( "Hello World!" );
    }
}
