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
        Meal meal = null;
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
    public List<Meal> getAllMeals() {
        List<Meal> meals = new ArrayList<>();
        String query = "SELECT * FROM Meal";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
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
        String query = "INSERT INTO Meal (Description) VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, meal.getDescription());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateMeal(Meal meal) {
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

    private Meal extractMealFromResultSet(ResultSet resultSet) throws SQLException {
        Meal meal = new Meal();
        meal.setMid(resultSet.getInt("MID"));
        meal.setDescription(resultSet.getString("Description"));
        return meal;
    }
}