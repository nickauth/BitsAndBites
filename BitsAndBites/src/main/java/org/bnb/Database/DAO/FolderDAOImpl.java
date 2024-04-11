package org.bnb.Database.DAO;

import org.bnb.Database.DAO.interfaces.FolderDAO;
import org.bnb.Database.Classes.FolderDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FolderDAOImpl implements FolderDAO {

    private final Connection connection;

    public FolderDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public FolderDB getFolderById(int id) {
        FolderDB folderDB = null;
        String query = "SELECT * FROM Folder WHERE ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                folderDB = extractFolderFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return folderDB;
    }

    @Override
    public List<FolderDB> getAllFolders() {
        List<FolderDB> folderDBS = new ArrayList<>();
        String query = "SELECT * FROM Folder";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                FolderDB folderDB = extractFolderFromResultSet(resultSet);
                folderDBS.add(folderDB);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return folderDBS;
    }

    @Override
    public void addFolder(FolderDB folderDB) {
        String query = "INSERT INTO Folder (FolderName) VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, folderDB.getFolderName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateFolder(FolderDB folderDB) {
        String query = "UPDATE Folder SET FolderName = ? WHERE ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, folderDB.getFolderName());
            preparedStatement.setInt(2, folderDB.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteFolder(int id) {
        String query = "DELETE FROM Folder WHERE ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private FolderDB extractFolderFromResultSet(ResultSet resultSet) throws SQLException {
        FolderDB folderDB = new FolderDB();
        folderDB.setId(resultSet.getInt("ID"));
        folderDB.setFolderName(resultSet.getString("FolderName"));
        return folderDB;
    }
}
