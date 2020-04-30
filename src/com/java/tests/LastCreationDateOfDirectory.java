package com.java.tests;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Comparator;

public class LastCreationDateOfDirectory {
	static String originalPath = "C:\\Users\\athada\\Desktop\\Results\\";
	static String customDirName = "custom29";
	static String tempDirName = "";
	static String directoryInSymbol = "\\";
	public static void main(String[] args) {
		File dir = new File(originalPath+customDirName+directoryInSymbol);
		deleteResultsDirectory(dir);
		
	}
	
	static boolean deleteResultsDirectory(File directoryToBeDeleted) {
		File[] allContents = directoryToBeDeleted.listFiles();
		if (allContents != null) {
			for (File file : allContents) {
				deleteResultsDirectory(file);
			}
		}
		return directoryToBeDeleted.delete();
	}
	
	public static void deleteDirectory1(File file) {
		int childDirs = getListOfDirectories(file);
		System.out.println("List Of Dirs : "+childDirs);
		if(childDirs != 0) {
			tempDirName = file.listFiles()[0].getAbsolutePath();
			System.out.println(tempDirName);
			deleteDirectory1(new File(tempDirName));
		}else {
			deleteFilesInDir(new File(tempDirName));
			deleteDirectory1(new File(tempDirName));
		}
	}
	
	public static int getListOfDirectories(File file) {
		int dirCount = 0;
		File[] files = file.listFiles();
		for (File file2 : files) {
			if(file2.isDirectory())
				dirCount++;
		}
		return dirCount;
	}
	
	public static void deleteFilesInDir(File file) {
		File[] files = file.listFiles();
		for (int i = 0; i < files.length; i++) {
			files[i].delete();
		}
	}

	private static void printFiles(File[] files) {
		for (File file : files) {
			long m = getFileCreationEpoch(file);
			Instant instant = Instant.ofEpochMilli(m);
			LocalDateTime date = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
			System.out.println(date + " - " + file.getName());
		}
	}

	public static void sortFilesByDateCreated(File[] files) {
		Arrays.sort(files, new Comparator<File>() {
			public int compare(File f1, File f2) {
				long l1 = getFileCreationEpoch(f1);
				long l2 = getFileCreationEpoch(f2);
				return Long.valueOf(l1).compareTo(l2);
			}
		});
	}

	public static long getFileCreationEpoch(File file) {
		try {
			BasicFileAttributes attr = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
			return attr.creationTime().toInstant().toEpochMilli();
		} catch (IOException e) {
			throw new RuntimeException(file.getAbsolutePath(), e);
		}
	}
}