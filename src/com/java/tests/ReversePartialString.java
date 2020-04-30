package com.java.tests;

public class ReversePartialString {

	public static String left2(String str) {
		String result = "", lastwords = "";
		if (str.length() >= 2) {
			lastwords = str.substring(0, 2);
			System.out.println("Lastwords - "+lastwords);
			result = str.substring(2, str.length()) + lastwords.charAt(0) + lastwords.charAt(1);
		}
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(left2("Hello"));
	}

}
