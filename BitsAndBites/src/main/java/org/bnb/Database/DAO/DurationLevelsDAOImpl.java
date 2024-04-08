package org.bnb.Database.DAO;

import org.bnb.Database.DAO.interfaces.DurationLevelsDAO;
import org.bnb.Classes.DurationLevels;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DurationLevelsDAOImpl implements DurationLevelsDAO {

    private final Connection connection;

    public DurationLevelsDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public DurationLevels getDurationLevelsById(int durationLevelsId) {
        return null;
    }

    @Override
    public List<DurationLevels> getAllDurationLevels() {
        List<DurationLevels> durationLevels = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM DurationLevels");
            while (resultSet.next()) {
                DurationLevels durationLevel = extractDurationLevelFromResultSet(resultSet);
                durationLevels.add(durationLevel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return durationLevels;
    }

    @Override
    public void addDurationLevels(DurationLevels durationLevels) {

    }

    @Override
    public void updateDurationLevels(DurationLevels durationLevels) {

    }

    @Override
    public void deleteDurationLevels(int durationLevelsId) {

    }

    private DurationLevels extractDurationLevelFromResultSet(ResultSet resultSet) throws SQLException {
        DurationLevels durationLevel = new DurationLevels();
        durationLevel.setId(resultSet.getInt("ID"));
        durationLevel.setDescription(resultSet.getString("Description"));
        return durationLevel;
    }
}
