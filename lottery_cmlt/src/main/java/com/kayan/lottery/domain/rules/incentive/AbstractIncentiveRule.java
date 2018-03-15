package com.kayan.lottery.domain.rules.incentive;

import com.kayan.lottery.domain.rules.Rule;

public abstract class AbstractIncentiveRule implements Rule {

    @Override
    public boolean isIncentive() {
        return true;
    }


}
