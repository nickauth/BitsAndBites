package org.bnb.Database.DAO.interfaces;

import org.bnb.Database.Classes.RecipeDB;

import java.util.List;

public interface RecipeDAO {
    List<RecipeDB> getAllRecipes();

    RecipeDB getRecipeById(int recipeId);

    List<RecipeDB> getRecipeByLvl(int recipeLvl);

    List<RecipeDB> getRecipeByDuration(int durationLvl);

    List<RecipeDB> getRecipeByMeal(int mealId);

    List<RecipeDB> getRecipeByIsVegan(boolean isVegan);

    List<RecipeDB> getRecipeByIsVegetarian(boolean isVegetarian);

    List<RecipeDB> getRecipesByFilters(int level, int durationLevel, boolean isVegan, boolean isVegetarian, int mealId);

    void addRecipe(RecipeDB recipe);

    void updateRecipe(RecipeDB recipe);

    void deleteRecipe(int recipeId);
}
