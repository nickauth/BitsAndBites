package org.example.Database.DAO.interfaces;

import org.example.Classes.Recipe;

import java.util.List;

public interface RecipeDAO {
    Recipe getRecipeById(int recipeId);

    List<Recipe> getAllRecipes();

    void addRecipe(Recipe recipe);

    void updateRecipe(Recipe recipe);

    void deleteRecipe(int recipeId);
}
