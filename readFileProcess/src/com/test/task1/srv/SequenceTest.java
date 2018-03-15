package com.test.task1.srv;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SequenceTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		Sequence seq = new Sequence(4);
		assertEquals(0, seq.getNext());
		assertEquals(1, seq.getNext());
		assertEquals(2, seq.getNext());
		assertEquals(3, seq.getNext());
		assertEquals(0, seq.getNext());
		assertEquals(1, seq.getNext());
		assertEquals(2, seq.getNext());
	}

}
