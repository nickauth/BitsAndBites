package org.bnb.Database.DAO;

import org.bnb.Classes.RecipeImages;
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
    public List<RecipeImages> getRecipeImageById(int recipeId) {
        List<RecipeImages> recipeImagesList = new ArrayList<>();
        String query = "SELECT * FROM RecipeImages WHERE RecipeID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, recipeId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                RecipeImages recipeImages = extractRecipeImageFromResultSet(resultSet);
                recipeImagesList.add(recipeImages);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return recipeImagesList;
    }

    @Override
    public List<RecipeImages> getAllRecipeImages() {
        List<RecipeImages> recipeImages = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM RecipeImages");
            while (resultSet.next()) {
                RecipeImages recipeImage = extractRecipeImageFromResultSet(resultSet);
                recipeImages.add(recipeImage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipeImages;
    }

    @Override
    public void addRecipeImage(RecipeImages recipeImage) {
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
    public void updateRecipeImage(RecipeImages recipeImage) {
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

    private RecipeImages extractRecipeImageFromResultSet(ResultSet resultSet) throws SQLException {
        RecipeImages recipeImage = new RecipeImages();
        recipeImage.setImageID(resultSet.getInt("ImageID"));
        recipeImage.setRecipeID(resultSet.getInt("RecipeID"));
        recipeImage.setImageAddress(resultSet.getString("ImageAddress"));
        return recipeImage;
    }
}