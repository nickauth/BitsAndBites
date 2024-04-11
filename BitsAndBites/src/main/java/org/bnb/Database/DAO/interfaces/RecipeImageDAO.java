package org.bnb.Database.DAO.interfaces;

import org.bnb.Database.Classes.RecipeImagesDB;

import java.util.List;

public interface RecipeImageDAO {
    List<RecipeImagesDB> getRecipeImageById(int recipeImageId);

    List<RecipeImagesDB> getAllRecipeImages();

    void addRecipeImage(RecipeImagesDB recipeImage);

    void updateRecipeImage(RecipeImagesDB recipeImage);

    void deleteRecipeImage(int recipeImageId);
}
