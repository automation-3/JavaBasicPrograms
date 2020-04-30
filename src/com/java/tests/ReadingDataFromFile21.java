package com.java.tests;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

public class ReadingDataFromFile21 {

	public static void main(String[] args) throws Throwable {
		File inputFile = new File("C:\\Users\\athada\\Desktop\\Terraform\\terraform.tfstate");
		File outputFile = new File("C:\\Users\\athada\\Desktop\\Terraform\\inventory");
		if(outputFile.exists()) {
			outputFile.delete();
			outputFile.createNewFile();
		}
		try {
		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		BufferedWriter out = new BufferedWriter(new FileWriter(outputFile));
		String ip = null,resultIP="", cmpName=null,resultCmpName = "",value="";
		int count = 0;
		Map<String, String> map = new HashMap<String, String>();
		while ((ip=br.readLine())!=null) {
			if(ip.contains("\"ip\"")){
				ip=ip.replace("\"","");
				ip=ip.replace(",","");
				ip=ip.replace(":","");
				ip=ip.replace("ip","");
				ip=ip.trim();
				if(!ip.equals("allocated")) {
					count++;
					if((count % 2) == 0) {
//						map.put(ip, cmpName);
						System.out.println("IP - "+ip+" , ComputerName - "+cmpName);
					}
				}
			}
			if(ip.contains("\"computer_name\"")){
				cmpName=ip.replace("\"","");
				cmpName=cmpName.replace(",","");
				cmpName=cmpName.replace(":","");
				cmpName=cmpName.replace("computer_name","");
				cmpName=cmpName.trim();
				count++;
			}
		}
		
		for(Map.Entry<String, String> entry : map.entrySet()) {
//			System.out.println("Key - "+entry.getKey()+" , Value - "+entry.getValue());
			value = entry.getValue();
			if(value.trim().toLowerCase().contentEquals("dockerdemo1")) {
				resultCmpName = resultCmpName+entry.getKey()+"\n";
			}else {
				resultIP = resultIP+entry.getKey()+"\n";
			}
		}
		
		out.write("[demo]\n");
		out.write("cicddemo1.lab.opentext.com\ncicddemo2.lab.opentext.com\n");
		out.write("\n[demo:vars]\r\n" + 
				"ansible_user=Administrator\r\n" + 
				"ansible_password=N0b0r33l\r\n" + 
				"ansible_connection=winrm\r\n" + 
				"ansible_winrm_server_cert_validation=ignore\n\n");
		out.write("[soap]\n");
		out.write("cicddemo2.lab.opentext.com\n");
		out.write("\n[soap:vars]\r\n" + 
				"ansible_user=Administrator\r\n" + 
				"ansible_password=N0b0r33l\r\n" + 
				"ansible_connection=winrm\r\n" + 
				"ansible_winrm_server_cert_validation=ignore\n\n");
		out.write("[target]\n"+resultCmpName);
		br.close();
		out.close();
		System.out.println("Successfully Generated inventory file - "+outputFile.getAbsolutePath());
		}catch (FileNotFoundException fnfe) {
			System.out.println("Unable to find a file - "+fnfe.getMessage());
		}
	}
}
