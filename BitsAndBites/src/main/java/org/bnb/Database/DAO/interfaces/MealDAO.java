package org.bnb.Database.DAO.interfaces;

import org.bnb.Database.Classes.MealDB;

import java.util.List;

public interface MealDAO {
    MealDB getMealById(int mealId);

    List<MealDB> getAllMeals();

    void addMeal(MealDB meal);

    void updateMeal(MealDB meal);

    void deleteMeal(int mealId);
}
