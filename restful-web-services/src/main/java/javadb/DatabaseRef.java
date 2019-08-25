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

	
	public static void main(String[] args) throws SQLException
	{
//		DatabaseRef db = new DatabaseRef();
//		
//		DatabaseRef.update("delete from sale where PostID = 26");
	}
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
			canConnect();
			
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
	
	public boolean canConnect()
	{
		try
		{
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://35.189.1.213:3306/sept";
			String username = "root";
			String password = "bananasept";
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
			statement = conn.createStatement();
			System.out.println("Connected");
			return true;
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}
	
	
	
	
	
	
	

//login details

	//user ID check
	public boolean checkId(String id) throws SQLException 
	{
		data = statement.executeQuery("select * from login where ID='" + id + "'");
		while (data.next())
			if (id.equalsIgnoreCase(data.getString("ID")))
				return true;
		return false;
	}

	//password check
	public boolean checkPassword(String id, String password) throws SQLException
	{
		data = statement.executeQuery("select * from login where ID='" + id + "' AND password='" + password + "'");
		while (data.next())
			if (id.equalsIgnoreCase(data.getString("ID")) && password.equals(data.getString("password")))
				return true;
		return false;
	}
	//end login details

	
	
	
	
	
	
	
	//browse marketplace
	//main place display of for sale items, checks for current for sale items in db
	public Map<Integer, Post> check_for_sale() throws SQLException
	{
		return getPosts("select * from sale where Status= 'a'");
	}

	//Show history of sale items for user
	public Map<Integer, Post> sale_history(String ownerId) throws SQLException 
	{
		return getPosts("select * from sale where ID='" + ownerId + "'");
	}

	//Show history of sale items for user
	public Map<Integer, Post> searchCategory(String cate_desc) throws SQLException 
	{
		return getPosts("select * from sale where Category='"+cate_desc+"'");
	}

	//Show history of sale items for user
	public Map<Integer, Post> sale_history(String cate_word, String desc_word) throws SQLException 
	{
		return getPosts("select * from sale where Category='"+cate_word+"'AND Item_Description like'%"+ desc_word +"%'");
	}
		

	
	
	
	
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
		
		data = statement.executeQuery("select * from sale");
		while (data.next())
			if (data.isLast())
				posts.put(data.getInt(1), new Post(data.getInt(1), owner, title, desc, price, curdate, cate));
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
	
	public static ResultSet query(String str) throws SQLException
	{
		return statement.executeQuery(str);
	}
	
	public Map<Integer, Post> getPosts(String str) throws SQLException
	{
		Map<Integer, Post> matched = new HashMap<>();
		
		data = statement.executeQuery(str);
		while (data.next()) {
			int id = data.getInt(1);
			matched.put(id, posts.get(id));
		}
		
		return matched;
	}
	
}
