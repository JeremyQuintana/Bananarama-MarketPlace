package javadb;

import java.awt.image.BufferedImage;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import javadb.Post.Column;

public class Post {
	
	// raw post creation
	public Post(int id, String owner, String title, String description, int price, Date date, String category)
	{
		this.id = id;
		this.ownerId = owner;
		this.title = title;
		this.description = description;
		this.price = price;
		this.category = category;
		this.status = Status.AVAILABLE;
		this.datePosted = date;
	}
	
	// post creation from a database
	public Post(ResultSet post)
	{
		try {
			id = post.getInt(1);
			ownerId = post.getString(2);
			title = post.getString(3);
			description = post.getString(4);
			price = post.getInt(5);
			status = Status.getStatus(post.getString(6));
			datePosted = post.getDate(7);
			category = post.getString(8);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	private String description;
	private String title;
	private int price;
	private String ownerId;
	private Status status;
	private Date datePosted;
	private String category;
	private int id;
	// by giving another name, can put daatabase in "test mode"
	public static String TABLE_NAME = "sale";
	//edit post column in marketplace
	public void edit(Column column, String edit) {
		update(column, edit);
		update(column, edit, "" + id);

	}

	//Delete item from Marketplace
	public void delete() {
		update(Column.STATUS, "D");
		update(Column.STATUS, "D", "" + id);
	}

	//Marked Item as sold on Marketplace
	public void sold() {
		update(Column.STATUS, "S");
		update(Column.STATUS, "S", "" + id);
	}
	
	// update THIS CLASS
	private void update(Column column, String edit)
	{
		switch (column)
		{
			case NAME : 	title = edit;					break;
			case DESC : 	description = edit;				break;
			case STATUS :	status = Status.getStatus(edit);break;
			case CATEGORY : category = edit;				break;
			case PRICE : price = Integer.parseInt(edit);	break;
			default: throw new NullPointerException("cannot change this");
		}
	}
	
	// update DATABASE
	public static void update(Column column, String value, String id)
	{
		DatabaseRef.update("Update "+ TABLE_NAME +" set " + column.key() + "='"+ value +"' where PostID='"+id+"'");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// any way to manipulate a post
	public enum Action
	{
		SELL, SOLD, DELETE, EDIT
	}
	
	public enum Status
	{
		AVAILABLE, DELETED, SOLD;
		
		public static Status getStatus(String str)
		{
			switch (str)
			{
				case "a" : case "A" : return AVAILABLE; 
				case "s" : case "S" : return SOLD; 
				case "d" : case "D" : return DELETED; 
			}
			throw new NullPointerException("post status not defined");
		}
	}
	
	public enum Column
	{
		DESC("Item_Description"), NAME("Item_Name"), PRICE("Price"), STATUS("Status"), CATEGORY("Category");
		
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
		return id + " " + ownerId + " " + title + " " + description + " " + price + " " + status + " " + datePosted + " " + category;
	}
	
	public int getId()			{return id;}
	public void setId(int id)	{this.id = id;}
	public Status getStatus()		{return status;}
	public String getOwnerId()	{return ownerId;}

	public String getTitle() {return title;}
	public String getDesc() {return description;}
	public String getCategory() {return category;}
	public int getPrice() {return price;}
	
}
