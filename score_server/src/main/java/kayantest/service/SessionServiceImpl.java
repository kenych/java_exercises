package kayantest.service;

import kayantest.domain.PlayerSession;
import kayantest.domain.exceptions.SessionException;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static java.lang.System.nanoTime;


public class SessionServiceImpl implements SessionService {


    private int expirationDelayInSeconds;

    //TODO
    // add here DelayQueue which will delete sessions once expired up to deletion time, I demonstrated this previous time
    // we need to do clean up so playerSessions doesn't consume all memory for expired sessions

    private int deletionDelayInSeconds;

    private ConcurrentMap<String, PlayerSession> playerSessions = new ConcurrentHashMap<String, PlayerSession>();

    public SessionServiceImpl(int expirationDelayInSeconds, int deletionDelayInSeconds) {
        this.expirationDelayInSeconds = expirationDelayInSeconds;
        this.deletionDelayInSeconds = deletionDelayInSeconds;
    }

    @Override
    public String createSession(Integer playerId) {
        String newSessionId = UUID.randomUUID().toString();

        PlayerSession existingPlayerSession = playerSessions.putIfAbsent(newSessionId,
                new PlayerSession(newSessionId, playerId, nanoTime()));

        if (existingPlayerSession != null) {
            return existingPlayerSession.getSessionId();
        }

        return newSessionId;
    }

    @Override
    public int findPlayerBy(String sessionId) {

        PlayerSession playerSession = playerSessions.get(sessionId);

        if (playerSession == null) {
            throw new SessionException("No session with id: " + sessionId);
        } else if (playerSession.expiredWithDelay(expirationDelayInSeconds)) {
            throw new SessionException("Session expired with id: " + sessionId);
        }


        return playerSession.getPlayerId();
    }
}
