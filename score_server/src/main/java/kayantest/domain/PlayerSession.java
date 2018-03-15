package kayantest.domain;

import java.util.concurrent.TimeUnit;

public class PlayerSession {
    private String sessionId;
    int playerId;
    long creationTimeNano;

    public PlayerSession(String sessionId, int playerId, long creationTimeNano) {
        this.sessionId = sessionId;
        this.playerId = playerId;
        this.creationTimeNano = creationTimeNano;
    }

    public int getPlayerId() {
        return playerId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public boolean expiredWithDelay(int delayInSeconds) {
        return TimeUnit.NANOSECONDS.toSeconds(System.nanoTime() - creationTimeNano) > delayInSeconds;
    }
}
