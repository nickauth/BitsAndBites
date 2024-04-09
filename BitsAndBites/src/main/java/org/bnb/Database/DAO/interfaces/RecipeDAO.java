package org.bnb.Database.DAO.interfaces;

import org.bnb.Classes.Ingredients;
import org.bnb.Classes.Recipe;

import java.util.List;

public interface RecipeDAO {
    List<Recipe> getAllRecipes();

    Recipe getRecipeById(int recipeId);

    List<Recipe> getRecipeByLvl(int recipeLvl);

    List<Recipe> getRecipeByDuration(int durationLvl);

    List<Recipe> getRecipeByMeal(int mealId);

    List<Recipe> getRecipeByIsVegan(boolean isVegan);

    List<Recipe> getRecipeByIsVegetarian(boolean isVegetarian);

    List<Recipe> getRecipesByFilters(int level, int durationLevel, boolean isVegan, boolean isVegetarian, int mealId);

    void addRecipe(Recipe recipe);

    void updateRecipe(Recipe recipe);

    void deleteRecipe(int recipeId);
}
