package org.bnb.Database.DAO.interfaces;

import org.bnb.Classes.DurationLevels;

import java.util.List;

public interface DurationLevelsDAO {
    DurationLevels getDurationLevelsById(int durationLevelsId);

    List<DurationLevels> getAllDurationLevels();

    void addDurationLevels(DurationLevels durationLevels);

    void updateDurationLevels(DurationLevels durationLevels);

    void deleteDurationLevels(int durationLevelsId);
}
