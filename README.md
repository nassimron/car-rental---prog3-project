# Car Rental System -- Programmieren 3 Projekt

## Beschreibung

Dieses Repository enthält ein konsolenbasiertes Car-Rental-System in
Java, das im Rahmen der Prüfung **Programmieren 3** entwickelt wurde.

Ziel des Projekts ist die Umsetzung eines einfachen Verwaltungssystems
für Fahrzeuge, Kunden und Mietverträge mit klarer Struktur und Trennung
der Zuständigkeiten.

Das System unterstützt bei der einfachen Verwaltung von Fahrzeugvermietung
und verhindert fehlerhafte Buchungen durch automatische Prüfungen der Verfügbarkeit.

Der Fokus liegt auf: 
- sauberem Code
-  nachvollziehbarer Geschäftslogik
-   Nutzung einer Datenbank zur Persistenz
-   Testbarkeit durch JUnit

------------------------------------------------------------------------

## Features

### Fahrzeugverwaltung

-   Autos anlegen
-   Autos anzeigen
-   Verfügbarkeit verwalten

### Kundenverwaltung

-   Kunden anlegen
-   Kunden anzeigen

### Vertragsverwaltung

-   Mietverträge erstellen
-   Verknüpfung von Kunde und Auto
-   Automatische Preisberechnung

### Persistenz

-   Zentrale Datenhaltung über eine SQLite-Datenbank
-   Verwaltung über die `Database`-Klasse

### Tests

-   Unit- und Integrationstests mit JUnit

------------------------------------------------------------------------

## Projektstruktur

    src/main/java/de/hochschule/carrental
    │
    ├── data          → Datenklassen (Car, Customer, Contract, Database)
    ├── logic         → Geschäftslogik (CarLogic, CustomerLogic, ContractLogic)
    ├── presentation  → Konsolenoberfläche (ConsoleUI, UIHelper)
    ├── jooq          → Generierte Klassen (jOOQ)
    └── App.java      → Startpunkt der Anwendung

    src/test/java
    └── JUnit-Tests für data- und logic-Klassen

------------------------------------------------------------------------

## Voraussetzungen

-   Java 17 oder höher
-   Maven

------------------------------------------------------------------------

## Installation & Start

### Projekt kompilieren und testen

mvn clean test

### Programm starten

mvn clean compile exec:java -Dexec.mainClass="de.hochschule.carrental.App"

oder über die IDE mit:

de.hochschule.carrental.App

------------------------------------------------------------------------

## Datenbank

-   Verwendet wird SQLite
-   Die Datenbank wird automatisch beim Start initialisiert
-   Speicherort: data/carrental.db

------------------------------------------------------------------------

## Fehlerbehebung

### Java-Version nicht korrekt

java -version

Gegebenenfalls Java 17 oder höher installieren.

### Maven nicht erkannt

mvn -v

Falls Maven nicht gefunden wird, neu installieren und den PATH prüfen.

------------------------------------------------------------------------

## Support

Bei Fragen oder Problemen zum Projekt kann ein Issue im Repository erstellt
oder ein Mitglied des Projektteams kontaktiert werden.

------------------------------------------------------------------------

## Maintainer

Dieses Projekt wird im Rahmen der Veranstalltung **Programmieren 3**
entwickelt und gepfelgt von:

- Lennart Braun
- Sidney Burkhardt
- Amar Chahror
- Nassim Roncoroni
