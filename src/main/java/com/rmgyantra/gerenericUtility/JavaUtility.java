package com.rmgyantra.gerenericUtility;

import java.util.Random;

/**
 * 
 * @author Deepak
 *
 */
public class JavaUtility {
	/**
	 * 
	 * @return random number in the form String 
	 */
	public static String getRandomNumber() {
		Random ran = new Random();
		int ranDomNumber = ran.nextInt(1000);
		return ""+ranDomNumber;
	}
}
