package com.kayan.lottery.domain;

import java.math.BigDecimal;

public class Win {

    BigDecimal amount;
    Match match;
    Draw draw;

    public Win(Match match) {
        this.match = match;
    }

    public Win(Draw draw) {
        this.draw = draw;
    }

    public Win(BigDecimal amount, Match match) {
        this.amount = amount;
        this.match = match;
    }

    public Win() {
    }

    public Match getMatch() {
        return match;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Win withAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public Draw getDraw() {
        return draw;
    }

    @Override
    public String toString() {
        return "Win{" +
                "amount=" + amount +
                ", match=" + match +
                ", draw=" + draw +
                '}';
    }
}
