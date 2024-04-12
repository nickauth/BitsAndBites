package org.bnb.ServiceHandler;

import org.bnb.Database.Classes.RecipeImagesDB;
import org.bnb.Database.DAO.RecipeImagesDAOImpl;
import org.bnb.Database.DatabaseHandler;
import org.bnb.Model.RecipeImage;

import java.util.List;

public class RecipeImageServiceController {
    private final RecipeImagesDAOImpl recipeImagesDAO = new RecipeImagesDAOImpl(DatabaseHandler.connect());


    public void insertNewImages4NewRecipe(List<RecipeImage> recipeImageList, int recipeId){
        for (RecipeImage image : recipeImageList){
            RecipeImagesDB imagesDB = new RecipeImagesDB();
            imagesDB.setImageAddress(image.getImgAdress());
            imagesDB.setRecipeID(recipeId);
            recipeImagesDAO.addRecipeImage(imagesDB);
        }
    }
}
