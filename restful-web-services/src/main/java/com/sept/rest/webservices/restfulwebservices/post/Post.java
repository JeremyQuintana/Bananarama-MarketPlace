package com.sept.rest.webservices.restfulwebservices.post;

import java.awt.image.BufferedImage;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sept.rest.webservices.restfulwebservices.jwt.JwtTokenUtil;

//import com.sept.rest.webservices.restfulwebservices.todo.Post.Column;

import javadb.DatabaseRef;

@Entity
public class Post implements Comparable<Post> {
	
	// variables need to be above constructor for json
	/*to change*/
	private String description;
	private String title;
	private String price;
	private String category;
	private String photo;
	private String ownerId;
	private Date datePosted = new Date(new java.util.Date().getTime());
	
	@Enumerated(EnumType.STRING)
	private Status status = Status.AVAILABLE;
	
	// a bug prevents the above values being stored in a constructor, 
	// used to work before but now doesn't
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	// by giving another name, can put daatabase in "test mode"
	public static String TABLE_NAME = "sale";
	
	// annoyingly jpa 2.0 needs this
	public Post(){}
	
// cannot create multiple constructors
	
	@JsonIgnore
	public Post(Long id, String owner, String title, String description, String price, Date date, String category)
	{
		this(id, owner, title, description, price, category);
		this.datePosted = date;
	}
	
	@JsonIgnore
	public Post(Long id)
	{
		
		this.id = id;
	}
	
	@JsonIgnore
	public Post(Long id, String owner, String title, String description, String price, String category, String photo)
	{
		this(id, owner, title, description, price, category);
		this.id = id;
	}
	
	@JsonIgnore
	public Post(Long id, String owner, String title, String description, String price, String category)
	{
		this(owner, title, description, price, category);
		this.id = id;
	}
	
	@JsonIgnore
	public Post(String owner, String title, String description, String price, String category)
	{
		this.ownerId = owner;
		this.title = title;
		this.description = description;
		this.price = price;
		this.category = category;
		this.status = Status.AVAILABLE;
		this.datePosted = new Date(new java.util.Date().getTime()); ;
	}
	
	public void setOwner(String ownerId) {
		this.ownerId = ownerId;
	}
	
	// post creation from a (raw) database
	@JsonIgnore
	public Post(ResultSet post)
	{
		try {
			id = post.getLong(1);
			ownerId = post.getString(2);
			title = post.getString(3);
			description = post.getString(4);
			price = post.getString(5);
			status = Status.getStatus(post.getString(6));
			datePosted = post.getDate(7);
			category = post.getString(8);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	//edit post column in marketplace
	public void edit(Column column, String edit) throws SQLException {
		update(column, edit);
		update(column, edit, "" + id);

	}

	//Delete item from Marketplace
	public void delete() throws SQLException {
		update(Column.STATUS, "D");
		update(Column.STATUS, "D", "" + id);
	}

	//Marked Item as sold on Marketplace
	public void sold() throws SQLException {
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
			case PRICE : price = edit;						break;
			default: throw new NullPointerException("cannot change this");
		}
	}
	
	// update DATABASE
	public static void update(Column column, String value, String id) throws SQLException
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
		AVAILABLE, DELETED, SOLD, TEST;
		
		public static Status getStatus(String str)
		{
			switch (str)
			{
				case "a" : case "A" : return AVAILABLE; 
				case "s" : case "S" : return SOLD; 
				case "d" : case "D" : return DELETED; 
				case "t" : case "T" : return TEST;
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
	
	@Override
	public int compareTo(Post o) 
	{
		double price1 = price != null ? Double.valueOf(price) : -1;
		double price2 = o.price != null ? Double.valueOf(o.price) : -1;
		return Double.compare(price1, price2);
	}
	
	
	@Override
	public int hashCode() 
	{
		return 31 + ((id == null) ? 0 : id.hashCode());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) 					return true;
		if (obj == null) 					return false;
		if (getClass() != obj.getClass())	return false;
		Post other = (Post) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Long getId()						{return id;}
	public void setId(Long id)				{this.id = id;}
	public Status getStatus()				{return status;}
	public void setStatus(Status status) 	{this.status = status;}
	public String getOwnerId()				{return ownerId;}

	public String getTitle() 				{return title;}
	public String getDescription() 			{return description;}
	public String getCategory() 			{return category;}
	public String getPrice() 				{return price;}
	public String getPhoto() 				{return photo;}
	public void setPhoto(String photo)	{this.photo = photo;}
	
}


