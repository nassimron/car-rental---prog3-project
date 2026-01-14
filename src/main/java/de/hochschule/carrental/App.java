package de.hochschule.carrental;

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
