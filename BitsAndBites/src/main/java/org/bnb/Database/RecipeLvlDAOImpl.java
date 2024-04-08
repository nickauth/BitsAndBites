package org.bnb.Database;

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
    public List<RecipeLvl> getAllRecipeLevels() {
        List<RecipeLvl> recipeLevels = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM RecipeLvl");
            while (resultSet.next()) {
                RecipeLvl recipeLevel = extractRecipeLevelFromResultSet(resultSet);
                recipeLevels.add(recipeLevel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipeLevels;
    }

    private RecipeLvl extractRecipeLevelFromResultSet(ResultSet resultSet) throws SQLException {
        RecipeLvl recipeLevel = new RecipeLvl();
        recipeLevel.setLvlID(resultSet.getInt("LvlID"));
        recipeLevel.setDescription(resultSet.getString("Description"));
        return recipeLevel;
    }

    @Override
    public RecipeLvl getRecipeLvlById(int recipeLvlId) {
        return null;
    }

    @Override
    public List<RecipeLvl> getAllRecipeLvls() {
        return null;
    }

    @Override
    public void addRecipeLvl(RecipeLvl recipeLvl) {

    }

    @Override
    public void updateRecipeLvl(RecipeLvl recipeLvl) {

    }

    @Override
    public void deleteRecipeLvl(int recipeLvlId) {

    }
}