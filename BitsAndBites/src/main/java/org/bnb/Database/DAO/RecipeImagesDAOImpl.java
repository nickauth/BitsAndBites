package org.bnb.Database.DAO;

import org.bnb.Database.Classes.RecipeImagesDB;
import org.bnb.Database.DAO.interfaces.RecipeImageDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecipeImagesDAOImpl implements RecipeImageDAO {

    private final Connection connection;

    public RecipeImagesDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<RecipeImagesDB> getRecipeImageById(int recipeId) {
        List<RecipeImagesDB> recipeImagesDBList = new ArrayList<>();
        String query = "SELECT * FROM RecipeImages WHERE RecipeID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, recipeId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                RecipeImagesDB recipeImagesDB = extractRecipeImageFromResultSet(resultSet);
                recipeImagesDBList.add(recipeImagesDB);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return recipeImagesDBList;
    }

    @Override
    public List<RecipeImagesDB> getAllRecipeImages() {
        List<RecipeImagesDB> recipeImageDBS = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM RecipeImages");
            while (resultSet.next()) {
                RecipeImagesDB recipeImage = extractRecipeImageFromResultSet(resultSet);
                recipeImageDBS.add(recipeImage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipeImageDBS;
    }

    @Override
    public void addRecipeImage(RecipeImagesDB recipeImage) {
        String query = "INSERT INTO RecipeImages (RecipeID, ImageAddress) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, recipeImage.getRecipeID());
            preparedStatement.setString(2, recipeImage.getImageAddress());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateRecipeImage(RecipeImagesDB recipeImage) {
        String query = "UPDATE RecipeImages SET ImageAddress = ? WHERE ImageID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, recipeImage.getImageAddress());
            preparedStatement.setInt(2, recipeImage.getImageID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteRecipeImage(int imageId) {
        String query = "DELETE FROM RecipeImages WHERE ImageID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, imageId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private RecipeImagesDB extractRecipeImageFromResultSet(ResultSet resultSet) throws SQLException {
        RecipeImagesDB recipeImage = new RecipeImagesDB();
        recipeImage.setImageID(resultSet.getInt("ImageID"));
        recipeImage.setRecipeID(resultSet.getInt("RecipeID"));
        recipeImage.setImageAddress(resultSet.getString("ImageAddress"));
        return recipeImage;
    }
}