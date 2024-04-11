package org.bnb.Database.DAO;

import org.bnb.Database.DAO.interfaces.IngredientsDAO;
import org.bnb.Classes.Ingredients;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IngredientsDAOImpl implements IngredientsDAO {

    private final Connection connection;

    public IngredientsDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Ingredients getIngredientsById(int ingredientsId) {
        String query = "SELECT * FROM Ingredients WHERE ID = ?";
        Ingredients ingredients = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, ingredientsId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                ingredients = extractIngredientsFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ingredients;
    }

    @Override
    public List<Ingredients> getAllIngredients() {
        List<Ingredients> ingredients = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Ingredients");
            while (resultSet.next()) {
                Ingredients ingredient = extractIngredientsFromResultSet(resultSet);
                ingredients.add(ingredient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ingredients;
    }

    @Override
    public void addIngredients(Ingredients ingredients) {
        try {
            String query = "INSERT INTO Ingredients (Name) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, ingredients.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateIngredients(Ingredients ingredients) {
        try {
            String query = "UPDATE Ingredients SET Name = ? WHERE ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, ingredients.getName());
            preparedStatement.setInt(2, ingredients.getId());
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

    private Ingredients extractIngredientsFromResultSet(ResultSet resultSet) throws SQLException {
        Ingredients ingredient = new Ingredients();
        ingredient.setId(resultSet.getInt("ID"));
        ingredient.setName(resultSet.getString("Name"));
        return ingredient;
    }
}