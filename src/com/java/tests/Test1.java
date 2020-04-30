package com.java.tests;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public abstract class Test1 {
	public static final String SELECTCONDITIONWITHINONEDATE[][][] = {{{"within","next","1"}}};
	public static void main(String[] args) throws IOException {
		System.out.println(m());
		System.out.println(SELECTCONDITIONWITHINONEDATE[0][0][2]);
		
	}

	public static String m() throws IOException {
		try (BufferedReader r = new BufferedReader(new FileReader("C:\\Users\\athada\\Desktop\\ErrorLogs.txt"))) {
			return r.readLine();
		}
	}
}