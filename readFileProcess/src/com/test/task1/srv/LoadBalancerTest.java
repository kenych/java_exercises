package com.test.task1.srv;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LoadBalancerTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		LoadBalancer<String> loadBalancer = new LoadBalancer<>(new Sequence(4));
		assertEquals(0, loadBalancer.getNode("a"));
		assertEquals(1, loadBalancer.getNode("b"));
		assertEquals(0, loadBalancer.getNode("a"));
		assertEquals(2, loadBalancer.getNode("c"));
		assertEquals(3, loadBalancer.getNode("d"));
		assertEquals(2, loadBalancer.getNode("c"));
	}

}
