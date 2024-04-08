package org.bnb.Database.DAO.interfaces;

import org.bnb.Classes.RecipeLvl;

import java.util.List;

public interface RecipeLvlDAO {
    List<RecipeLvl> getAllRecipeLevels();

    RecipeLvl getRecipeLvlById(int recipeLvlId);

    List<RecipeLvl> getAllRecipeLvls();

    void addRecipeLvl(RecipeLvl recipeLvl);

    void updateRecipeLvl(RecipeLvl recipeLvl);

    void deleteRecipeLvl(int recipeLvlId);
}
