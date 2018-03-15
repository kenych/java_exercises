package kayantest.domain;

import org.junit.Test;

import static kayantest.domain.TopList.DESCENDING_COMPARATOR;
import static org.fest.assertions.api.Assertions.assertThat;

public class TopListTest {

    public static final int PLAYER_ID_1 = 1;
    public static final int PLAYER_ID_2 = 2;

    @Test
    public void testUpdateForOneUserWithListSizeOf1() throws Exception {

        //given
        TopList topList = new TopList(1);

        //when
        topList.update(new PlayerScore(PLAYER_ID_1, 5));
        topList.update(new PlayerScore(PLAYER_ID_1, 2));

        //then
        assertThat(topList.asArray()[0].getScore()).isEqualTo(5);
        assertThat(topList.asArray()[0].getPlayerId()).isEqualTo(PLAYER_ID_1);
    }

    @Test
    public void testUpdateForTwoUsersWithListSizeOf2() throws Exception {

        //given
        TopList topList = new TopList(2);

        //when
        topList.update(new PlayerScore(PLAYER_ID_2, 7));
        topList.update(new PlayerScore(PLAYER_ID_1, 5));
        topList.update(new PlayerScore(PLAYER_ID_1, 2));
        topList.update(new PlayerScore(PLAYER_ID_2, 1));

        //then
        assertThat(topList.playerScores.get(PLAYER_ID_1)).isEqualTo(5);
        assertThat(topList.playerScores.get(PLAYER_ID_2)).isEqualTo(7);

        assertThat(topList.asArray()).isSortedAccordingTo(DESCENDING_COMPARATOR);
        assertThat(topList.asList()).isSortedAccordingTo(DESCENDING_COMPARATOR);
    }

}
