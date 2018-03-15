package com.kayan.lottery.service;

import com.kayan.lottery.domain.*;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static com.kayan.lottery.TestUtils.*;
import static org.fest.assertions.api.Assertions.assertThat;

public class LotteryServiceTest {

    public static final int DRAW_LENGTH = 6;
    public static final Range NUMBER_RANGE = new Range(1, 60);
    public static final int FREQUENCY = 7;
    LotteryService lotteryService = new LotteryService(new GameService(new RuleService()));


    @Test
    public void testProcessGames() throws Exception {

        //given
        Game gameMoreThan3Matches = gameMoreThan3Matches();
        Game game3Matches = game3Matches();
        Game gameLessThan3Matches = gameLessThan3Matches();

        List<Game> games = newArrayList(gameMoreThan3Matches, gameLessThan3Matches, game3Matches);

        //when
        lotteryService.processGames(games);

        //then
        assertThat(game3Matches.getWin().getAmount()).isEqualTo(new BigDecimal(3001));
        assertThat(gameMoreThan3Matches.getWin().getAmount()).isEqualTo(new BigDecimal(4001));
        assertThat(gameLessThan3Matches.getWin().getAmount()).isEqualTo(new BigDecimal(21));
    }


    @Test
    public void testCreateDraw() {
        //given
        Lottery lottery = new Lottery(DayOfWeek.MONDAY, 0, NUMBER_RANGE, DRAW_LENGTH, FREQUENCY);

        //when
        Draw draw = lotteryService.createDraw(NOW, lottery);
        Collections.sort(draw.getDrawnBalls());

        //then
        assertThat(draw.getDrawnBalls()).hasSize(lottery.getDrawLength());
        assertThat(draw.getDrawnBalls().get(0)).isLessThanOrEqualTo(NUMBER_RANGE.getEnd());
        assertThat(draw.getDrawnBalls().get(DRAW_LENGTH - 1)).isGreaterThanOrEqualTo(NUMBER_RANGE.getStart());

    }

    @Test
    public void testCreateGames() {

        //given
        Lottery lottery = new Lottery(DayOfWeek.MONDAY, 0, NUMBER_RANGE, DRAW_LENGTH, FREQUENCY);
        int timesToPlay = 10;
        Player player = new Player(NOW, NOW.plusDays(FREQUENCY * timesToPlay), BALLS_1_2_3_4_1_1);

        //when
        List<Game> games = lotteryService.createGames(player, lottery);

        assertThat(games).hasSize(timesToPlay + 1);
    }

}