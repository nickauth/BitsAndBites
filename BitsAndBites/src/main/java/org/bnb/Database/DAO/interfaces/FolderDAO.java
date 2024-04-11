package org.bnb.Database.DAO.interfaces;

import org.bnb.Database.Classes.FolderDB;

import java.util.List;

public interface FolderDAO {
    FolderDB getFolderById(int folderId);

    List<FolderDB> getAllFolders();

    void addFolder(FolderDB folderDB);

    void updateFolder(FolderDB folderDB);

    void deleteFolder(int folderId);
}
