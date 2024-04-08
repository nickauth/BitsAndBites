package org.bnb.Database.DAO.interfaces;

import org.bnb.Classes.Meal;

import java.util.List;

public interface MealDAO {
    Meal getMealById(int mealId);

    List<Meal> getAllMeals();

    void addMeal(Meal meal);

    void updateMeal(Meal meal);

    void deleteMeal(int mealId);
}
