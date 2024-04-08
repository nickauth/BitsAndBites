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
        return null;
    }

    @Override
    public List<Ingredients> getAllIngredients() {
        List<Ingredients> ingredients = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Ingredients");
            while (resultSet.next()) {
                Ingredients ingredient = extractIngredientFromResultSet(resultSet);
                ingredients.add(ingredient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ingredients;
    }

    @Override
    public void addIngredients(Ingredients ingredients) {

    }

    @Override
    public void updateIngredients(Ingredients ingredients) {

    }

    @Override
    public void deleteIngredients(int ingredientsId) {

    }

    private Ingredients extractIngredientFromResultSet(ResultSet resultSet) throws SQLException {
        Ingredients ingredient = new Ingredients();
        ingredient.setId(resultSet.getInt("ID"));
        ingredient.setName(resultSet.getString("Name"));
        return ingredient;
    }
}