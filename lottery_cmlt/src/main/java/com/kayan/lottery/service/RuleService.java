package com.kayan.lottery.service;

import com.google.common.base.Predicate;
import com.kayan.lottery.domain.rules.Rule;
import com.kayan.lottery.domain.rules.RuleComparator;
import com.kayan.lottery.domain.rules.incentive.February29IncentiveRule;
import com.kayan.lottery.domain.rules.incentive.LeapYearFebruaryIncentiveRule;
import com.kayan.lottery.domain.rules.main.AllBallsMatchMainRule;
import com.kayan.lottery.domain.rules.main.LessThanThreeBallsMatchMainRule;
import com.kayan.lottery.domain.rules.main.ThreeAndMoreBallsMatchMainRule;

import java.util.List;

import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Lists.newArrayList;
import static java.util.Collections.sort;

public class RuleService {

    static List<Rule> listOfRules = newArrayList(
            new AllBallsMatchMainRule(),
            new ThreeAndMoreBallsMatchMainRule(),
            new LessThanThreeBallsMatchMainRule(),
            new LeapYearFebruaryIncentiveRule(),
            new February29IncentiveRule()
    );

    static Predicate<Rule> mainRulesPredicate = new Predicate<Rule>() {
        @Override
        public boolean apply(Rule rule) {
            return !rule.isIncentive();
        }
    };

    static Predicate<Rule> incentiveRulesPredicate = new Predicate<Rule>() {
        @Override
        public boolean apply(Rule rule) {
            return rule.isIncentive();
        }
    };

    public List<Rule> mainRules() {
        return getAndSortRules(mainRulesPredicate);
    }

    public List<Rule> incentiveRules() {
        return getAndSortRules(incentiveRulesPredicate);
    }

    private List<Rule> getAndSortRules(Predicate<Rule> predicate) {
        List<Rule> rules = newArrayList(filter(listOfRules, predicate).iterator());
        sort(rules, new RuleComparator());
        return rules;
    }
}