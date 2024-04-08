package org.bnb.Database.DAO;

import org.bnb.Database.DAO.interfaces.MealDAO;
import org.bnb.Classes.Meal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MealDAOImpl implements MealDAO {

    private final Connection connection;

    public MealDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Meal getMealById(int mealId) {
        return null;
    }

    @Override
    public List<Meal> getAllMeals() {
        List<Meal> meals = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Meal");
            while (resultSet.next()) {
                Meal meal = extractMealFromResultSet(resultSet);
                meals.add(meal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return meals;
    }

    @Override
    public void addMeal(Meal meal) {

    }

    @Override
    public void updateMeal(Meal meal) {

    }

    @Override
    public void deleteMeal(int mealId) {

    }

    private Meal extractMealFromResultSet(ResultSet resultSet) throws SQLException {
        Meal meal = new Meal();
        meal.setMid(resultSet.getInt("MID"));
        meal.setDescription(resultSet.getString("Description"));
        return meal;
    }
}