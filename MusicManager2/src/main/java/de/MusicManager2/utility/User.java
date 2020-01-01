package de.MusicManager2.utility;

import java.util.Scanner;

public class User {

	public static Scanner sc = new Scanner(System.in);
	
	public static String input(String message) {
		System.out.println(message);
		return sc.nextLine().toLowerCase();
	}
	
	public static void output(String message) {
		System.out.println(message);
	}
	
}