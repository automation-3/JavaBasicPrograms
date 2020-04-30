package com.java.tests;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

public class ReadingDataFromFile2 {

	public static void main(String[] args) throws Throwable {
		File inputFile = new File("C:\\Users\\athada\\Desktop\\Terraform\\terraform.tfstate");
		File outputFile = new File("C:\\Users\\athada\\Desktop\\Terraform\\inventory");
		File jenkinsSlaveFile = new File("C:\\Users\\athada\\Desktop\\Terraform\\jenkins-slave.bat");
		if(outputFile.exists()) {
			outputFile.delete();
			outputFile.createNewFile();
		}
		if(jenkinsSlaveFile.exists()) {
			jenkinsSlaveFile.delete();
			jenkinsSlaveFile.createNewFile();
		}
		try {
		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		BufferedWriter out = new BufferedWriter(new FileWriter(outputFile));
		BufferedWriter jenkinsBatFile = new BufferedWriter(new FileWriter(jenkinsSlaveFile));
		jenkinsBatFile.write("set JAVA_HOME=\"C:\\Program Files\\Java\\OpenJDK-11.0.2\\bin\"\n");
		jenkinsBatFile.write("cd C:\\Sample\n");
		String ip = null,resultIP="", agent = "DemoAgent_";
		int count = 1;
		while ((ip=br.readLine())!=null) {
			if(ip.contains("\"ip\"")){
				ip=ip.replace("\"","");
				ip=ip.replace(",","");
				ip=ip.replace(":","");
				ip=ip.replace("ip","");
				ip=ip.trim();
				if(!ip.equals("allocated")) {
					jenkinsBatFile.write("java -jar jenkins-cli.jar -s http://10.96.73.148:8080/ get-node RelQAAutFE09 | java -jar jenkins-cli.jar -s http://10.96.73.148:8080/ create-node "+(agent+count)+"\n");
					jenkinsBatFile.write("java -jar agent.jar -jnlpUrl http://10.96.73.148:8080/computer/"+(agent+count)+"/slave-agent.jnlp\n");
					resultIP = resultIP+ip+"\n";
					count++;
				}
			}
		}
		jenkinsBatFile.close();
		out.write("[demo]\n"+resultIP);
		out.write("\n[demo:vars]\r\n" + 
				"ansible_user=Administrator\r\n" + 
				"ansible_password=N0b0r33l\r\n" + 
				"ansible_connection=winrm\r\n" + 
				"ansible_winrm_server_cert_validation=ignore");
		br.close();
		out.close();
		System.out.println("Successfully Generated inventory file - "+outputFile.getAbsolutePath());
		System.out.println("Successfully Generated Jenkins batch file - "+jenkinsSlaveFile.getAbsolutePath());
		}catch (FileNotFoundException fnfe) {
			System.out.println("Unable to find a file - "+fnfe.getMessage());
		}
	}
}
