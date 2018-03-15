package com.kayan.lottery.service;

import com.google.common.base.Function;
import com.kayan.lottery.domain.rules.Rule;
import com.kayan.lottery.domain.rules.RuleComparator;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.google.common.collect.Lists.transform;
import static org.fest.assertions.api.Assertions.assertThat;

public class RuleServiceTest {

    RuleService ruleService;

    Function<Rule, Integer> rulesToPriorities =
            new Function<Rule, Integer>() {
                public Integer apply(Rule rule) {
                    return rule.getPriority();
                }
            };

    @Before
    public void setUp() {
        ruleService = new RuleService();
    }


    @Test
    public void testMainRules() {
        //when
        List<Rule> ruleList = ruleService.mainRules();

        //then
        assertThat(ruleList).isSortedAccordingTo(new RuleComparator());
        assertThat(ruleList).hasSize(3);

        List<Integer> priorities = transform(ruleList, rulesToPriorities);
        assertThat(priorities).doesNotHaveDuplicates();
    }

    @Test
    public void testIncentiveRules() {
        //when
        List<Rule> ruleList = ruleService.incentiveRules();

        //then
        assertThat(ruleList). isSortedAccordingTo(new RuleComparator());
        assertThat(ruleList).hasSize(2);

        List<Integer> priorities = transform(ruleList, rulesToPriorities);
        assertThat(priorities).doesNotHaveDuplicates();
    }


}
