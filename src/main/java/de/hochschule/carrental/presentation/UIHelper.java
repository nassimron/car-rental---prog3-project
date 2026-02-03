package de.hochschule.carrental.presentation;

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
            if(input.matches("V\\d+")){
                return true;
            }
        }
        return false;
    }

    public static boolean isValidCustomerID(String input){
        if(!input.isEmpty()){
            if(input.matches("K\\d+")){
                return true;
            }
        }
        return false;
    }

    public static boolean isValidCarID(String input){
        if(!input.isEmpty()){
            if(input.matches("A\\d+")){
                return true;
            }
        }
        return false;
    }
}
