package de.hochschule.carrental;

import de.hochschule.carrental.data.Contract;
import de.hochschule.carrental.logic.ContractLogic;
import de.hochschule.carrental.presentation.ConsoleUI;
import org.jooq.meta.derby.sys.Sys;

public class App{
    public static void main(String[] args){
        ConsoleUI ui = new ConsoleUI();
        ui.startUI();
    }
}