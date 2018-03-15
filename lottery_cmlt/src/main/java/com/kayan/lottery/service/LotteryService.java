package com.kayan.lottery.service;

import com.google.common.annotations.VisibleForTesting;
import com.kayan.lottery.domain.*;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

import static ch.lambdaj.Lambda.join;
import static com.google.common.collect.Lists.newArrayList;
import static java.lang.String.format;

public class LotteryService {

    GameService gameService;
    ExecutorService executor = Executors.newFixedThreadPool(300);
    Random random = new Random();

    public LotteryService(GameService gameService) {
        this.gameService = gameService;
    }

    public List<Game> processGames(List<Game> games) {
        List<Game> processedGames = collectAndPrintJobs(submitJobsForExecution(games));
        executor.shutdown();

        return processedGames;
    }

    private List<Game> collectAndPrintJobs(List<Future<Game>> jobsList) {
        List<Game> processedGames = newArrayList();

        for (Future<Game> future : jobsList) {
            try {
                Game game = future.get();
                print(game);
                processedGames.add(game);
            } catch (InterruptedException e) {
                new RuntimeException(e.getMessage(), e.getCause());
            } catch (ExecutionException e) {
                new RuntimeException(e.getMessage(), e.getCause());
            }
        }

        return processedGames;
    }

    private List<Future<Game>> submitJobsForExecution(List<Game> games) {

        List<Future<Game>> jobsList = new ArrayList<Future<Game>>();
        for (int lineNumber = 0; lineNumber < games.size(); lineNumber++) {

            final Game game = games.get(lineNumber);

            Callable<Game> recordProcessor = new Callable<Game>() {
                @Override
                public Game call() throws Exception {
                    return gameService.play(game);
                }
            };

            Future<Game> submit = executor.submit(recordProcessor);
            jobsList.add(submit);
        }
        return jobsList;
    }

    public void print(Game game) {
        System.out.println(format("Draw date: %s, Draw numbers: %s, Winning prize: %s",
                game.getDraw().getDrawDate().toDate(),
                join(game.getDraw().getDrawnBalls()),
                game.getWin().getAmount()));
    }

    public List<Game> createGames(Player player, Lottery lottery) {
        DateTime next = player.getStartDate();
        List<Game> games = newArrayList();

        do {
            Draw draw = createDraw(next, lottery);
            games.add(new Game(draw, player));
            next = next.plusDays(lottery.getFrequency());
        } while (next.isBefore(player.getEndDate()) || next.isEqual(player.getEndDate()));

        return games;
    }

    @VisibleForTesting
    Draw createDraw(DateTime drawDate, Lottery lottery) {

        List<Integer> balls = newArrayList();
        for (int i = 0; i < lottery.getDrawLength(); i++) {
            int randomNum = randomOfRange(lottery.getNumberRange());
            balls.add(randomNum);
        }

        return new Draw(drawDate, balls);
    }

    private int randomOfRange(Range range) {
        int max = range.getEnd();
        int min = range.getStart();
        return random.nextInt((max - min) + 1) + min;
    }


}
