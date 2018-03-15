package com.kayan.lottery.app;


import com.google.common.base.Function;
import com.kayan.lottery.domain.*;
import com.kayan.lottery.service.GameService;
import com.kayan.lottery.service.LotteryService;
import com.kayan.lottery.service.RuleService;
import org.joda.time.DateTime;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.collect.Lists.transform;
import static java.util.Arrays.asList;
import static org.joda.time.DateTime.now;
import static org.joda.time.format.DateTimeFormat.forPattern;


public class Application {

    public static final int DRAW_LENGTH = 6;
    public static final Range NUMBER_RANGE = new Range(1, 60);
    public static final int SUBSCRIPTION_LENGTH = 6;
    public static final int FREQUENCY = 7;

    public static final String DATE_FORMAT_DD_MM_YYYY = "dd/MM/yyyy";

    public static final String ERROR_MESSAGE_ARGS = "Please provide 2 arguments: subscription end date(should be 6 month from now)" +
            "  followed by 6 numbers from 1-60 separated by comma.\n" +
            "Example: " + forPattern(DATE_FORMAT_DD_MM_YYYY).print(now().plusMonths(6)) + " 1,2,3,4,5,6";

    static LotteryService lotteryService = new LotteryService(new GameService(new RuleService()));

   static Lottery lottery = new Lottery(DayOfWeek.MONDAY, SUBSCRIPTION_LENGTH, NUMBER_RANGE, DRAW_LENGTH, FREQUENCY);

    public static void main(String[] args) {

        checkArgument(args.length == 2, ERROR_MESSAGE_ARGS);
        String endOfSubscription = args[0];
        String playerGuessString = args[1];

        List<Integer> playerGuess = getPlayerGuess(playerGuessString);

        Player player = new Player(getEndDate(endOfSubscription), playerGuess);
        player.subscribeTo(lottery);


        List<Game> games = lotteryService.createGames(player, lottery);
        lotteryService.processGames(games);


    }

    private static DateTime getEndDate(String endOfSubscription) {
        return forPattern(DATE_FORMAT_DD_MM_YYYY).parseDateTime(endOfSubscription);
    }



    private static List<Integer> getPlayerGuess(String playerGuessString) {
        List<String> playerGuessNumbersString = asList(playerGuessString.trim().split(","));

        checkArgument(playerGuessNumbersString.size() == lottery.getDrawLength(), "Draw size is wrong, should be: "+ lottery.getDrawLength());

        List<Integer> list =  transform(playerGuessNumbersString, new Function<String, Integer>() {
            public Integer apply(String ball) {
                return Integer.valueOf(ball.trim());
            }
        });

        return list;
    }
}
