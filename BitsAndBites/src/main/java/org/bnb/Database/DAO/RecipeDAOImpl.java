package org.bnb.Database.DAO;

import org.bnb.Classes.Recipe;
import org.bnb.Database.DatabaseHandler;
import org.bnb.Database.DAO.interfaces.RecipeDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecipeDAOImpl implements RecipeDAO {

    @Override
    public Recipe getRecipeById(int recipeId) {
        Recipe recipe = null;
        try (Connection con = DatabaseHandler.connect();
             PreparedStatement stmt = con.prepareStatement("SELECT * FROM Recipe WHERE RID = ?")) {
            stmt.setInt(1, recipeId);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                recipe = extractRecipeFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipe;
    }

    @Override
    public List<Recipe> getAllRecipes() {
        List<Recipe> recipes = new ArrayList<>();
        try (Connection con = DatabaseHandler.connect();
             Statement stmt = con.createStatement();
             ResultSet resultSet = stmt.executeQuery("SELECT * FROM Recipe")) {

            while (resultSet.next()) {
                Recipe recipe = extractRecipeFromResultSet(resultSet);
                recipes.add(recipe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipes;
    }

    @Override
    public void addRecipe(Recipe recipe) {
        // Implementierung zum Hinzufügen eines Rezepts zur Datenbank
    }

    @Override
    public void updateRecipe(Recipe recipe) {
        // Implementierung zum Aktualisieren eines Rezepts in der Datenbank
    }

    @Override
    public void deleteRecipe(int recipeId) {
        // Implementierung zum Löschen eines Rezepts aus der Datenbank
    }

    private Recipe extractRecipeFromResultSet(ResultSet resultSet) throws SQLException {
        Recipe recipe = new Recipe();
        recipe.setRid(resultSet.getInt("RID"));
        recipe.setrName(resultSet.getString("RName"));
        recipe.setDescription(resultSet.getString("Description"));
        recipe.setrLvl(resultSet.getInt("RLvl"));
        recipe.setDurationLvlID(resultSet.getInt("DurationLvlID"));
        recipe.setUserComment(resultSet.getString("UserComment"));
        recipe.setMealID(resultSet.getInt("MealID"));
        recipe.setIsVegan(resultSet.getInt("IsVegan"));
        recipe.setIsVegetarian(resultSet.getInt("IsVegetarian"));
        recipe.setIngredientListID(resultSet.getInt("IngredientListID"));
        // Setzen Sie hier die restlichen Eigenschaften des Rezepts entsprechend der Spalten in der Datenbank
        return recipe;
    }
}
