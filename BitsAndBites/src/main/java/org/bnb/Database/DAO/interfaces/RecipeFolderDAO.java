package org.bnb.Database.DAO.interfaces;

import org.bnb.Database.Classes.RecipeFolderDB;

import java.util.List;

public interface RecipeFolderDAO {
    RecipeFolderDB getRecipeFolderById(int recipeFolderId);

    List<RecipeFolderDB> getRecipeFoldersByRecipeId(int recipeId);

    List<RecipeFolderDB> getAllRecipeFolders();

    void addRecipeFolder(RecipeFolderDB recipeFolderDB);

    void updateRecipeFolder(RecipeFolderDB recipeFolderDB);

    void deleteRecipeFolder(int recipeFolderId);
}
