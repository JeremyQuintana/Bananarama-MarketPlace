package javadb;
import javadb.Login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Maintest {

	public static void main(String[] args) throws Exception {

		String id = null;
		String password = null;
		Login db = new Login();
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome");
		System.out.println("");
		System.out.println("1. Login");
		System.out.println("X. Exit");
		System.out.println("Please Enter An Option:");
		String menuchoice = sc.nextLine();
		boolean loopid = true;
		boolean looppassword = true;
		while (!menuchoice.equalsIgnoreCase("1") && !menuchoice.equalsIgnoreCase("X")) {
			System.out.println("Please Input A Valid Option");
			menuchoice = sc.nextLine();
		}
		if (menuchoice.equalsIgnoreCase("1")) {
			// Loop for checking valid ID
			while (loopid) {
				System.out.println("Please enter your id:");
				id = sc.nextLine();
				// Checks id is valid
				if (db.checkId(id) == true) {
					System.out.println("USER ID VALID");
					loopid = false;
				} else if (id.equalsIgnoreCase("x")) {
					System.out.println("Goodbye.");
					System.exit(0);
				} else {
					System.out.println("NOT VALID USER ID");
				}
			}
			while (looppassword) {
				System.out.println("Please enter your password:");
				password = sc.nextLine();
				if (password.equalsIgnoreCase("x")) {
					System.out.println("Goodbye.");
					System.exit(0);
				}
				if (db.checkPassword(id, password) == true) {
					System.out.println("USER PASSWORD VALID");
					looppassword = false;
				} else {
					System.out.println("USER PASSWORD INVALID");
				}
			}
		}
	}
}