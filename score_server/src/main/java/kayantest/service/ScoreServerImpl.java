package kayantest.service;

import kayantest.domain.LevelScore;
import kayantest.domain.PlayerScore;

public class ScoreServerImpl implements ScoreServer {

    SessionService sessionService;
    ScoreService scoreService;

    public ScoreServerImpl(SessionService sessionService, ScoreService scoreService) {
        this.sessionService = sessionService;
        this.scoreService = scoreService;
    }

    @Override
    public String getSessionKey(int playerId) {
        return sessionService.createSession(playerId);
    }

    @Override
    public void registerScore(String sessionKey, int level, int score) {
        scoreService.registerScore(sessionKey, new LevelScore(level, score));
    }

    @Override
    public PlayerScore[] getToplist(int level) {
        return scoreService.getTopList(level);
    }
}
