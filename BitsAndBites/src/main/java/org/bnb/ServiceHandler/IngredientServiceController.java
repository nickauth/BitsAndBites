package org.bnb.ServiceHandler;

import org.bnb.Database.Classes.IngredientsListDB;
import org.bnb.Database.DAO.IngredientsDAOImpl;
import org.bnb.Database.DAO.IngredientsListDAOImpl;
import org.bnb.Database.DatabaseHandler;
import org.bnb.Model.Ingredient;

import java.util.List;

public class IngredientServiceController {
    private final IngredientsListDAOImpl ingredientsListDAO = new IngredientsListDAOImpl();
    private final IngredientsDAOImpl ingredientsDAO = new IngredientsDAOImpl(DatabaseHandler.connect());


    public void insertIngredients4NewRecipe(List<Ingredient> ingredientList,int recipeId){
        for (Ingredient ingredient : ingredientList){
            IngredientsListDB ingredientsListDB = new IngredientsListDB();
            ingredientsListDB.setRecipeID(recipeId);
            ingredientsListDB.setIngredientID(ingredientsDAO.getOrInsertIngredientId(ingredient.getName()));
            ingredientsListDB.setAmount(ingredient.getAmount());
            ingredientsListDB.setUnit(ingredient.getUnit());
            ingredientsListDAO.addIngredientsList(ingredientsListDB);
        }
    }

}
