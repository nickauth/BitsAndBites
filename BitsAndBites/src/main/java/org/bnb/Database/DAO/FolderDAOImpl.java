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
    public Folder getFolderById(int folderId) {
        return null;
    }

    @Override
    public List<Folder> getAllFolders() {
        List<Folder> folders = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Folder");
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

    }

    @Override
    public void updateFolder(Folder folder) {

    }

    @Override
    public void deleteFolder(int folderId) {

    }

    private Folder extractFolderFromResultSet(ResultSet resultSet) throws SQLException {
        Folder folder = new Folder();
        folder.setId(resultSet.getInt("ID"));
        folder.setFolderName(resultSet.getString("FolderName"));
        return folder;
    }
}