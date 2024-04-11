package org.bnb.Database.DAO;

import org.bnb.Database.Classes.RecipeLvlDB;
import org.bnb.Database.DAO.interfaces.RecipeLvlDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecipeLvlDAOImpl implements RecipeLvlDAO {

    private final Connection connection;

    public RecipeLvlDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public RecipeLvlDB getRecipeLvlById(int lvlId) {
        String query = "SELECT * FROM RecipeLvl WHERE LvlID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, lvlId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return extractRecipeLvlFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<RecipeLvlDB> getAllRecipeLvls() {
        List<RecipeLvlDB> recipeLvlDBS = new ArrayList<>();
        String query = "SELECT * FROM RecipeLvl";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                RecipeLvlDB recipeLvlDB = extractRecipeLvlFromResultSet(resultSet);
                recipeLvlDBS.add(recipeLvlDB);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return recipeLvlDBS;
    }

    @Override
    public void addRecipeLvl(RecipeLvlDB recipeLvlDB) {
        String query = "INSERT INTO RecipeLvl (Description) VALUES (?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, recipeLvlDB.getDescription());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateRecipeLvl(RecipeLvlDB recipeLvlDB) {
        String query = "UPDATE RecipeLvl SET Description = ? WHERE LvlID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, recipeLvlDB.getDescription());
            preparedStatement.setInt(2, recipeLvlDB.getLvlID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteRecipeLvl(int lvlId) {
        String query = "DELETE FROM RecipeLvl WHERE LvlID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, lvlId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private RecipeLvlDB extractRecipeLvlFromResultSet(ResultSet resultSet) throws SQLException {
        RecipeLvlDB recipeLevel = new RecipeLvlDB();
        recipeLevel.setLvlID(resultSet.getInt("LvlID"));
        recipeLevel.setDescription(resultSet.getString("Description"));
        return recipeLevel;
    }
}