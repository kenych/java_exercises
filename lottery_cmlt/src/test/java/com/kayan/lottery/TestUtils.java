package com.kayan.lottery;

import com.kayan.lottery.domain.Draw;
import com.kayan.lottery.domain.Game;
import com.kayan.lottery.domain.Player;
import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.util.ArrayList;

import static com.google.common.collect.Lists.newArrayList;

public class TestUtils {

    public static final DateTime NOW = DateTime.now();

    public static final ArrayList<Integer> BALLS_1_2_3 = newArrayList(1, 2, 3);
    public static final ArrayList<Integer> BALLS_4_5_6 = newArrayList(4, 5, 6);
    public static final ArrayList<Integer> BALLS_1_2_3_4_5_6 = newArrayList(1, 2, 3, 4, 5, 6);
    public static final ArrayList<Integer> BALLS_1_2_3_4_1_1 = newArrayList(1, 2, 3, 4, 1, 1);
    public static final ArrayList<Integer> BALLS_1_2_3_1_1_1 = newArrayList(1, 2, 3, 1, 1, 1);
    public static final ArrayList<Integer> BALLS_1_2_1_1_1_1 = newArrayList(1, 2, 1, 1, 1, 1);
    public static final ArrayList<Integer> BALLS_1 = newArrayList(1);

    public static final BigDecimal TEN = BigDecimal.TEN;
    public static final BigDecimal THIRTY = new BigDecimal(30);
    public static final BigDecimal TWENTY = new BigDecimal(20);

    public static Game game3Matches() {
        final Draw draw = new Draw(NOW, BALLS_1_2_3_4_5_6);
        final Player player = new Player(NOW, NOW, BALLS_1_2_3_1_1_1);
        return new Game(draw, player);
    }

    public static Game gameAllMatches() {
        final Draw draw = new Draw(NOW, BALLS_1_2_3_4_5_6);
        final Player player = new Player(NOW, NOW, BALLS_1_2_3_4_5_6);
        return new Game(draw, player);
    }

    public static Game gameMoreThan3Matches() {
        Draw draw = new Draw(NOW, BALLS_1_2_3_4_5_6);
        Player player = new Player(NOW, NOW, BALLS_1_2_3_4_1_1);
        return new Game(draw, player);

    }

    public static Game gameLessThan3Matches() {
        //given
        Draw draw = new Draw(NOW, BALLS_1_2_3_4_5_6);
        Player player = new Player(NOW, NOW, BALLS_1_2_1_1_1_1);
        return new Game(draw, player);

    }


}
