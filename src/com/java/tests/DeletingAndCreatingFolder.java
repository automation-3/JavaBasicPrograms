package com.java.tests;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class DeletingAndCreatingFolder {
	public static LocalDate localDate = LocalDate.now();
	public static String MONTH = "";
	public static String HOUR = "";
	public static final String INCREMENT = "increment";
	public static final String DECREMENT = "decrement";
	private static SimpleDateFormat dateFormat ;
	private static Calendar calendar = Calendar.getInstance();
	
	public static void main(String[] args) {
//		deleteAllChildrensOfDirectory("C:\\Users\\athada\\Desktop\\JavaDemoFolders\\Demo");
		System.out.println("Previous Day - "+getPreviousDaysOfMonth(1));
//		if(dir.exists()) {
//			System.out.println("Directory Already Exist!!");
//			dir.delete();
//			System.out.println("Directory Deleted!!");
//		}else {
//			dir.mkdir();
//			System.out.println("Directory Created Successfully!!!");
//		}
		
	}
	
	public static void deleteAllChildrensOfDirectory(String directoryPath) {
		File directory = new File(directoryPath);
		File[] childFiles = directory.listFiles();
		for (File file : childFiles) {
			file.delete();
			System.out.println("Deleted a File - "+file.getPath());
		}
	}
	
	public static String getNextDaysOfMonth(int... noOfDays) {
		String presentMonth=localDate.getMonth().toString();
		if(!presentMonth.equalsIgnoreCase(MONTH) && !MONTH.isEmpty())
			MONTH=presentMonth;
		if ((noOfDays.length == 0) || (noOfDays[0] == 0) || (noOfDays[0] == 1)) {
			if(localDate.getDayOfMonth() == calendar.getActualMaximum(Calendar.DATE))
				return localDate.getDayOfMonth()+"";
			else
				return (localDate.getDayOfMonth() + 1) + "";
		}
		else if ((noOfDays.length != 0) && (noOfDays[0] == calendar.getActualMaximum(Calendar.DATE))) {
			MONTH = localDate.getMonth().plus(1).toString();
			calendar.add(Calendar.MONTH, 1);
			calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			System.out.println("Month - "+MONTH);
			return calendar.getTime().getDate()+"";
		}
		else if ((localDate.getDayOfMonth() + (noOfDays[0])) > (calendar.getActualMaximum(Calendar.DATE))) {
			MONTH = localDate.getMonth().plus(1).toString();
			System.out.println("Month - "+MONTH);
			return ((localDate.getDayOfMonth() + (noOfDays[0])) - (calendar.getActualMaximum(Calendar.DATE))) + "";
		} else
			return (localDate.getDayOfMonth() + (noOfDays[0])) + "";
	}
	
	public static String getPreviousDaysOfMonth(int... noOfDays) {
		int presentDate=localDate.getDayOfMonth();
		int presentMonth = localDate.getMonthValue();
		
		if((noOfDays.length == 0) || (noOfDays[0] == 0) || (noOfDays[0] == 1)) {
			if(localDate.getDayOfMonth() == calendar.getActualMinimum(calendar.DATE)) {
				MONTH = localDate.getMonth().minus(1).toString();
				calendar.add(Calendar.DAY_OF_MONTH, -2);
				calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
				return calendar.getTime().getDate()+"";}
			else
				return localDate.getDayOfMonth()+"";
		}
		else if ((noOfDays.length != 0) && (noOfDays[0] == calendar.getActualMinimum(Calendar.DATE)))
			return noOfDays[0] + "";
		else if ((localDate.getDayOfMonth() + (noOfDays[0])) > (calendar.getActualMinimum(Calendar.DATE))) {
			MONTH = localDate.getMonth().minus(1).toString();
			System.out.println("Month - "+MONTH);
			return ((localDate.getDayOfMonth() + (noOfDays[0])) - (calendar.getActualMinimum(Calendar.DATE))) + "";
		} else
			return (localDate.getDayOfMonth() + (noOfDays[0])) + "";
	}

}
