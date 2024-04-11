package org.bnb.Database.DAO;

import org.bnb.Database.DAO.interfaces.FolderDAO;
import org.bnb.Classes.Folder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FolderDAOImpl implements FolderDAO {

    private final Connection connection;

    public FolderDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Folder getFolderById(int id) {
        Folder folder = null;
        String query = "SELECT * FROM Folder WHERE ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                folder = extractFolderFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return folder;
    }

    @Override
    public List<Folder> getAllFolders() {
        List<Folder> folders = new ArrayList<>();
        String query = "SELECT * FROM Folder";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Folder folder = extractFolderFromResultSet(resultSet);
                folders.add(folder);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return folders;
    }

    @Override
    public void addFolder(Folder folder) {
        String query = "INSERT INTO Folder (FolderName) VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, folder.getFolderName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateFolder(Folder folder) {
        String query = "UPDATE Folder SET FolderName = ? WHERE ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, folder.getFolderName());
            preparedStatement.setInt(2, folder.getId());
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

    private Folder extractFolderFromResultSet(ResultSet resultSet) throws SQLException {
        Folder folder = new Folder();
        folder.setId(resultSet.getInt("ID"));
        folder.setFolderName(resultSet.getString("FolderName"));
        return folder;
    }
}
