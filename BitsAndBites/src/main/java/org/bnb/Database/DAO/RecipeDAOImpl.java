package org.bnb.Database.DAO;

import org.bnb.Database.Classes.RecipeDB;
import org.bnb.Database.DatabaseHandler;
import org.bnb.Database.DAO.interfaces.RecipeDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecipeDAOImpl implements RecipeDAO {

    private final Connection connection;

    public RecipeDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public RecipeDB getRecipeById(int recipeId) {
        RecipeDB recipe = null;
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Recipe WHERE RID = ?")) {
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
    public List<RecipeDB> getRecipeByLvl(int recipeLvl) {
        List<RecipeDB> recipes = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM Recipe WHERE RLvl = ?")) {
            preparedStatement.setInt(1, recipeLvl);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    RecipeDB recipe = extractRecipeFromResultSet(resultSet);
                    recipes.add(recipe);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipes;
    }

    @Override
    public List<RecipeDB> getRecipeByDuration(int durationLvl) {
        List<RecipeDB> recipes = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM Recipe WHERE DurationLvlID = ?")) {
            preparedStatement.setInt(1, durationLvl);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    RecipeDB recipe = extractRecipeFromResultSet(resultSet);
                    recipes.add(recipe);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipes;
    }

    @Override
    public List<RecipeDB> getRecipeByMeal(int mealId) {
        List<RecipeDB> recipes = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM Recipe WHERE MealID = ?")) {
            preparedStatement.setInt(1, mealId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    RecipeDB recipe = extractRecipeFromResultSet(resultSet);
                    recipes.add(recipe);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipes;
    }

    @Override
    public List<RecipeDB> getRecipeByIsVegan(boolean isVegan) {
        int veganInt = isVegan ? 1 : 0;
        List<RecipeDB> recipes = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM Recipe WHERE IsVegan = ?")) {
            preparedStatement.setInt(1, veganInt);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    RecipeDB recipe = extractRecipeFromResultSet(resultSet);
                    recipes.add(recipe);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipes;
    }

    @Override
    public List<RecipeDB> getRecipeByIsVegetarian(boolean isVegetarian) {
        int vegetarianInt = isVegetarian ? 1 : 0;
        List<RecipeDB> recipes = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM Recipe WHERE IsVegetarian = ?")) {
            preparedStatement.setInt(1, vegetarianInt);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    RecipeDB recipe = extractRecipeFromResultSet(resultSet);
                    recipes.add(recipe);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipes;
    }

    @Override
    public List<RecipeDB> getRecipesByFilters(int level, int durationLevel, boolean isVegan, boolean isVegetarian, int mealId) {
        int veganInt = isVegan ? 1 : 0;
        int vegetarianInt = isVegetarian ? 1 : 0;
        List<RecipeDB> recipes = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM Recipe WHERE RLvl = ? AND DurationLvlID = ? AND IsVegan = ? AND IsVegetarian = ? AND MealID = ?")) {
            preparedStatement.setInt(1, level);
            preparedStatement.setInt(2, durationLevel);
            preparedStatement.setInt(3, veganInt);
            preparedStatement.setInt(4, vegetarianInt);
            preparedStatement.setInt(5, mealId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    RecipeDB recipe = extractRecipeFromResultSet(resultSet);
                    recipes.add(recipe);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipes;
    }

    @Override
    public List<RecipeDB> getAllRecipes() {
        List<RecipeDB> recipes = new ArrayList<>();
        try (Connection con = DatabaseHandler.connect();
             Statement stmt = con.createStatement();
             ResultSet resultSet = stmt.executeQuery("SELECT * FROM Recipe")) {

            while (resultSet.next()) {
                RecipeDB recipe = extractRecipeFromResultSet(resultSet);
                recipes.add(recipe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipes;
    }

    @Override
    public void addRecipe(RecipeDB recipe) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Recipe (RName, Description, RLvl, DurationLvlID, UserComment, MealID, IsVegan, IsVegetarian, IngredientListID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, recipe.getrName());
            preparedStatement.setString(2, recipe.getDescription());
            preparedStatement.setInt(3, recipe.getrLvl());
            preparedStatement.setInt(4, recipe.getDurationLvlID());
            preparedStatement.setString(5, recipe.getUserComment());
            preparedStatement.setInt(6, recipe.getMealID());
            preparedStatement.setInt(7, recipe.getIsVegan());
            preparedStatement.setInt(8, recipe.getIsVegetarian());
            preparedStatement.setInt(9, recipe.getIngredientListID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateRecipe(RecipeDB recipe) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Recipe SET RName = ?, Description = ?, RLvl = ?, DurationLvlID = ?, UserComment = ?, MealID = ?, IsVegan = ?, IsVegetarian = ?, IngredientListID = ? WHERE RID = ?");
            preparedStatement.setString(1, recipe.getrName());
            preparedStatement.setString(2, recipe.getDescription());
            preparedStatement.setInt(3, recipe.getrLvl());
            preparedStatement.setInt(4, recipe.getDurationLvlID());
            preparedStatement.setString(5, recipe.getUserComment());
            preparedStatement.setInt(6, recipe.getMealID());
            preparedStatement.setInt(7, recipe.getIsVegan());
            preparedStatement.setInt(8, recipe.getIsVegetarian());
            preparedStatement.setInt(9, recipe.getIngredientListID());
            preparedStatement.setInt(10, recipe.getRid());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteRecipe(int recipeId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Recipe WHERE RID = ?");
            preparedStatement.setInt(1, recipeId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private RecipeDB extractRecipeFromResultSet(ResultSet resultSet) throws SQLException {
        RecipeDB recipe = new RecipeDB();
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
