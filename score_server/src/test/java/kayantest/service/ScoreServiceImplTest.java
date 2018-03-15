package kayantest.service;

import kayantest.domain.LevelScore;
import kayantest.domain.PlayerLevel;
import kayantest.infrastructure.Config;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static kayantest.infrastructure.Config.MAX_LEVELS;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class ScoreServiceImplTest {

    private static final Integer PLAYER_ID = 1;
    public static final String ANY_SESSION_ID = "ANY_SESSION_ID";
    public static final int LEVEL_0 = 0;
    public static final int LEVEL_1 = 1;
    private ScoreService scoreService;

    @Mock
    private SessionService sessionServiceMock;


    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        scoreService = new ScoreServiceImpl(sessionServiceMock, MAX_LEVELS);

        when(sessionServiceMock.findPlayerBy(anyString())).thenReturn(PLAYER_ID);
    }


    @Test
    public void testUpdateWithHigherScore() throws Exception {

        //given
        int expectedLowerScore = 10;
        LevelScore levelScore = new LevelScore(LEVEL_0, expectedLowerScore);


        //when
        scoreService.registerScore(ANY_SESSION_ID, levelScore);
        int actualScore = scoreService.getHighScoreBy(new PlayerLevel(PLAYER_ID, levelScore.getLevel()));

        //then
        assertThat(actualScore).isEqualTo(expectedLowerScore);

        //given
        int expectedHigherScore = 11;
        levelScore = new LevelScore(LEVEL_0, expectedHigherScore);


        //when
        scoreService.registerScore(ANY_SESSION_ID, levelScore);
        actualScore = scoreService.getHighScoreBy(new PlayerLevel(PLAYER_ID, levelScore.getLevel()));

        //then
        assertThat(actualScore).isEqualTo(expectedHigherScore);

        System.out.println(Arrays.toString(scoreService.getTopList(LEVEL_0)));
    }

    @Test
    public void testUpdateWithLowerScore() throws Exception {

        //given
        int expectedHigherScore = 13;
        LevelScore levelScore = new LevelScore(LEVEL_0, expectedHigherScore);


        //when
        scoreService.registerScore(ANY_SESSION_ID, levelScore);
        int actualScore = scoreService.getHighScoreBy(new PlayerLevel(PLAYER_ID, levelScore.getLevel()));

        //then
        assertThat(actualScore).isEqualTo(expectedHigherScore);

        //given
        int expectedLowerScore = 11;
        levelScore = new LevelScore(LEVEL_0, expectedLowerScore);


        //when
        scoreService.registerScore(ANY_SESSION_ID, levelScore);
        actualScore = scoreService.getHighScoreBy(new PlayerLevel(PLAYER_ID, levelScore.getLevel()));

        //then
        assertThat(actualScore).isNotEqualTo(expectedLowerScore);


    }

    @Test
    public void testUpdateTwoLevels() throws Exception {

        //given
        int expectedHigherScore = 13;
        LevelScore levelScore = new LevelScore(LEVEL_0, expectedHigherScore);


        //when
        scoreService.registerScore(ANY_SESSION_ID, levelScore);
        int actualScore = scoreService.getHighScoreBy(new PlayerLevel(PLAYER_ID, levelScore.getLevel()));

        //then
        assertThat(actualScore).isEqualTo(expectedHigherScore);

        //given
        expectedHigherScore = 14;
        levelScore = new LevelScore(LEVEL_1, expectedHigherScore);


        //when
        scoreService.registerScore(ANY_SESSION_ID, levelScore);
        actualScore = scoreService.getHighScoreBy(new PlayerLevel(PLAYER_ID, levelScore.getLevel()));

        //then
        assertThat(actualScore).isEqualTo(expectedHigherScore);



    }


}
