package com.java.tests;

import java.util.Random;

public class Parent {
	
	public static void main(String[] args) {
		Random random = new Random();
		String s = "adp"+random.nextInt()+"dummy";
		System.out.println("//*[matches(@text,'adp[]')]");
		
	}

}
