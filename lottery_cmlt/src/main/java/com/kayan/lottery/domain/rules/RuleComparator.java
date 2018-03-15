package com.kayan.lottery.domain.rules;

import java.util.Comparator;

public class RuleComparator implements Comparator<Rule> {
    @Override
    public int compare(Rule rule1, Rule rule2) {
        return new Integer(rule1.getPriority()).compareTo(
                new Integer(rule2.getPriority()
                ));
    }
}
