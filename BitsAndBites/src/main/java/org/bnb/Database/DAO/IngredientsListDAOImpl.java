package org.bnb.Database.DAO;

import org.bnb.Database.DAO.interfaces.IngredientsListDAO;
import org.bnb.Database.DatabaseHandler;
import org.bnb.Database.Classes.IngredientsListDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IngredientsListDAOImpl implements IngredientsListDAO {

    private final Connection connection;

    public IngredientsListDAOImpl() {
        this.connection = DatabaseHandler.connect();
    }

    @Override
    public IngredientsListDB getIngredientsListById(int ingredientsListId) {
        String query = "SELECT * FROM IngredientsList WHERE ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, ingredientsListId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                IngredientsListDB ingredientsListDB = new IngredientsListDB();
                ingredientsListDB.setId(resultSet.getInt("ID"));
                //ingredientsListDB.setListID(resultSet.getInt("ListID"));
                ingredientsListDB.setIngredientID(resultSet.getInt("IngredientID"));
                ingredientsListDB.setRecipeID(resultSet.getInt("RecipeID"));
                ingredientsListDB.setAmount(resultSet.getDouble("Amount"));
                ingredientsListDB.setUnit(resultSet.getString("Unit"));
                return ingredientsListDB;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<IngredientsListDB> getIngredientsListByRecipeId(int recipeId) {
        List<IngredientsListDB> ingredientsListDBS = new ArrayList<>();
        String query = "SELECT * FROM IngredientsList WHERE RecipeID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, recipeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                IngredientsListDB ingredientsListDB = new IngredientsListDB();
                ingredientsListDB.setId(resultSet.getInt("ID"));
                //ingredientsListDB.setListID(resultSet.getInt("ListID"));
                ingredientsListDB.setIngredientID(resultSet.getInt("IngredientID"));
                ingredientsListDB.setRecipeID(resultSet.getInt("RecipeID"));
                ingredientsListDB.setAmount(resultSet.getDouble("Amount"));
                ingredientsListDB.setUnit(resultSet.getString("Unit"));
                ingredientsListDBS.add(ingredientsListDB);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ingredientsListDBS;
    }

    @Override
    public List<IngredientsListDB> getAllIngredientsLists() {
        List<IngredientsListDB> ingredientsListDBS = new ArrayList<>();
        String query = "SELECT * FROM IngredientsList";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                IngredientsListDB ingredientsListDB = new IngredientsListDB();
                ingredientsListDB.setId(resultSet.getInt("ID"));
                //ingredientsListDB.setListID(resultSet.getInt("ListID"));
                ingredientsListDB.setIngredientID(resultSet.getInt("IngredientID"));
                ingredientsListDB.setRecipeID(resultSet.getInt("RecipeID"));
                ingredientsListDB.setAmount(resultSet.getDouble("Amount"));
                ingredientsListDB.setUnit(resultSet.getString("Unit"));
                ingredientsListDBS.add(ingredientsListDB);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ingredientsListDBS;
    }

    @Override
    public void addIngredientsList(IngredientsListDB ingredientsListDB) {
        String query = "INSERT INTO IngredientsList (ID, /* weitere Spalten */) VALUES (?, /* weitere Werte */)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, ingredientsListDB.getId());
            //preparedStatement.setInt(2, ingredientsListDB.getListID());
            preparedStatement.setInt(3, ingredientsListDB.getIngredientID());
            preparedStatement.setInt(4, ingredientsListDB.getRecipeID());
            preparedStatement.setDouble(5, ingredientsListDB.getAmount());
            preparedStatement.setString(6, ingredientsListDB.getUnit());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateIngredientsList(IngredientsListDB ingredientsListDB) {
        String query = "UPDATE IngredientsList SET /* Spalten = Werte */ WHERE ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, ingredientsListDB.getId());
            preparedStatement.setInt(2, ingredientsListDB.getIngredientID());
            preparedStatement.setInt(3, ingredientsListDB.getRecipeID());
            preparedStatement.setDouble(4, ingredientsListDB.getAmount());
            preparedStatement.setString(5, ingredientsListDB.getUnit());
            preparedStatement.setInt(6, ingredientsListDB.getId());
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

    private IngredientsListDB extractIngredientsListFromResultSet(ResultSet resultSet) throws SQLException {
        IngredientsListDB ingredientsListDB = new IngredientsListDB();
        ingredientsListDB.setId(resultSet.getInt("ID"));
        //ingredientsListDB.setListID(resultSet.getInt("ListID"));
        ingredientsListDB.setIngredientID(resultSet.getInt("IngredientID"));
        ingredientsListDB.setRecipeID(resultSet.getInt("RecipeID"));
        ingredientsListDB.setAmount(resultSet.getDouble("Amount"));
        ingredientsListDB.setUnit(resultSet.getString("Unit"));
        return ingredientsListDB;
    }
}