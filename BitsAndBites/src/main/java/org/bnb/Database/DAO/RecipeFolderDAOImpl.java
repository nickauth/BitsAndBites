package org.bnb.Database.DAO;

import org.bnb.Database.DAO.interfaces.RecipeFolderDAO;
import org.bnb.Classes.RecipeFolder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecipeFolderDAOImpl implements RecipeFolderDAO {

    private final Connection connection;

    public RecipeFolderDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public RecipeFolder getRecipeFolderById(int recipeFolderId) {
        RecipeFolder recipeFolder = null;
        String query = "SELECT * FROM RecipeFolder WHERE ROID = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, recipeFolderId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                recipeFolder = extractRecipeFolderFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return recipeFolder;
    }

    @Override
    public List<RecipeFolder> getRecipeFoldersByRecipeId(int recipeId) {
        List<RecipeFolder> recipeFolders = new ArrayList<>();
        String query = "SELECT * FROM RecipeFolder WHERE RecipeID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, recipeId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                RecipeFolder recipeFolder = extractRecipeFolderFromResultSet(resultSet);
                recipeFolders.add(recipeFolder);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return recipeFolders;
    }

    @Override
    public List<RecipeFolder> getAllRecipeFolders() {
        List<RecipeFolder> recipeFolders = new ArrayList<>();
        String query = "SELECT * FROM RecipeFolder";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                RecipeFolder recipeFolder = extractRecipeFolderFromResultSet(resultSet);
                recipeFolders.add(recipeFolder);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return recipeFolders;
    }

    @Override
    public void addRecipeFolder(RecipeFolder recipeFolder) {
        String query = "INSERT INTO RecipeFolder (RecipeID, FolderID) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, recipeFolder.getRecipeID());
            preparedStatement.setInt(2, recipeFolder.getFolderID());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateRecipeFolder(RecipeFolder recipeFolder) {
        String query = "UPDATE RecipeFolder SET RecipeID = ?, FolderID = ? WHERE ROID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, recipeFolder.getRecipeID());
            preparedStatement.setInt(2, recipeFolder.getFolderID());
            preparedStatement.setInt(3, recipeFolder.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteRecipeFolder(int recipeFolderId) {
        String query = "DELETE FROM RecipeFolder WHERE ROID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, recipeFolderId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private RecipeFolder extractRecipeFolderFromResultSet(ResultSet resultSet) throws SQLException {
        RecipeFolder recipeFolder = new RecipeFolder();
        recipeFolder.setId(resultSet.getInt("ROID"));
        recipeFolder.setRecipeID(resultSet.getInt("RecipeID"));
        recipeFolder.setFolderID(resultSet.getInt("FolderID"));
        return recipeFolder;
    }
}