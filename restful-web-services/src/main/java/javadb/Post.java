package javadb;

import java.awt.image.BufferedImage;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Post {
	
	/*functionality not added*/
	public Post(String title, String description, double price, BufferedImage photo, String ownerId)
	{
		this(title, description, price, ownerId);
	}
	
	public Post(String title, String description, double price, String ownerId)
	{
		super();
		this.title = title;
		this.description = description;
		this.price = price;
	}
	
	public Post(ResultSet post, DatabaseRef db)
	{
		try {
//			ResultSet post = db.find("select * from sale where PostID=" + identifier);
			id = post.getString(0);
			ownerId = post.getString(1);
			title = post.getString(2);
			description = post.getString(3);
			price = post.getDouble(4);
			description = post.getString(5);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private String description;
	private String title;
	private double price;
	private String ownerId;
	private char status;
	private String id;
	
	//edit price items in marketplace
		public void edit(Column column, String edit) {
			DatabaseRef.update(column, edit, id);
		}

	//Delete item from Marketplace
		public void delete() {
			DatabaseRef.update(Column.STATUS, "D", id);
		}

	//Marked Item as sold on Marketplace
		public void sold() {
			DatabaseRef.update(Column.STATUS, "S", id);
		}
		
		public enum Action
		{
			SELL, SOLD, DELETE, EDIT
		}
		public enum Column
		{
			DESC("Item_Description"), NAME("Item_Name"), PRICE("Price"), STATUS("Status");
			
			private String key;
			private Column(String key)
			{
				this.key = key;
			}
			
			public String key()
			{
				return key;
			}
		}
	
	public String toString()
	{
		return description + " " + title + " " + price + " " + ownerId + " " + status + " ";
	}
	
	public char getStatus()		{return status;}
	public String getOwnerId()	{return ownerId;}
}
