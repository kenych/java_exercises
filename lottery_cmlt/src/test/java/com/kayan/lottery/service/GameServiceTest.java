package com.kayan.lottery.service;

import com.kayan.lottery.domain.Draw;
import com.kayan.lottery.domain.Game;
import com.kayan.lottery.domain.Player;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static com.kayan.lottery.TestUtils.BALLS_1_2_3_4_5_6;
import static com.kayan.lottery.TestUtils.*;
import static org.fest.assertions.api.Assertions.assertThat;


public class GameServiceTest {

    GameService gameService;

    @Before
    public void setUp() throws Exception {
        gameService = new GameService(new RuleService());
    }

    @Test
    public void testPlayAndWinWhenAllMatchesAnd29OfFebruary() throws Exception {
        //given
        Draw draw = new Draw(new DateTime().
                withYear(2016).
                withMonthOfYear(2).
                withDayOfMonth(29), BALLS_1_2_3_4_5_6);
        Player player = new Player(NOW, NOW, BALLS_1_2_3_4_5_6);
        Game game = new Game(draw, player);


        //when
        gameService.play(game);

        //then
        assertThat(game.getWin().getAmount()).isEqualTo(new BigDecimal(210000*3));
    }

    @Test
    public void testPlayAndWinWhen3AndMoreMatchesAndFebruaryLeapYear() throws Exception {
        //given
        final Draw draw = new Draw(new DateTime().
                withYear(2016).
                withMonthOfYear(2).
                withDayOfMonth(28), BALLS_1_2_3_4_5_6);
        final Player player = new Player(NOW, NOW, BALLS_1_2_3_4_1_1);
        Game game = new Game(draw, player);


        //when
        gameService.play(game);

        //then
        assertThat(game.getWin().getAmount()).isEqualTo(new BigDecimal((4000+1)*2));
    }
}
