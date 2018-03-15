package kayantest.domain;

import org.junit.Test;

import static kayantest.utils.PlayerTestHelper.createPlayerSession;
import static kayantest.utils.PlayerTestHelper.createPlayerSessionInThePast;
import static org.fest.assertions.api.Assertions.assertThat;

public class PlayerSessionTest {
    @Test
    public void testExpired() {

        //given
        //instead of waiting we can create session in the past
        PlayerSession playerSession = createPlayerSessionInThePast(10);

        //when
        boolean actual = playerSession.expiredWithDelay(9);

        //then
        assertThat(actual).isTrue();

    }

    @Test
    public void testNotExpired() {

        //given
        PlayerSession playerSession = createPlayerSession();

        //when
        boolean actual = playerSession.expiredWithDelay(9);

        //then
        assertThat(actual).isFalse();

    }

}
