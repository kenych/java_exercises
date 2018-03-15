package kayantest.service;

public interface SessionService {

    String createSession(Integer playerId);

    int findPlayerBy(String sessionId);
}
