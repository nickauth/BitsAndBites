package org.bnb.Database.DAO.interfaces;

import org.bnb.Classes.RecipeImages;

import java.util.List;

public interface RecipeImageDAO {
    RecipeImages getRecipeImageById(int recipeImageId);

    List<RecipeImages> getAllRecipeImages();

    void addRecipeImage(RecipeImages recipeImage);

    void updateRecipeImage(RecipeImages recipeImage);

    void deleteRecipeImage(int recipeImageId);
}
