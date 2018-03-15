package com.randomltd.engine;

import com.randomltd.domain.BackBet;
import com.randomltd.domain.LayBet;
import com.randomltd.domain.Market;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static java.util.Collections.shuffle;
import static org.fest.assertions.api.Assertions.assertThat;

public class MatchingEngineTest {

    MatchingEngine<String> matchingEngine;

    @Before
    public void setUp() throws Exception {
        matchingEngine = new MatchingEngine<String>();
    }

    @Test
    public void testProcess() throws Exception {
        //given
        List<Pairing<String>> pairings = new LinkedList<Pairing<String>>();
        create(pairings, new Market("A"), 100, 99);
        create(pairings, new Market("B"), 88, 86);
        create(pairings, new Market("C"), 60, 70);
        shuffle(pairings);

        //when
        MatchingResult<String> matchingResult = matchingEngine.findMatches(pairings);

        //then
        assertThat(matchingResult.matchedBy("A")).isEqualTo(99);
        assertThat(matchingResult.matchedBy("B")).isEqualTo(86);
        assertThat(matchingResult.matchedBy("C")).isEqualTo(60);

        assertThat(matchingResult.unmatchedLeftBy("A")).isEqualTo(1);
        assertThat(matchingResult.unmatchedRightBy("A")).isEqualTo(0);

        assertThat(matchingResult.unmatchedLeftBy("B")).isEqualTo(2);
        assertThat(matchingResult.unmatchedRightBy("B")).isEqualTo(0);

        assertThat(matchingResult.unmatchedLeftBy("C")).isEqualTo(0);
        assertThat(matchingResult.unmatchedRightBy("C")).isEqualTo(10);
    }

    private void create(List<Pairing<String>> pairings, Market market, int backs, int lays) {
        for (int i = 0; i < backs; i++) {
            pairings.add(new BackBet(market));
        }
        for (int i = 0; i < lays; i++) {
            pairings.add(new LayBet(market));
        }
    }

}
