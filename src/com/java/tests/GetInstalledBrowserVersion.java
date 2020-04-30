package com.java.tests;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class GetInstalledBrowserVersion {
	public static void main(String[] args) throws Throwable{

		ArrayList<String> output = new ArrayList<String>();
		Process p = Runtime.getRuntime().exec("reg query \"HKLM\\Software\\Microsoft\\Internet Explorer\" /v Version");
		BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()), 8 * 1024);
		BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
		String s = null;

		while ((s = stdInput.readLine()) != null) {
			output.add(s);
		}

		String internet_explorer_value = (output.get(2));
		String version_ie = internet_explorer_value.trim().split("   ")[2];

		// ------------------------------------

		stdInput = null;
		stdError = null;
		s = null;
		p = null;
		output.clear();

		p = Runtime.getRuntime().exec("reg query \"HKLM\\Software\\Mozilla\\Mozilla Firefox\" /v CurrentVersion");
		stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()), 8 * 1024);
		stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

		while ((s = stdInput.readLine()) != null) {
			output.add(s);
		}

		String ff_value = (output.get(2));
		String version_ff = ff_value.trim().split("   ")[2];

		// ------------------------------------

		System.out.println("Browser Versions:\n");
		System.out.println("  IE: " + version_ie);
		System.out.println("  FF: " + version_ff);

	}
}