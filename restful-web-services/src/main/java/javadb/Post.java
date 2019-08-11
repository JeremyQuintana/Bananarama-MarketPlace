package javadb;

import java.awt.image.BufferedImage;

public class Post {
	
	/*functionality not added*/
	public Post(String title, String description, double price, BufferedImage photo)
	{
		this(title, description, price);
	}
	
	public Post(String title, String description, double price)
	{
		this.title = title;
		this.description = description;
		this.price = price;
	}
	
	public Post(String identifier)
	{
		
	}
	
	private String description;
	private String title;
	private double price;
}
