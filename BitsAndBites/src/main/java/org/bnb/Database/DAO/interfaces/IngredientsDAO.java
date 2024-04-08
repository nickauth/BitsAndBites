package org.bnb.Database.DAO.interfaces;

import org.bnb.Classes.Ingredients;

import java.util.List;

public interface IngredientsDAO {
    Ingredients getIngredientsById(int ingredientsId);

    List<Ingredients> getAllIngredients();

    void addIngredients(Ingredients ingredients);

    void updateIngredients(Ingredients ingredients);

    void deleteIngredients(int ingredientsId);
}
