package org.bnb.Database.DAO.interfaces;

import org.bnb.Database.Classes.DurationLevelsDB;

import java.util.List;

public interface DurationLevelsDAO {
    DurationLevelsDB getDurationLevelsById(int durationLevelsId);

    List<DurationLevelsDB> getAllDurationLevels();

    void addDurationLevels(DurationLevelsDB durationLevelsDB);

    void updateDurationLevels(DurationLevelsDB durationLevelsDB);

    void deleteDurationLevels(int durationLevelsId);
}
