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

import javadb.ChatBase.Overhead;
import javadb.Post.Action;
import javadb.Post.Column;
import javadb.Post.Status;

import java.sql.Date;


public class DatabaseRef {

	
	public static void main(String[] args) throws SQLException
	{
		ChatBase db = new ChatBase();
		
		
		db.addText("What do you call a sad strawberry? a blueberry", 2 , "s1111111");
		db.addText("Robert, I am changing classes, please do not contact me", 2, "s2222222");
		db.addText("Howdy Pardneerrr", 1, "s1234567");
		db.addText("cowboy on discussions mitch", 1, "s1111111");
		
		System.out.println(db.usersExist("s1234567", "s1111111"));
		System.out.println(db.usersExist("s1111111", "s1234567"));		
		System.out.println(db.usersExist("2", "2"));
	}
	private static Statement statement;
	protected static ResultSet data;
	private Connection conn;
	String id;
	String password;
	String cate;
	int p_id;
	int new_price;
	String new_title;
	String new_despt;
	public Map<Integer, Post> posts;
	
	public DatabaseRef() throws SQLException
	{			
		canConnect();
		
		posts = new HashMap<>();
		data = query("select * from sale");
		while (data.next())
		{
			int id = data.getInt(1);
			posts.put(id, new Post(data));
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
		return valuesExist(String.format("select count(*) from %s where upper(%s) = '%s'", "login", "ID", id.toUpperCase()));
	}

	//password check
	public boolean checkPassword(String id, String password) throws SQLException
	{
		return valuesExist(String.format("select count(*) from %s where upper(%s) = '%s' AND %s = '%s'", "login", "ID", id.toUpperCase(), "password", password));
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
	public static void update(String str) throws SQLException
	{
		statement.executeUpdate(str);
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
	
	// given a query, check if the values searched exist in the database
	public boolean valuesExist(String str) throws SQLException
	{
		data = query(str);
		while (data.next())
			if (data.getInt(1) > 0)
				return true;
		return false;
	}
		
}
