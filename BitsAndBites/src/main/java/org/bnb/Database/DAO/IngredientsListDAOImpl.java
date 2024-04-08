package org.bnb.Database.DAO;

import org.bnb.Database.DAO.interfaces.IngredientsListDAO;
import org.bnb.Classes.IngredientsList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IngredientsListDAOImpl implements IngredientsListDAO {

    private final Connection connection;

    public IngredientsListDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public IngredientsList getIngredientsListById(int ingredientsListId) {
        return null;
    }

    @Override
    public List<IngredientsList> getAllIngredientsLists() {
        List<IngredientsList> ingredientsLists = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM IngredientsList");
            while (resultSet.next()) {
                IngredientsList ingredientsList = extractIngredientsListFromResultSet(resultSet);
                ingredientsLists.add(ingredientsList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ingredientsLists;
    }

    @Override
    public void addIngredientsList(IngredientsList ingredientsList) {

    }

    @Override
    public void updateIngredientsList(IngredientsList ingredientsList) {

    }

    @Override
    public void deleteIngredientsList(int ingredientsListId) {

    }

    private IngredientsList extractIngredientsListFromResultSet(ResultSet resultSet) throws SQLException {
        IngredientsList ingredientsList = new IngredientsList();
        ingredientsList.setId(resultSet.getInt("ID"));
        ingredientsList.setListID(resultSet.getInt("ListID"));
        ingredientsList.setIngredientID(resultSet.getInt("IngredientID"));
        ingredientsList.setRecipeID(resultSet.getInt("RecipeID"));
        ingredientsList.setAmount(resultSet.getDouble("Amount"));
        ingredientsList.setUnit(resultSet.getString("Unit"));
        return ingredientsList;
    }
}