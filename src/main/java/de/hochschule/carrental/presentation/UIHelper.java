package de.hochschule.carrental.presentation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UIHelper {

    public static boolean isValidInt(String input) {
        if (!input.isEmpty()) {
            try {
                Integer.parseInt(input);
            } catch (NumberFormatException e) {
                return false;
            }
            return true;
        }
        return false;
    }

    public static boolean isValidContractID(String input){
        if(!input.isEmpty()){
            return input.matches("V\\d+");
        }
        return false;
    }

    public static boolean isValidCustomerID(String input){
        if(!input.isEmpty()){
            return input.matches("K\\d+");
        }
        return false;
    }

    public static boolean isValidCarID(String input){
        if(!input.isEmpty()){
            return input.matches("A\\d+");
        }
        return false;
    }


    public static boolean isValidDate(String input){
        if(!input.isEmpty()){
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(input,formatter);
            } catch (Exception e) {
                return false;
            }
            return true;
        }
        return false;
    }
}
