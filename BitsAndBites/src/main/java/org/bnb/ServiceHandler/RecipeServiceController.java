package org.bnb.ServiceHandler;

import org.bnb.Database.Classes.IngredientsListDB;
import org.bnb.Database.Classes.RecipeDB;
import org.bnb.Database.Classes.RecipeFolderDB;
import org.bnb.Database.Classes.RecipeImagesDB;
import org.bnb.Database.DAO.*;
import org.bnb.Database.DatabaseHandler;
import org.bnb.Model.Folder;
import org.bnb.Model.Ingredient;
import org.bnb.Model.Recipe;
import org.bnb.Model.RecipeImage;

import java.util.ArrayList;
import java.util.List;

public class RecipeServiceController {
    private final RecipeDAOImpl recipeDAO = new RecipeDAOImpl(DatabaseHandler.connect());
    private final RecipeLvlDAOImpl lvlDAO = new RecipeLvlDAOImpl(DatabaseHandler.connect());
    private final DurationLevelsDAOImpl durationLevelsDAO = new DurationLevelsDAOImpl(DatabaseHandler.connect());
    private final MealDAOImpl mealDAO = new MealDAOImpl(DatabaseHandler.connect());
    private final IngredientsListDAOImpl ingredientsListDAO = new IngredientsListDAOImpl();
    private final IngredientsDAOImpl ingredientsDAO = new IngredientsDAOImpl(DatabaseHandler.connect());
    private final RecipeImagesDAOImpl recipeImagesDAO = new RecipeImagesDAOImpl(DatabaseHandler.connect());
    private final RecipeFolderDAOImpl recipeFolderDAO = new RecipeFolderDAOImpl(DatabaseHandler.connect());
    private final FolderDAOImpl folderDAO = new FolderDAOImpl(DatabaseHandler.connect());
    private Boolean booleanValue = null;
    private IngredientServiceController ingredientServiceController;
    private RecipeImageServiceController recipeImageServiceController;

    public Recipe getAllRecipeInfos(int recipeId) {
        RecipeDB dbRecipe = recipeDAO.getRecipeById(recipeId);
        if (dbRecipe == null) {
            return null; // Falls nicht, null zurückgeben
        }

        // Erstellen eines neuen Recipe-Objekts und Befüllen mit Daten aus dem RecipeDB
        Recipe recipe = new Recipe();
        recipe.setId(dbRecipe.getRid());
        recipe.setName(dbRecipe.getrName());
        recipe.setDescription(dbRecipe.getDescription());
        recipe.setLvl(lvlDAO.getRecipeLvlById(dbRecipe.getrLvl()).getDescription());
        recipe.setDuration(durationLevelsDAO.getDurationLevelsById(dbRecipe.getDurationLvlID()).getDescription());
        recipe.setUserComment(dbRecipe.getUserComment());
        recipe.setMeal(mealDAO.getMealById(dbRecipe.getMealID()).getDescription());
        booleanValue = (dbRecipe.getIsVegan() == 1);
        recipe.setVegan(booleanValue);
        booleanValue = (dbRecipe.getIsVegetarian() == 1);
        recipe.setVegetarian(booleanValue);

        // Zutatenliste füllen
        List<Ingredient> ingredientList = new ArrayList<>();
        for (IngredientsListDB listDB : ingredientsListDAO.getIngredientsListByRecipeId(recipe.getId())) {
            Ingredient ingredient = new Ingredient();
            ingredient.setName(ingredientsDAO.getIngredientsById(listDB.getIngredientID()).getName());
            ingredient.setAmount(listDB.getAmount());
            ingredient.setUnit(listDB.getUnit());
            ingredientList.add(ingredient);
        }
        recipe.setIngredients(ingredientList);

        // Rezeptbilder Liste füllen
        List<RecipeImage> imageList = new ArrayList<>();
        for (RecipeImagesDB imagesDBList : recipeImagesDAO.getRecipeImageById(recipe.getId())) {
            RecipeImage image = new RecipeImage();
            image.setImgAdress(imagesDBList.getImageAddress());
        }
        recipe.setImageList(imageList);

        // Ordnerliste füllen
        List<Folder> folderList = new ArrayList<>();
        for (RecipeFolderDB folderDBList : recipeFolderDAO.getRecipeFoldersByRecipeId(recipe.getId())) {
            Folder folder = new Folder();
            folder.setFolderName(folderDAO.getFolderById(folderDBList.getFolderID()).getFolderName());
        }
        recipe.setFolderList(folderList);


        // Vollständiges Recipe-Objekt zurückgeben
        return recipe;
    }

    public void insertNewRecipe(Recipe newRecipe){
        RecipeDB newRecipeDB = new RecipeDB();
        newRecipeDB.setRid(recipeDAO.getNextRecipeId());
        newRecipeDB.setrName(newRecipe.getName());
        newRecipeDB.setDescription(newRecipe.getDescription());
        newRecipeDB.setUserComment(newRecipe.getUserComment());
        newRecipeDB.setMealID(
                mealDAO.getMealIdByDescription(newRecipe.getMeal())
        );
        boolean bool2int = newRecipe.isVegan();
        newRecipeDB.setIsVegan(bool2int ? 1 : 0);
        bool2int = newRecipe.isVegetarian();
        newRecipeDB.setIsVegetarian(bool2int ? 1 : 0);
        ingredientServiceController.insertIngredients4NewRecipe(newRecipe.getIngredients(), newRecipe.getId());
        recipeImageServiceController.insertNewImages4NewRecipe(newRecipe.getImageList(), newRecipe.getId());
        recipeDAO.addRecipe(newRecipeDB);
    }
}
