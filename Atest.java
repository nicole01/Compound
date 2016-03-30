package fuli1.test;

import static org.junit.Assert.*;

import org.junit.Test;

import fuli1.fuli1;

public class Atest {

	
	@Test
	public void test() {
		double a=new fuli1().CalculateA(1, 3, 10000, 0.03);
		boolean flag=0.0==a-10927.27;
        assertEquals(true, flag);
	}

}
