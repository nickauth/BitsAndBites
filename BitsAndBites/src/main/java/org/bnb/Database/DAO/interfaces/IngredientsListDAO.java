package org.bnb.Database.DAO.interfaces;

import org.bnb.Classes.IngredientsList;

import java.util.List;

public interface IngredientsListDAO {
    IngredientsList getIngredientsListById(int ingredientsListId);

    List<IngredientsList> getAllIngredientsLists();

    void addIngredientsList(IngredientsList ingredientsList);

    void updateIngredientsList(IngredientsList ingredientsList);

    void deleteIngredientsList(int ingredientsListId);
}
