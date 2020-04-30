package com.java.tests;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

public class ReadingDataFromFile {

	public static void main(String[] args) throws Throwable {
		File inputFile = new File("C:\\Terraform\\terraform.tfstate");
		File outputFile = new File("C:\\Terraform\\inventory");
		if (outputFile.exists()) {
			outputFile.delete();
			outputFile.createNewFile();
		}
		try {
			BufferedReader br = new BufferedReader(new FileReader(inputFile));
			BufferedWriter out = new BufferedWriter(new FileWriter(outputFile));
			String value = null, winosIP = "", cmpName = null, centosIP = "";
			Map<String, String> map = new HashMap<String, String>();
			while ((value = br.readLine()) != null) {
				if (value.contains("\"ip\"")) {
					value = value.replace("\"", "");
					value = value.replace(",", "");
					value = value.replace(":", "");
					value = value.replace("ip", "");
					value = value.trim();
					if (!value.equals("allocated")) {
						map.put(value, cmpName);
						System.out.println("IP - " + value + " , ComputerName - " + cmpName);
					}
				}
				if (value.contains("\"name\"")) {
					cmpName = value.replace("\"", "");
					cmpName = cmpName.replace(",", "");
					cmpName = cmpName.replace(":", "");
					cmpName = cmpName.trim();
				}
			}

			for (Map.Entry<String, String> entry : map.entrySet()) {
				value = entry.getValue();
				if (value.trim().toLowerCase().contains("centos")) {
					centosIP = centosIP + entry.getKey() + "\n";
				}
				if (value.trim().toLowerCase().contains("win")) {
					winosIP = winosIP + entry.getKey() + "\n";
				}
			}
			out.write("[centos]\n" + centosIP + "\n");
			out.write("[windows]\n" + winosIP);
			out.write("\n[windows:vars]\r\n" + "ansible_user=Administrator\r\n" + "ansible_password=Opentext1!\r\n"
					+ "ansible_connection=winrm\r\n" + "ansible_winrm_server_cert_validation=ignore");
			br.close();
			out.close();
			System.out.println("Successfully Generated inventory file - " + outputFile.getAbsolutePath());
		} catch (FileNotFoundException fnfe) {
			System.out.println("Unable to find a file - " + fnfe.getMessage());
		}
	}
}
