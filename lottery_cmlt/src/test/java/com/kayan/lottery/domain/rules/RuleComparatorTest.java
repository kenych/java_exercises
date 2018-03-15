package com.kayan.lottery.domain.rules;

import com.kayan.lottery.domain.rules.incentive.February29IncentiveRule;
import com.kayan.lottery.domain.rules.incentive.LeapYearFebruaryIncentiveRule;
import com.kayan.lottery.domain.rules.main.AllBallsMatchMainRule;
import com.kayan.lottery.domain.rules.main.LessThanThreeBallsMatchMainRule;
import com.kayan.lottery.domain.rules.main.ThreeAndMoreBallsMatchMainRule;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.fest.assertions.api.Assertions.assertThat;

public class RuleComparatorTest {

    List<? extends Rule> rules;

    @Before
    public void setUp() {

    }

    @Test
    public void testCompareMainRules() throws Exception {
        //given
        rules = newArrayList(
                new ThreeAndMoreBallsMatchMainRule(),
                new AllBallsMatchMainRule(),
                new LessThanThreeBallsMatchMainRule()
        );

        //when
        Collections.sort(rules, new RuleComparator());

        //then
        assertThat(rules.get(0)).isExactlyInstanceOf(AllBallsMatchMainRule.class);
        assertThat(rules.get(1)).isExactlyInstanceOf(ThreeAndMoreBallsMatchMainRule.class);
        assertThat(rules.get(2)).isExactlyInstanceOf(LessThanThreeBallsMatchMainRule.class);
    }

    @Test
    public void testCompareIncentiveRules() throws Exception {
        //given
        rules = newArrayList(
                new LeapYearFebruaryIncentiveRule(),
                new February29IncentiveRule()
        );

        //when
        Collections.sort(rules, new RuleComparator());

        //then
        assertThat(rules.get(0)).isExactlyInstanceOf(February29IncentiveRule.class);
        assertThat(rules.get(1)).isExactlyInstanceOf(LeapYearFebruaryIncentiveRule.class);
    }
}
