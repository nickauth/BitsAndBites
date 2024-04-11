package org.bnb.Database.DAO.interfaces;

import org.bnb.Database.Classes.IngredientsDB;

import java.util.List;

public interface IngredientsDAO {
    IngredientsDB getIngredientsById(int ingredientsId);

    List<IngredientsDB> getAllIngredients();

    void addIngredients(IngredientsDB ingredientsDB);

    void updateIngredients(IngredientsDB ingredientsDB);

    void deleteIngredients(int ingredientsId);
}
