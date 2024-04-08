package org.bnb.Database.DAO.interfaces;

import org.bnb.Classes.Folder;

import java.util.List;

public interface FolderDAO {
    Folder getFolderById(int folderId);

    List<Folder> getAllFolders();

    void addFolder(Folder folder);

    void updateFolder(Folder folder);

    void deleteFolder(int folderId);
}
