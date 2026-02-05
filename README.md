# car-rental---prog3-project

## Beschreibung
Dieses Repository enthält ein konsolenbasiertes Car-Rental-System in Java, entwickelt im Rahmen der Prüfung Programmieren 3. Der Fokus liegt auf sauberer Struktur, klarer Trennung von Zuständigkeiten und nachvollziehbarer Geschäftslogik.


## Features
Fahrzeuge
- Autos anlegen
- Autos anzeigen
- Verfügbarkeitsstatus verwalten

Kundenverwaltung
- Kunden anlegen
- Kunden anzeigen

Vertragsverwaltung
- Mietverträge erstellen
- Verknüpfung von Kunde und Auto

Persistenz
- Zentrale In-Memory-Datenhaltung über Database

## Projektstruktur
- `data` - Car, Contract, Database
- `jooq` - DefaultCatalog, DefaultSchema, Keys, Tables
- `logic` - CarLogic, ContractLogic, CustomerLogic
- `presentation` - ConsoleUI, UIHelper
- `test` - JUnit Tests

## Voraussetzungen
- Java 17+
- Maven

## Fehlerbehebung
Häufige Fahler:
- Java-Version nicht korrekt.
  Fehlermeldung:
  ```bash
  Fehler: java version is not supported
  ```
  Mit "java -version"  Version Überprüfen und falls <17, Java 17 oder höher installieren

- Maven Fehlermeldung:
  ```bash
  mvn : Die Benennung "mvn" wurde nicht als Name eines Cmdlet, ...
  ```
  Mit "mvn -v"  Maven Überprüfen und falls nicht gefunden, Maven neu installieren.
  Sollte ein Build-Fehler auftreten "mvn clean compile" ausführen.
  

## Starten
```bash
mvn clean testh
