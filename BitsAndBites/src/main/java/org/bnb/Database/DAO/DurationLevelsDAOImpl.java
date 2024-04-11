package org.bnb.Database.DAO;

import org.bnb.Database.DAO.interfaces.DurationLevelsDAO;
import org.bnb.Database.Classes.DurationLevelsDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DurationLevelsDAOImpl implements DurationLevelsDAO {

    private final Connection connection;

    public DurationLevelsDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public DurationLevelsDB getDurationLevelsById(int durationLevelsId) {
        DurationLevelsDB durationLevel = null;
        String query = "SELECT * FROM DurationLevels WHERE ID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, durationLevelsId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                durationLevel = new DurationLevelsDB();
                durationLevel.setId(resultSet.getInt("ID"));
                durationLevel.setDescription(resultSet.getString("Description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return durationLevel;
    }

    @Override
    public List<DurationLevelsDB> getAllDurationLevels() {
        List<DurationLevelsDB> durationLevelDBS = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM DurationLevels");
            while (resultSet.next()) {
                DurationLevelsDB durationLevel = extractDurationLevelFromResultSet(resultSet);
                durationLevelDBS.add(durationLevel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return durationLevelDBS;
    }

    @Override
    public void addDurationLevels(DurationLevelsDB durationLevel) {
        String query = "INSERT INTO DurationLevels (Description) VALUES (?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, durationLevel.getDescription());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateDurationLevels(DurationLevelsDB durationLevel) {
        String query = "UPDATE DurationLevels SET Description = ? WHERE ID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, durationLevel.getDescription());
            preparedStatement.setInt(2, durationLevel.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteDurationLevels(int durationLevelId) {
        String query = "DELETE FROM DurationLevels WHERE ID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, durationLevelId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private DurationLevelsDB extractDurationLevelFromResultSet(ResultSet resultSet) throws SQLException {
        DurationLevelsDB durationLevel = new DurationLevelsDB();
        durationLevel.setId(resultSet.getInt("ID"));
        durationLevel.setDescription(resultSet.getString("Description"));
        return durationLevel;
    }
}
