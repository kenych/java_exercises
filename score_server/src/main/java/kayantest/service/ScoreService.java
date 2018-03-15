package kayantest.service;

import kayantest.domain.LevelScore;
import kayantest.domain.PlayerLevel;
import kayantest.domain.PlayerScore;

public interface ScoreService {

    void registerScore(String sessionId, LevelScore levelScore);

    int getHighScoreBy(PlayerLevel playerLevel);

    //This was requested to be ScoreListEntry but PlayerScore feels more natural name
    PlayerScore[] getTopList(int level);
}
