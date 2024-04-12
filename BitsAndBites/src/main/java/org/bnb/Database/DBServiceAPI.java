package org.bnb.Database;

import org.bnb.Database.Classes.*;
import org.bnb.Database.DAO.*;
import org.bnb.Database.DAO.interfaces.RecipeDAO;
import org.bnb.Model.Folder;
import org.bnb.Model.Ingredient;
import org.bnb.Model.Recipe;
import org.bnb.Model.RecipeImage;
import org.bnb.ServiceHandler.RecipeServiceController;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Zentrale Schnittstelle für die Interaktion zwischen Geschäftslogik und Datenbank
 * Hier findet auch das Mapping der DB-Klassen auf die Serviceklassen statt
 * */
public class DBServiceAPI {

    private RecipeServiceController recipeServiceController;


    public Recipe getFullRecipe(int recipeId) {
        return recipeServiceController.getAllRecipeInfos(recipeId);
    }

}
