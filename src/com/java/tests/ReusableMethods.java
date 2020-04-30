package com.java.tests;

public class ReusableMethods {
	public static String getClassName(Object obj) {
		return obj.getClass().getName().substring(obj.getClass().getName().lastIndexOf(".")+1, obj.getClass().getName().length()).strip();
	}

}
