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
    public RecipeImages getRecipeImageById(int recipeImageId) {
        return null;
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

    }

    @Override
    public void updateRecipeImage(RecipeImages recipeImage) {

    }

    @Override
    public void deleteRecipeImage(int recipeImageId) {

    }

    private RecipeImages extractRecipeImageFromResultSet(ResultSet resultSet) throws SQLException {
        RecipeImages recipeImage = new RecipeImages();
        recipeImage.setImageID(resultSet.getInt("ImageID"));
        recipeImage.setRecipeID(resultSet.getInt("RecipeID"));
        recipeImage.setImageAddress(resultSet.getString("ImageAddress"));
        return recipeImage;
    }
}