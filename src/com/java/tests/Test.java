package com.java.tests;

import java.util.Scanner;

class Test {
	public static void main (String[] args) {
	Scanner scanner = new Scanner(System.in);
		int noOfTestCases = scanner.nextInt();
		int i, j;
		for(i = 0; i < noOfTestCases; i++){
		    int sizeOfArray = scanner.nextInt();
		    Integer elements[] = new Integer[sizeOfArray];
		    int elementsToFind = scanner.nextInt();
		    for(j = 0; j < elements.length; j++){
		        elements[j] = scanner.nextInt();
		    }
		    for(i = 0 ; i < (sizeOfArray - 1); i++){
		        for(j = i+1; j < sizeOfArray; j++){
		            if(elements[i]<elements[j]){
		                int temp = elements[j];
		                elements[j] = elements[i];
		                elements[i] = temp;
		            }
		        }
		    }
		    for(int k = 0 ; k < elementsToFind; k++){
		        System.out.print(elements[k]+" ");
		    }
		    System.out.println();
		}
	}
}