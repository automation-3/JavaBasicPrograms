package com.java.tests;

public class FindingIndex {
	public static void main(String[] args) {
		System.out.println(hasBad("ba"));
		System.out.println(hasBad("xba"));
	}

	public static boolean hasBad(String str) {
		boolean flag = false;
		if (str.length() > 0) {
			for (int i = 0; i <= 1; i++) {
				if ((str.charAt(i)) == 'b') {
					flag = true;
					break;
				}
			}
		}
		if (flag) {
			return true;
		} else {
			return false;
		}
	}
}
