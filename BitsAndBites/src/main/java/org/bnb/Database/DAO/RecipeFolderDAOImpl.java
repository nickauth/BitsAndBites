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
        return null;
    }

    @Override
    public List<RecipeFolder> getAllRecipeFolders() {
        List<RecipeFolder> recipeFolders = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM RecipeFolder");
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

    }

    @Override
    public void updateRecipeFolder(RecipeFolder recipeFolder) {

    }

    @Override
    public void deleteRecipeFolder(int recipeFolderId) {

    }

    private RecipeFolder extractRecipeFolderFromResultSet(ResultSet resultSet) throws SQLException {
        RecipeFolder recipeFolder = new RecipeFolder();
        recipeFolder.setId(resultSet.getInt("ROID"));
        recipeFolder.setRecipeID(resultSet.getInt("RecipeID"));
        recipeFolder.setFolderID(resultSet.getInt("FolderID"));
        return recipeFolder;
    }
}