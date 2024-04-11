package org.bnb.Database.DAO;

import org.bnb.Database.DAO.interfaces.RecipeFolderDAO;
import org.bnb.Database.Classes.RecipeFolderDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecipeFolderDAOImpl implements RecipeFolderDAO {

    private final Connection connection;

    public RecipeFolderDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public RecipeFolderDB getRecipeFolderById(int recipeFolderId) {
        RecipeFolderDB recipeFolderDB = null;
        String query = "SELECT * FROM RecipeFolder WHERE ROID = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, recipeFolderId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                recipeFolderDB = extractRecipeFolderFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return recipeFolderDB;
    }

    @Override
    public List<RecipeFolderDB> getRecipeFoldersByRecipeId(int recipeId) {
        List<RecipeFolderDB> recipeFolderDBS = new ArrayList<>();
        String query = "SELECT * FROM RecipeFolder WHERE RecipeID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, recipeId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                RecipeFolderDB recipeFolderDB = extractRecipeFolderFromResultSet(resultSet);
                recipeFolderDBS.add(recipeFolderDB);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return recipeFolderDBS;
    }

    @Override
    public List<RecipeFolderDB> getAllRecipeFolders() {
        List<RecipeFolderDB> recipeFolderDBS = new ArrayList<>();
        String query = "SELECT * FROM RecipeFolder";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                RecipeFolderDB recipeFolderDB = extractRecipeFolderFromResultSet(resultSet);
                recipeFolderDBS.add(recipeFolderDB);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return recipeFolderDBS;
    }

    @Override
    public void addRecipeFolder(RecipeFolderDB recipeFolderDB) {
        String query = "INSERT INTO RecipeFolder (RecipeID, FolderID) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, recipeFolderDB.getRecipeID());
            preparedStatement.setInt(2, recipeFolderDB.getFolderID());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateRecipeFolder(RecipeFolderDB recipeFolderDB) {
        String query = "UPDATE RecipeFolder SET RecipeID = ?, FolderID = ? WHERE ROID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, recipeFolderDB.getRecipeID());
            preparedStatement.setInt(2, recipeFolderDB.getFolderID());
            preparedStatement.setInt(3, recipeFolderDB.getId());

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

    private RecipeFolderDB extractRecipeFolderFromResultSet(ResultSet resultSet) throws SQLException {
        RecipeFolderDB recipeFolderDB = new RecipeFolderDB();
        recipeFolderDB.setId(resultSet.getInt("ROID"));
        recipeFolderDB.setRecipeID(resultSet.getInt("RecipeID"));
        recipeFolderDB.setFolderID(resultSet.getInt("FolderID"));
        return recipeFolderDB;
    }
}