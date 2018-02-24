package com.lidongmin.test;

import java.math.BigDecimal;

import org.junit.Test;

public class MyTest {
	@Test
	public void myTest(){
		BigDecimal b1 = new BigDecimal("12.0");
		BigDecimal b2 = new BigDecimal("13.0");
		BigDecimal b3 = null;
		
		b3 = b1.add(b2);
		
		System.out.println(b3.doubleValue());
	}
}
