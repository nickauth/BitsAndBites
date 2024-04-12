package org.bnb.Database.DAO;

import org.bnb.Database.DAO.interfaces.IngredientsDAO;
import org.bnb.Database.Classes.IngredientsDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IngredientsDAOImpl implements IngredientsDAO {

    private final Connection connection;

    public IngredientsDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public IngredientsDB getIngredientsById(int ingredientsId) {
        String query = "SELECT * FROM Ingredients WHERE ID = ?";
        IngredientsDB ingredientsDB = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, ingredientsId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                ingredientsDB = extractIngredientsFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ingredientsDB;
    }

    @Override
    public List<IngredientsDB> getAllIngredients() {
        List<IngredientsDB> ingredients = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Ingredients");
            while (resultSet.next()) {
                IngredientsDB ingredient = extractIngredientsFromResultSet(resultSet);
                ingredients.add(ingredient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ingredients;
    }

    @Override
    public int getOrInsertIngredientId(String ingredientName) {
        try {String query = "SELECT ingredientId FROM Ingredients WHERE name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, ingredientName);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("ingredientId"); // Zutat existiert bereits, gebe die ID zurück
            } else {
                // Zutat existiert nicht, füge sie hinzu und gebe die neue ID zurück
                String insertQuery = "INSERT INTO Ingredients (name) VALUES (?)";
                PreparedStatement insertStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
                insertStatement.setString(1, ingredientName);
                insertStatement.executeUpdate();
                ResultSet generatedKeys = insertStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1); // Rückgabe der neuen ID
                } else {
                    return -1; // Fehler beim Einfügen der Zutat
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1; // Fehler beim Zugriff auf die Datenbank
        }
    }


    @Override
    public void addIngredients(IngredientsDB ingredientsDB) {
        try {
            String query = "INSERT INTO Ingredients (Name) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, ingredientsDB.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateIngredients(IngredientsDB ingredientsDB) {
        try {
            String query = "UPDATE Ingredients SET Name = ? WHERE ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, ingredientsDB.getName());
            preparedStatement.setInt(2, ingredientsDB.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteIngredients(int ingredientsId) {
        try {
            String query = "DELETE FROM Ingredients WHERE ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, ingredientsId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private IngredientsDB extractIngredientsFromResultSet(ResultSet resultSet) throws SQLException {
        IngredientsDB ingredient = new IngredientsDB();
        ingredient.setId(resultSet.getInt("ID"));
        ingredient.setName(resultSet.getString("Name"));
        return ingredient;
    }
}