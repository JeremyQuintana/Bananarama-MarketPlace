package javadb;
import javadb.Database;

import java.sql.SQLException;
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
		//Database.check_for_sale();
	//	System.out.println("history of sales for id");
		//Database.sale_history(id);
		
	//	Database.sell_item(id, "Chair", "New, white", 20, "Household");
		System.out.println("confirmed");
	//	Database.edit_price(3, 100);
	//	Database.edit_item_name(4 , "Not a chair");
	//	Database.edit_description(7, "new item description");
	//	Database.delete_item(8);
	//	Database.sold_item(9);
	//	Database.search_by_category("Household"); 
		System.out.println("confirmed");
		System.out.println("confirmed");
		System.out.println("confirmed");
		System.out.println(Database.check_for_sale());
	//	Database.search_by_category_descpt("Household", "horse");
	}
}
