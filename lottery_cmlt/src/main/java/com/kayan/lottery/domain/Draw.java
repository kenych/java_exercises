package com.kayan.lottery.domain;


import org.joda.time.DateTime;

import java.util.List;

public class Draw {

    public Draw(DateTime drawDate, List<Integer> drawnBolls) {
        this.drawDate = drawDate;
        this.drawnBolls = drawnBolls;
    }

    DateTime drawDate;
    List<Integer> drawnBolls;

    public DateTime getDrawDate() {
        return drawDate;
    }

    public List<Integer> getDrawnBalls() {
        return drawnBolls;
    }

    @Override
    public String toString() {
        return "Draw{" +
                "drawDate=" + drawDate +
                ", drawnBolls=" + drawnBolls +
                '}';
    }
}
