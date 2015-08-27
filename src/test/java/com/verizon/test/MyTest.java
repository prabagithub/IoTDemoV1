package com.verizon.test;

import static org.junit.Assert.*;

import org.junit.Test;

public class MyTest {

	@Test
	public final void test1() {
		boolean result = false;
		int i = 3;
		int j = 5;
		
		if(i < j){
			result = true;
		}
		assertTrue(result);
	}
	
	@Test
	public final void test2() {
		boolean result = false;
		int i = 3;
		int j = 5;
		
		if(j < i){
			result = true;
		}
		assertFalse(result);
	}

}
