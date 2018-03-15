package com.kayan.lottery.domain;

import com.google.common.collect.Lists;

import java.util.List;

public class Match {
    private List<Integer> guessedBalls = Lists.newArrayList();
    private List<Integer> missedBalls = Lists.newArrayList();


    public void addMissed(Integer missedBall) {
        missedBalls.add(missedBall);
    }

    public void addGuessed(Integer guessedBall) {
        guessedBalls.add(guessedBall);
    }

    public Match withGuessedBalls(List<Integer> guessedBalls) {
        this.guessedBalls = guessedBalls;
        return this;
    }

    public Match withMissedBalls(List<Integer> missedBalls) {
        this.missedBalls = missedBalls;
        return this;
    }

    public int missSize() {
        return missedBalls.size();
    }

    public int guessSize() {
        return guessedBalls.size();
    }

    public List<Integer> getGuessedBalls() {
        return guessedBalls;
    }

    public List<Integer> getMissedBalls() {
        return missedBalls;
    }

    @Override
    public String toString() {
        return "Match{" +
                "guessedBalls=" + guessedBalls +
                ", missedBalls=" + missedBalls +
                '}';
    }
}
