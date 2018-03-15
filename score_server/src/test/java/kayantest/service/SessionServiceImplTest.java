package kayantest.service;

import kayantest.domain.exceptions.SessionException;
import org.junit.Test;

import static kayantest.utils.TestUtils.Code;
import static kayantest.utils.TestUtils.assertThat;
import static org.fest.assertions.api.Assertions.assertThat;

public class SessionServiceImplTest {

    public static final int DELAY_ZERO = 0;
    public static final int DELAY_IN_THE_PAST = -10;
    public static final int PLAYER_ID = 1;
    public static final String NOT_EXISTING_SESSION = "NOT_EXISTING_SESSION";


    @Test
    public void testCreateSession() throws Exception {

        //given
        SessionService sessionService = new SessionServiceImpl(DELAY_ZERO, DELAY_ZERO);

        //when
        String sessionId1 = sessionService.createSession(1);
        String sessionId2 = sessionService.createSession(1);


        //then
        assertThat(sessionId1).isNotEmpty();
        assertThat(sessionId2).isNotEmpty();

        assertThat(sessionId1).isNotEqualTo(sessionId2);


    }

    @Test
    public void testFindPlayerBy() throws Exception {

        //given
        SessionService sessionService = new SessionServiceImpl(DELAY_ZERO, DELAY_ZERO);
        String sessionId = sessionService.createSession(PLAYER_ID);

        //when
        int playerId = sessionService.findPlayerBy(sessionId);


        //then
        assertThat(PLAYER_ID).isEqualTo(playerId);

    }

    @Test
    public void testSessionExpiredException() throws Exception {

        //given
        final SessionService sessionService = new SessionServiceImpl(DELAY_IN_THE_PAST, DELAY_ZERO);
        final String sessionId = sessionService.createSession(PLAYER_ID);

        //when
        Code code = () -> sessionService.findPlayerBy(sessionId);

        //then
        assertThat(code).
                throwsException(SessionException.class).
                withMessageContaining("Session expired with id");
    }

    @Test
    public void testSessionNotFoundException() throws Exception {

        //given
        final SessionService sessionService = new SessionServiceImpl(DELAY_ZERO, DELAY_ZERO);

        //when
        Code code = () -> sessionService.findPlayerBy(NOT_EXISTING_SESSION);

        //then
        assertThat(code).
                throwsException(SessionException.class).
                withMessageContaining("No session with id");
    }


}
