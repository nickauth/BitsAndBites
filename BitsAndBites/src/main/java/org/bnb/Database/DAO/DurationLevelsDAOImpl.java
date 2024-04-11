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
        DurationLevels durationLevel = null;
        String query = "SELECT * FROM DurationLevels WHERE ID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, durationLevelsId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                durationLevel = new DurationLevels();
                durationLevel.setId(resultSet.getInt("ID"));
                durationLevel.setDescription(resultSet.getString("Description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return durationLevel;
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
    public void addDurationLevels(DurationLevels durationLevel) {
        String query = "INSERT INTO DurationLevels (Description) VALUES (?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, durationLevel.getDescription());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateDurationLevels(DurationLevels durationLevel) {
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

    private DurationLevels extractDurationLevelFromResultSet(ResultSet resultSet) throws SQLException {
        DurationLevels durationLevel = new DurationLevels();
        durationLevel.setId(resultSet.getInt("ID"));
        durationLevel.setDescription(resultSet.getString("Description"));
        return durationLevel;
    }
}
