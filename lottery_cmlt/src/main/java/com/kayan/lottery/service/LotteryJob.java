package com.kayan.lottery.service;

import com.kayan.lottery.domain.Game;

import java.util.concurrent.Callable;

public class LotteryJob implements Callable<Game> {

    private Game game;
    private GameService gameService;


    public LotteryJob(Game game, GameService gameService) {
        this.game = game;
        this.gameService = gameService;
    }

    @Override
    public Game call() throws Exception {
        return gameService.play(game);
    }
}
