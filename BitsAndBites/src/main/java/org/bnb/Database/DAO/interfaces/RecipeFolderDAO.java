package org.bnb.Database.DAO.interfaces;

import org.bnb.Classes.RecipeFolder;

import java.util.List;

public interface RecipeFolderDAO {
    RecipeFolder getRecipeFolderById(int recipeFolderId);

    List<RecipeFolder> getAllRecipeFolders();

    void addRecipeFolder(RecipeFolder recipeFolder);

    void updateRecipeFolder(RecipeFolder recipeFolder);

    void deleteRecipeFolder(int recipeFolderId);
}
