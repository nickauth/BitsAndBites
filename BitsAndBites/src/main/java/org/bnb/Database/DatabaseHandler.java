package org.bnb.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHandler {
  public static Connection connect(){
      Connection con = null;
      try {
          Class.forName("org.sqlite.JDBC");
          con = DriverManager.getConnection("jdbc:sqlite:D:\\Dokumente\\Uni\\5. Semester\\SWEP\\Git\\BitsAndBites\\src\\main\\resources\\BitsNBitesDB.db");
          System.out.println("Connected");
      } catch (ClassNotFoundException | SQLException e){
          System.out.println(e+"");
      }
      return con;
  }

}
