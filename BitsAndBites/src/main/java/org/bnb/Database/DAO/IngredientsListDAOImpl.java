package org.bnb.Database.DAO;

import org.bnb.Database.DAO.interfaces.IngredientsListDAO;
import org.bnb.Database.DatabaseHandler;
import org.bnb.Classes.IngredientsList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IngredientsListDAOImpl implements IngredientsListDAO {

    private final Connection connection;

    public IngredientsListDAOImpl() {
        this.connection = DatabaseHandler.connect();
    }

    @Override
    public IngredientsList getIngredientsListById(int ingredientsListId) {
        String query = "SELECT * FROM IngredientsList WHERE ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, ingredientsListId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                IngredientsList ingredientsList = new IngredientsList();
                ingredientsList.setId(resultSet.getInt("ID"));
                ingredientsList.setListID(resultSet.getInt("ListID"));
                ingredientsList.setIngredientID(resultSet.getInt("IngredientID"));
                ingredientsList.setRecipeID(resultSet.getInt("RecipeID"));
                ingredientsList.setAmount(resultSet.getDouble("Amount"));
                ingredientsList.setUnit(resultSet.getString("Unit"));
                return ingredientsList;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<IngredientsList> getIngredientsListByRecipeId(int recipeId) {
        List<IngredientsList> ingredientsLists = new ArrayList<>();
        String query = "SELECT * FROM IngredientsList WHERE RecipeID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, recipeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                IngredientsList ingredientsList = new IngredientsList();
                ingredientsList.setId(resultSet.getInt("ID"));
                ingredientsList.setListID(resultSet.getInt("ListID"));
                ingredientsList.setIngredientID(resultSet.getInt("IngredientID"));
                ingredientsList.setRecipeID(resultSet.getInt("RecipeID"));
                ingredientsList.setAmount(resultSet.getDouble("Amount"));
                ingredientsList.setUnit(resultSet.getString("Unit"));
                ingredientsLists.add(ingredientsList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ingredientsLists;
    }

    @Override
    public List<IngredientsList> getAllIngredientsLists() {
        List<IngredientsList> ingredientsLists = new ArrayList<>();
        String query = "SELECT * FROM IngredientsList";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                IngredientsList ingredientsList = new IngredientsList();
                ingredientsList.setId(resultSet.getInt("ID"));
                ingredientsList.setListID(resultSet.getInt("ListID"));
                ingredientsList.setIngredientID(resultSet.getInt("IngredientID"));
                ingredientsList.setRecipeID(resultSet.getInt("RecipeID"));
                ingredientsList.setAmount(resultSet.getDouble("Amount"));
                ingredientsList.setUnit(resultSet.getString("Unit"));
                ingredientsLists.add(ingredientsList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ingredientsLists;
    }

    @Override
    public void addIngredientsList(IngredientsList ingredientsList) {
        String query = "INSERT INTO IngredientsList (ID, /* weitere Spalten */) VALUES (?, /* weitere Werte */)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, ingredientsList.getId());
            preparedStatement.setInt(2, ingredientsList.getListID());
            preparedStatement.setInt(3, ingredientsList.getIngredientID());
            preparedStatement.setInt(4, ingredientsList.getRecipeID());
            preparedStatement.setDouble(5, ingredientsList.getAmount());
            preparedStatement.setString(6, ingredientsList.getUnit());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateIngredientsList(IngredientsList ingredientsList) {
        String query = "UPDATE IngredientsList SET /* Spalten = Werte */ WHERE ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, ingredientsList.getId());
            preparedStatement.setInt(2, ingredientsList.getIngredientID());
            preparedStatement.setInt(3, ingredientsList.getRecipeID());
            preparedStatement.setDouble(4, ingredientsList.getAmount());
            preparedStatement.setString(5, ingredientsList.getUnit());
            preparedStatement.setInt(6, ingredientsList.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteIngredientsList(int ingredientsListId) {
        String query = "DELETE FROM IngredientsList WHERE ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, ingredientsListId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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