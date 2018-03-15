package com.kayan.lottery.service;

import com.google.common.base.Optional;
import com.kayan.lottery.domain.Game;
import com.kayan.lottery.domain.Win;
import com.kayan.lottery.domain.rules.Rule;

import java.math.BigDecimal;
import java.util.List;

public class GameService {

    RuleService ruleService;

    public GameService(RuleService ruleService) {
        this.ruleService = ruleService;
    }

    public Game play(Game game) {
        playMainGame(game);
        playIncentiveGame(game);
        return game;
    }

    private Game playIncentiveGame(Game game) {
        return game.isWon() ? deal(game, ruleService.incentiveRules()) : game;
    }

    private Game playMainGame(Game game) {
        return deal(game, ruleService.mainRules());
    }

    private Game deal(Game game, List<Rule> rules) {

        for (Rule rule : rules) {
            Optional<Win> optionalWin = rule.applyTo(game);

            if (optionalWin.isPresent()) {
                Win win = optionalWin.get();

                BigDecimal wonAmount = rule.applyTo(win);
                game.won(win.withAmount(wonAmount));

                break;
            }
        }

        return game;

    }

}
