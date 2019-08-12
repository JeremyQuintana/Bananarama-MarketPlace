package javadb;
import javadb.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Maintest {

	public static void main(String[] args) throws Exception {

		String id = null;
		String password = null;
		Database db = new Database();
		Scanner sc = new Scanner(System.in);
		boolean loopid = true;
		boolean looppassword = true;
	
		// Loop for checking valid ID
		while (loopid) {
			System.out.println("Please enter your id:");
			id = sc.nextLine();
			// Checks id is valid
			if (db.checkId(id) == true) {
				System.out.println("USER ID VALID");
				loopid = false;
			} 
			else {
				System.out.println("NOT VALID USER ID");
			}
		}
		while (looppassword) {
			System.out.println("Please enter your password:");
			password = sc.nextLine();
			
			if (db.checkPassword(id, password) == true) {
				System.out.println("USER PASSWORD VALID");
				looppassword = false;
				
			} 
			else {
				System.out.println("USER PASSWORD INVALID");
			}
		}
		Database.check_for_sale();
		System.out.println("history of sales for id");
		Database.sale_history(id);
		Database.sell_item(id, "table", "Large, round table", 12);
	}
}
