package org.bnb;

import org.bnb.Database.DAO.IngredientsListDAOImpl;
import org.bnb.Database.DAO.RecipeDAOImpl;
import org.bnb.Database.DatabaseHandler;

import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("Hello World");
        System.out.println("Das ist ein Test");

        //DB Test

        RecipeDAOImpl recipeDAO = new RecipeDAOImpl(DatabaseHandler.connect());
        IngredientsListDAOImpl testDAO = new IngredientsListDAOImpl();

        System.out.println(testDAO.getAllIngredientsLists());

    }
}