package org.bnb.Database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseChecker {

    public static void main(String[] args) {
        try {
            // Verbindung zur Datenbank herstellen
            Connection connection = DriverManager.getConnection("jdbc:sqlite:D:\\Dokumente\\Uni\\5. Semester\\SWEP\\Git\\BitsAndBites\\src\\main\\resources\\BitsNBitesDB.db");

            // Metadaten über die Datenbank abrufen
            DatabaseMetaData metaData = connection.getMetaData();

            // Tabelleninformationen abrufen
            ResultSet tables = metaData.getTables(null, null, null, new String[]{"TABLE"});

            // Tabellennamen ausgeben
            System.out.println("Verfügbare Tabellen:");
            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                System.out.println(tableName);
            }

            // Verbindung schließen
            connection.close();
        } catch (SQLException e) {
            System.out.println("Fehler beim Verbinden zur Datenbank: " + e.getMessage());
        }
    }
}
