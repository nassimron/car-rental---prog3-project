package de.hochschule.carrental;

import de.hochschule.carrental.data.Database;
import de.hochschule.carrental.presentation.ConsoleUI;

public class App {
    public static void main(String[] args) {
        System.out.println("=== Car Rental System ===");

        // 1. Datenbank initialisieren
        Database.init();

        // 2. UI starten
        ConsoleUI ui = new ConsoleUI();
        ui.startUI();

        System.out.println("Programm beendet.");
    }
}