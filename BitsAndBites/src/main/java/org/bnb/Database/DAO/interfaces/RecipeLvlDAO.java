package org.bnb.Database.DAO.interfaces;

import org.bnb.Database.Classes.RecipeLvlDB;

import java.util.List;

public interface RecipeLvlDAO {

    RecipeLvlDB getRecipeLvlById(int recipeLvlId);

    List<RecipeLvlDB> getAllRecipeLvls();

    void addRecipeLvl(RecipeLvlDB recipeLvlDB);

    void updateRecipeLvl(RecipeLvlDB recipeLvlDB);

    void deleteRecipeLvl(int recipeLvlId);
}
