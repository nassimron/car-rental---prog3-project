# car-rental---prog3-project

## Beschreibung
Ein einfaches Car-Rental-System mit Schichtenstruktur in Java.

## Features
- Autos anlegen & anzeigen
- Kunden anlegen & anzeigen
- Verfügbarkeit der Autos verwalten
- JUnit-Tests

## Projektstruktur
- `data` - Car, Contract, Database
- `jooq` - DefaultCatalog, DefaultSchema, Keys, Tables
- `logic` - CarLogic, ContractLogic, CustomerLogic
- `presentation` - ConsoleUI, UIHelper
- `test` - JUnit Tests

## Voraussetzungen
- Java 17+
- Maven

## Starten
```bash
mvn clean test**
