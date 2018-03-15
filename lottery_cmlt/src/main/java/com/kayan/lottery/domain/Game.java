package com.kayan.lottery.domain;

public class Game {

    Draw draw;
    Player player;
    Win win;

    public Game(Draw draw, Player player) {
        this.draw = draw;
        this.player = player;
    }

    public Draw getDraw() {
        return draw;
    }

    public Player getPlayer() {
        return player;
    }


    public Game won(Win win) {
        this.win = win;
        return this;
    }

    public Win getWin() {
        return win;
    }

    public boolean isWon() {
        return win != null;
    }

    @Override
    public String toString() {
        return "Game{" +
                "draw=" + draw +
                ", player=" + player +
                ", win=" + win +
                '}';
    }
}
