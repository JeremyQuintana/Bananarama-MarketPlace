package javadb;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javadb.Post.Action;
import javadb.Post.Column;
import javadb.Post.Status;

import java.sql.Date;

public class DatabaseRef {

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
	public Map<Integer, Post> posts;
	
	public DatabaseRef() {
		try {				
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://35.189.1.213:3306/sept";
			String username = "root";
			String password = "bananasept";
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
			statement = conn.createStatement();
			System.out.println("Connected");
			
			posts = new HashMap<>();
			data = statement.executeQuery("select * from sale");
			while (data.next())
			{
				int id = data.getInt(1);
				posts.put(id, new Post(data));
			}
		} 
		
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	
	
	
	
	

//login details

	//user ID check
	public boolean checkId(String id) throws SQLException 
	{
		data = statement.executeQuery("select * from login");
		while (data.next())
			if (id.equalsIgnoreCase(data.getString("ID")))
				return true;
		return false;
	}

	//password check
	public boolean checkPassword(String id, String password) throws SQLException
	{
		data = statement.executeQuery("select * from login");
		while (data.next())
			if (id.equalsIgnoreCase(data.getString("ID")) && password.equals(data.getString("password")))
				return true;
		return false;
	}
//end login details

	
	
	
	
	
	
	
	//browse marketplace
		//main place display of for sale items, checks for current for sale items in db
		public void check_for_sale() throws SQLException
		{
			printPosts("select * from sale where Status= 'a'");
		}
	 
		//Show history of sale items for user
		public void sale_history(String ownerId) throws SQLException 
		{
			printPosts("select * from sale where ID='" + ownerId + "'");
		}
		
		//Show history of sale items for user
		public void searchCategory(String cate_desc) throws SQLException 
		{
			printPosts("select * from sale where Category='"+cate_desc+"'");
		}
		
		//Show history of sale items for user
		public void sale_history(String cate_word, String desc_word) throws SQLException 
		{
			printPosts("select * from sale where Category='"+cate_word+"'AND Item_Description like'%"+ desc_word +"%'");
		}
		
//
//		
//		
//		
//		
//		
//		
//		
//		
//	//Post sell items in marketplace
//		public void addPost(String id, String itemname, String descpt, int price, String cate) 
//		{
//			java.sql.Date curdate = new java.sql.Date(new java.util.Date().getTime());
//			update("insert into sale(ID, Item_Name, Item_Description, Price, Status, Date, Category) VALUES ('"+ id +"','"+itemname+"','"+descpt+"','"+price+"', 'A', '"+curdate+"','"+cate+"')");
//		}
//
//		//edit price items in marketplace
//		public void editPost(Column column, String edit) {
//			update(column, edit, id);
//		}
//
//		//Delete item from Marketplace
//		public void deletePost() {
//			update(Column.STATUS, "D", id);
//		}
//
//		//Marked Item as sold on Marketplace
//		public void postSold() {
//			update(Column.STATUS, "S", id);
//		}
//	public void deletePost(int id)
//	{
//		posts.remove(id);
//		
//		// change so post id's match arraylist's
//		for (int i=1; i<posts.size(); i++)
//			posts.get(i).setId(i);
//	}
	

	
	
	
	
	
//browse marketplace
	public void printPostsRaw() throws SQLException
	{
		data = statement.executeQuery("select * from sale");
		while (data.next()) {
		        System.out.print(new Post(data));
		    System.out.println();
		}
	}

//Post sell items in marketplace
	public void sell_item(String owner, String title, String desc, int price, String cate) throws SQLException {
		java.sql.Date curdate = new java.sql.Date(new java.util.Date().getTime());
		update("insert into sale(ID, Item_Name, Item_Description, Price, Status, Date, Category) VALUES ('"+ owner +"','"+title+"','"+desc+"','"+price+"', 'A', '"+curdate+"','"+cate+"')");
		
		LinkedList<Integer> sorted = new LinkedList<>(posts.keySet());
		Collections.sort(sorted);
		int id = sorted.getLast()+1;
		posts.put(id, new Post(id, owner, title, desc, price, curdate, cate));
		/*may not work*/
	}


	
	
	
	
	
	
	
	
	
	// actual commands to database
	public static void update(String str)
	{
		try 
		{
			statement.executeUpdate(str);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Map<Integer, Post> printPosts(String str) throws SQLException
	{
		Map<Integer, Post> matched = new HashMap<>();
		try
		{
			data = statement.executeQuery(str);
			while (data.next()) {
				int id = data.getInt(1);
				
				System.out.println(posts.get(id));
				matched.put(id, posts.get(id));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return matched;
		
		
	}
	
	
}