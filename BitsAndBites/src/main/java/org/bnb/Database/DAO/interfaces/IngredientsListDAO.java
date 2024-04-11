package org.bnb.Database.DAO.interfaces;

import org.bnb.Database.Classes.IngredientsListDB;

import java.util.List;

public interface IngredientsListDAO {
    IngredientsListDB getIngredientsListById(int ingredientsListId);

    List<IngredientsListDB> getIngredientsListByRecipeId(int recipeId);

    List<IngredientsListDB> getAllIngredientsLists();

    void addIngredientsList(IngredientsListDB ingredientsListDB);

    void updateIngredientsList(IngredientsListDB ingredientsListDB);

    void deleteIngredientsList(int ingredientsListId);
}
