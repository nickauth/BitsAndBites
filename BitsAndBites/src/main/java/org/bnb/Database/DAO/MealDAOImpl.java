package org.bnb.Database.DAO;

import org.bnb.Database.DAO.interfaces.MealDAO;
import org.bnb.Database.Classes.MealDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MealDAOImpl implements MealDAO {

    private final Connection connection;

    public MealDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public MealDB getMealById(int mealId) {
        MealDB meal = null;
        String query = "SELECT * FROM Meal WHERE MID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, mealId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                meal = extractMealFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return meal;
    }

    @Override
    public int getMealIdByDescription(String description) {
        try {String query = "SELECT mealId FROM Meals WHERE description = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, description);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("mealId");
            } else {
                return -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1; // Rückgabe eines negativen Werts im Fehlerfall
        }
    }

    @Override
    public List<MealDB> getAllMeals() {
        List<MealDB> meals = new ArrayList<>();
        String query = "SELECT * FROM Meal";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                MealDB meal = extractMealFromResultSet(resultSet);
                meals.add(meal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return meals;
    }

    @Override
    public void addMeal(MealDB meal) {
        String query = "INSERT INTO Meal (Description) VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, meal.getDescription());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateMeal(MealDB meal) {
        String query = "UPDATE Meal SET Description = ? WHERE MID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, meal.getDescription());
            preparedStatement.setInt(2, meal.getMid());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteMeal(int mealId) {
        String query = "DELETE FROM Meal WHERE MID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, mealId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private MealDB extractMealFromResultSet(ResultSet resultSet) throws SQLException {
        MealDB meal = new MealDB();
        meal.setMid(resultSet.getInt("MID"));
        meal.setDescription(resultSet.getString("Description"));
        return meal;
    }
}