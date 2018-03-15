package com.kayan.lottery.domain.rules;

import com.google.common.base.Optional;
import com.kayan.lottery.domain.Game;
import com.kayan.lottery.domain.Win;

import java.math.BigDecimal;

public interface Rule {

    Integer getPriority();

    Optional<Win> applyTo(Game gamGame);

    BigDecimal applyTo(Win win);

    /**
     * There are two types of rules: Main and Incentive.
     * Main rule creates win whereas incentive rule increases it afterwards.
     *
     * @return true if rule is incentive and false if it is main.
     */
    boolean isIncentive();

}
