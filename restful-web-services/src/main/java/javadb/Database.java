package javadb;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.Date;

public class Database {

	private static Statement statement;
	private static ResultSet data;
	private Connection conn;
	String id;
	String password;
	String cate;
	int p_id;
	int new_price;
	String new_title;
	String new_despt;
	
	public Database() {
		try {				
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://35.189.1.213:3306/sept";
			String username = "root";
			String password = "bananasept";
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
			statement = conn.createStatement();
			System.out.println("Connected");
			} 
		
		catch (Exception e) {
			System.out.println(e);
			}
		}

//login details

	//user ID check
	public boolean checkId(String id) {
		try {
			data = statement.executeQuery("select * from login");
			while (data.next()) {
				if (id.equalsIgnoreCase(data.getString("ID"))) {
					return true;
				}
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	//password check
	public boolean checkPassword(String id, String password) {
		try {
			data = statement
					.executeQuery("select * from login where ID='" + id + "' and Password=" + "'" + password + "'");
			while (data.next()) {
				if (id.equalsIgnoreCase(data.getString("ID")) && password.equals(data.getString("password"))) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
//end login details

//browse marketplace
	//main place display of for sale items, checks for current for sale items in db
	public static void check_for_sale() {
		try {
			data = statement.executeQuery("select * from sale where Status= 'a'");
			while (data.next()) {
				System.out.println(data.getString(2) + " " + data.getString(3) + " " + data.getString(4) + " " + data.getString(5)+" " + data.getString(7) + " " + data.getString(8));
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
 
	//Show history of sale items for user
	public static void sale_history(String id) {
		try {
			data = statement.executeQuery("select * from sale where ID='" + id + "'");
			
			while (data.next()) {
				System.out.println(data.getString(2) + " " + data.getString(3) + " " + data.getString(4) + " " + data.getString(5)+" " + data.getString(7) + " " + data.getString(8));
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

//Post sell items in marketplace
	public static void sell_item(String id, String itemname, String descpt, int price, String cate) {
		java.sql.Date curdate = new java.sql.Date(new java.util.Date().getTime());
		try {
			statement.executeUpdate("insert into sale(ID, Item_Name, Item_Description, Price, Status, Date, Category) VALUES ('"+ id +"','"+itemname+"','"+descpt+"','"+price+"', 'A', '"+curdate+"','"+cate+"')");
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

//edit price items in marketplace
	public static void edit_price(int p_id, int new_price) {
		
		try {
			statement.executeUpdate("Update sale set Price='"+ new_price+"' where PostID='"+p_id+"'");
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

//edit Title in marketplace
	public static void edit_item_name(int p_id, String new_title) {
		
		try {
			statement.executeUpdate("Update sale set Item_Name='"+ new_title+"' where PostID='"+p_id+"'");
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
//edit Description in marketplace
	public static void edit_description(int p_id, String new_despt) {
		
		try {
			statement.executeUpdate("Update sale set Item_Description='"+ new_despt+"' where PostID='"+p_id+"'");
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

//Delete item from Marketplace
	public static void delete_item(int p_id) {
		
		try {
			statement.executeUpdate("Update sale set Status= 'D' where PostID='"+p_id+"'");
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

//Marked Item as sold on Marketplace
	public static void sold_item(int p_id) {
		
		try {
			statement.executeUpdate("Update sale set Status= 'S' where PostID='"+p_id+"'");
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}