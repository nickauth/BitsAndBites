package org.bnb.Database.DAO.interfaces;

import org.bnb.Classes.Recipe;

import java.util.List;

public interface RecipeDAO {
    Recipe getRecipeById(int recipeId);

    List<Recipe> getAllRecipes();

    void addRecipe(Recipe recipe);

    void updateRecipe(Recipe recipe);

    void deleteRecipe(int recipeId);
}
