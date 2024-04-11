package org.bnb.Database.DAO;

import org.bnb.Classes.RecipeLvl;
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
    public RecipeLvl getRecipeLvlById(int lvlId) {
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
    public List<RecipeLvl> getAllRecipeLvls() {
        List<RecipeLvl> recipeLvls = new ArrayList<>();
        String query = "SELECT * FROM RecipeLvl";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                RecipeLvl recipeLvl = extractRecipeLvlFromResultSet(resultSet);
                recipeLvls.add(recipeLvl);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return recipeLvls;
    }

    @Override
    public void addRecipeLvl(RecipeLvl recipeLvl) {
        String query = "INSERT INTO RecipeLvl (Description) VALUES (?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, recipeLvl.getDescription());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateRecipeLvl(RecipeLvl recipeLvl) {
        String query = "UPDATE RecipeLvl SET Description = ? WHERE LvlID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, recipeLvl.getDescription());
            preparedStatement.setInt(2, recipeLvl.getLvlID());
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

    private RecipeLvl extractRecipeLvlFromResultSet(ResultSet resultSet) throws SQLException {
        RecipeLvl recipeLevel = new RecipeLvl();
        recipeLevel.setLvlID(resultSet.getInt("LvlID"));
        recipeLevel.setDescription(resultSet.getString("Description"));
        return recipeLevel;
    }
}