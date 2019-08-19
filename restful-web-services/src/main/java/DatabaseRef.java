package javadb;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	
	Map<String, Post> posts;
	
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
				posts.put(data.getString(0), new Post(data, this));
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
	public void check_for_sale() 
	{
//		data = query("select * from sale where Status= 'a'");
//		while (data.next()) {
//			System.out.println(data.getString(2) + " " + data.getString(3) + " " + data.getString(4) + " " + data.getString(5)+" " + data.getString(7) + " " + data.getString(8));
//		}
		for (Post post : posts.values())
			if (post.getStatus() == 'a')
				System.out.println(post.toString());
	}
 
	//Show history of sale items for user
	public void sale_history(String ownerId) {
//			data = query("select * from sale where ID='" + id + "'");
//			
//			while (data.next()) {
//				System.out.println(posts.get(id));
//			}
		
		for (Post post : posts.values())
			if (post.getOwnerId().equals(ownerId))
				System.out.println(post.toString());
	}

//Post sell items in marketplace
	public void sell_item(String id, String itemname, String descpt, int price, String cate) {
		java.sql.Date curdate = new java.sql.Date(new java.util.Date().getTime());
		update("insert into sale(ID, Item_Name, Item_Description, Price, Status, Date, Category) VALUES ('"+ id +"','"+itemname+"','"+descpt+"','"+price+"', 'A', '"+curdate+"','"+cate+"')");
//		posts.put(new Post(id, this));
		/*incomplete*/
	}

	public void deletePost(String id)
	{
		posts.get(id).delete();
		posts.remove(id);
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
	
	public static void update(Column column, String value, String id)
	{
		update("Update sale set " + column.key() + "='"+ value +"' where PostID='"+id+"'");
	}
	
	
}