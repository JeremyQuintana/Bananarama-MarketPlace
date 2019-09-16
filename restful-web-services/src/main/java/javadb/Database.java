package javadb;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.Scanner;
import java.sql.Date;

public class Database {

	private static Statement statement;
	private static ResultSet data;
	private static ResultSet rowcount;
	private static Connection conn;
	private static String DELIMITER = "#$#";
	String id;
	String password;
	String cate;
	int p_id;
	int new_price;
	String new_title;
	String new_despt;
	String cate_descpt;
	String search_word;
	
	public Database() {
		try {				
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://35.189.1.213:3306/sept";
			String username = "root";
			String password = "bananasept";
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
			statement = conn.createStatement();
			//System.out.println("Connected");
			} 
		
		catch (Exception e) {
			System.out.println(e);
		}
	}

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
	
//main place display of for sale items, checks for current for sale items in db
	public static void check_for_salewrite() throws Exception{
		try {
			
			data = statement.executeQuery("select * from sale where Status= 'A'");
			FileWriter fileWriter = new FileWriter("DATABD.txt");
			
			PrintWriter writer = new PrintWriter(fileWriter);
				while(data.next()) {
				writer.print(data.getString(1)+DELIMITER);
				writer.print(data.getString(3)+DELIMITER);
				writer.print (data.getString(4)+DELIMITER);
				writer.print(data.getString(2)+DELIMITER);
				writer.print(data.getString(5));
				writer.println();
			}
		writer.close();
		data.close();
		conn.close();
		
		}
		
		catch (Exception e) {
			e.printStackTrace();
			
		}
	}

	public static String[][] check_for_sale() throws Exception{
		List<List<String>> list = new ArrayList<List<String>>();
		BufferedReader br = new BufferedReader(new FileReader("DATABD.txt"));
		String line= "";
		while((line=br.readLine()) != null)  {
			String[] dataarray = line.split(DELIMITER);
			ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(dataarray));
			list.add(arrayList);
		}
		//System.out.println(Arrays.deepToString(list.toArray()));
		br.close();
		int rows = list.size();
		int cols = list.get(0).size();
		String[][] array2D = new String[rows][cols];
		for(int row = 0; row < rows; row++) {
			for(int col = 0; col < cols; col++) {
				array2D[row][col] = list.get(row).get(col);
			}
		}
		//System.out.println(Arrays.deepToString(array2D));
		return array2D;
	}
	
	
//Search category only
	public static void search_by_categorywrite(String cate_descpt) throws Exception{
		try {
			
			data = statement.executeQuery("select * from sale where Category='"+cate_descpt+"'");
			FileWriter fileWriter = new FileWriter("searchbycate.txt");
			
			PrintWriter writer = new PrintWriter(fileWriter);
				while(data.next()) {
					writer.print(data.getString(1)+DELIMITER);
					writer.print(data.getString(3)+DELIMITER);
					writer.print (data.getString(4)+DELIMITER);
					writer.print(data.getString(2)+DELIMITER);
					writer.print(data.getString(5));
					writer.println();
				}
			writer.close();
		}
		
		catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	
	public static String[][] search_by_category() throws Exception{
		List<List<String>> list = new ArrayList<List<String>>();
		BufferedReader br = new BufferedReader(new FileReader("searchbycate.txt"));
		String line= "";
		while((line=br.readLine()) != null)  {
			String[] dataarray = line.split(DELIMITER);
			ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(dataarray));
			list.add(arrayList);
		}
		//System.out.println(Arrays.deepToString(list.toArray()));
		br.close();
		int rows = list.size();
		int cols = list.get(0).size();
		String[][] array2D = new String[rows][cols];
		for(int row = 0; row < rows; row++) {
			for(int col = 0; col < cols; col++) {
				array2D[row][col] = list.get(row).get(col);
			}
		}
		System.out.println(Arrays.deepToString(array2D));
		return array2D;
	}
	
	
	
//Search category & description
	public static void search_by_category_descptwrite(String cate_descpt, String search_word) throws Exception{
		try {
			
			data = statement.executeQuery("select * from sale where Category='"+cate_descpt+"'AND Status= 'A' AND Item_Description like'%"+ search_word +"%'");
			FileWriter fileWriter = new FileWriter("searchbycatedescpt.txt");
			
			PrintWriter writer = new PrintWriter(fileWriter);
				while(data.next()) {
					writer.print(data.getString(1)+DELIMITER);
					writer.print(data.getString(3)+DELIMITER);
					writer.print (data.getString(4)+DELIMITER);
					writer.print(data.getString(2)+DELIMITER);
					writer.print(data.getString(5));
					writer.println();
				}
			writer.close();
		}
		
		catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	
	
	public static String[][] search_by_category_descpt() throws Exception{
		List<List<String>> list = new ArrayList<List<String>>();
		BufferedReader br = new BufferedReader(new FileReader("searchbycatedescpt.txt"));
		String line= "";
		while((line=br.readLine()) != null)  {
			String[] dataarray = line.split(DELIMITER);
			ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(dataarray));
			list.add(arrayList);
		}
		//System.out.println(Arrays.deepToString(list.toArray()));
		br.close();
		int rows = list.size();
		int cols = list.get(0).size();
		String[][] array2D = new String[rows][cols];
		for(int row = 0; row < rows; row++) {
			for(int col = 0; col < cols; col++) {
				array2D[row][col] = list.get(row).get(col);
			}
		}
		System.out.println(Arrays.deepToString(array2D));
		return array2D;
	}	
	
	
	
	
	

//Show history of sale items for user
	public static void sale_historywrite(String id) throws Exception{
		try {
			
			data = statement.executeQuery("select * from sale where ID='" + id + "'");
			FileWriter fileWriter = new FileWriter("salehistory.txt");
			
			
			PrintWriter writer = new PrintWriter(fileWriter);
				while(data.next()) {
					writer.print(data.getString(1)+DELIMITER);
					writer.print(data.getString(3)+DELIMITER);
					writer.print (data.getString(4)+DELIMITER);
					writer.print(data.getString(2)+DELIMITER);
					writer.print(data.getString(5));
					writer.println();
				}
			writer.close();
		}
		
		catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	
	
	public static String[][] sale_history() throws Exception{
		List<List<String>> list = new ArrayList<List<String>>();
		BufferedReader br = new BufferedReader(new FileReader("salehistory.txt"));
		String line= "";
		while((line=br.readLine()) != null)  {
			String[] dataarray = line.split(DELIMITER);
			ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(dataarray));
			list.add(arrayList);
		}
		//System.out.println(Arrays.deepToString(list.toArray()));
		br.close();
		int rows = list.size();
		int cols = list.get(0).size();
		String[][] array2D = new String[rows][cols];
		for(int row = 0; row < rows; row++) {
			for(int col = 0; col < cols; col++) {
				array2D[row][col] = list.get(row).get(col);
			}
		}
		System.out.println(Arrays.deepToString(array2D));
		return array2D;
	}	
	
	
	
//	//main place display of for sale items, checks for current for sale items in db
//		public static void check_for_salewrite() throws Exception{
//			try {
//				
//				data = statement.executeQuery("select * from sale where Status= 'A'");
//				FileWriter fileWriter = new FileWriter("DATABD.txt");
//				
//				PrintWriter writer = new PrintWriter(fileWriter);
//					while(data.next()) {
//					writer.print(data.getString(1)+",");
//					writer.print(data.getString(3)+",");
//					writer.print (data.getString(4)+",");
//					writer.print(data.getString(2)+",");
//					writer.print(data.getString(5));
//					writer.println();
//				}
//			writer.close();
//			data.close();
//			conn.close();
//			
//			}
//			
//			catch (Exception e) {
//				e.printStackTrace();
//				
//			}
//		}
//
//		public static String[][] check_for_sale() throws Exception{
//			List<List<String>> list = new ArrayList<List<String>>();
//			BufferedReader br = new BufferedReader(new FileReader("DATABD.txt"));
//			String line= "";
//			while((line=br.readLine()) != null)  {
//				String[] dataarray = line.split(",");
//				ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(dataarray));
//				list.add(arrayList);
//			}
//			//System.out.println(Arrays.deepToString(list.toArray()));
//			br.close();
//			int rows = list.size();
//			int cols = list.get(0).size();
//			String[][] array2D = new String[rows][cols];
//			for(int row = 0; row < rows; row++) {
//				for(int col = 0; col < cols; col++) {
//					array2D[row][col] = list.get(row).get(col);
//				}
//			}
//			//System.out.println(Arrays.deepToString(array2D));
//			return array2D;
//		}
//		
//		
//	//Search category only
//		public static void search_by_categorywrite(String cate_descpt) throws Exception{
//			try {
//				
//				data = statement.executeQuery("select * from sale where Category='"+cate_descpt+"'");
//				FileWriter fileWriter = new FileWriter("searchbycate.txt");
//				
//				PrintWriter writer = new PrintWriter(fileWriter);
//					while(data.next()) {
//						writer.print(data.getString(1)+",");
//						writer.print(data.getString(3)+",");
//						writer.print (data.getString(4)+",");
//						writer.print(data.getString(2)+",");
//						writer.print(data.getString(5));
//						writer.println();
//					}
//				writer.close();
//			}
//			
//			catch (Exception e) {
//				e.printStackTrace();
//				
//			}
//		}
//		
//		public static String[][] search_by_category() throws Exception{
//			List<List<String>> list = new ArrayList<List<String>>();
//			BufferedReader br = new BufferedReader(new FileReader("searchbycate.txt"));
//			String line= "";
//			while((line=br.readLine()) != null)  {
//				String[] dataarray = line.split(",");
//				ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(dataarray));
//				list.add(arrayList);
//			}
//			//System.out.println(Arrays.deepToString(list.toArray()));
//			br.close();
//			int rows = list.size();
//			int cols = list.get(0).size();
//			String[][] array2D = new String[rows][cols];
//			for(int row = 0; row < rows; row++) {
//				for(int col = 0; col < cols; col++) {
//					array2D[row][col] = list.get(row).get(col);
//				}
//			}
//			System.out.println(Arrays.deepToString(array2D));
//			return array2D;
//		}
//		
//		
//		
//	//Search category & description
//		public static void search_by_category_descptwrite(String cate_descpt, String search_word) throws Exception{
//			try {
//				
//				data = statement.executeQuery("select * from sale where Category='"+cate_descpt+"'AND Status= 'A' AND Item_Description like'%"+ search_word +"%'");
//				FileWriter fileWriter = new FileWriter("searchbycatedescpt.txt");
//				
//				PrintWriter writer = new PrintWriter(fileWriter);
//					while(data.next()) {
//						writer.print(data.getString(1)+",");
//						writer.print(data.getString(3)+",");
//						writer.print (data.getString(4)+",");
//						writer.print(data.getString(2)+",");
//						writer.print(data.getString(5));
//						writer.println();
//					}
//				writer.close();
//			}
//			
//			catch (Exception e) {
//				e.printStackTrace();
//				
//			}
//		}
//		
//		
//		public static String[][] search_by_category_descpt() throws Exception{
//			List<List<String>> list = new ArrayList<List<String>>();
//			BufferedReader br = new BufferedReader(new FileReader("searchbycatedescpt.txt"));
//			String line= "";
//			while((line=br.readLine()) != null)  {
//				String[] dataarray = line.split(",");
//				ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(dataarray));
//				list.add(arrayList);
//			}
//			//System.out.println(Arrays.deepToString(list.toArray()));
//			br.close();
//			int rows = list.size();
//			int cols = list.get(0).size();
//			String[][] array2D = new String[rows][cols];
//			for(int row = 0; row < rows; row++) {
//				for(int col = 0; col < cols; col++) {
//					array2D[row][col] = list.get(row).get(col);
//				}
//			}
//			System.out.println(Arrays.deepToString(array2D));
//			return array2D;
//		}	
//		
//		
//		
//		
//		
//
//	//Show history of sale items for user
//		public static void sale_historywrite(String id) throws Exception{
//			try {
//				
//				data = statement.executeQuery("select * from sale where ID='" + id + "'");
//				FileWriter fileWriter = new FileWriter("salehistory.txt");
//				
//				PrintWriter writer = new PrintWriter(fileWriter);
//					while(data.next()) {
//						writer.print(data.getString(1)+",");
//						writer.print(data.getString(3)+",");
//						writer.print (data.getString(4)+",");
//						writer.print(data.getString(2)+",");
//						writer.print(data.getString(5));
//						writer.println();
//					}
//				writer.close();
//			}
//			
//			catch (Exception e) {
//				e.printStackTrace();
//				
//			}
//		}
//		
//		
//		public static String[][] sale_history() throws Exception{
//			List<List<String>> list = new ArrayList<List<String>>();
//			BufferedReader br = new BufferedReader(new FileReader("salehistory.txt"));
//			String line= "";
//			while((line=br.readLine()) != null)  {
//				String[] dataarray = line.split(",");
//				ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(dataarray));
//				list.add(arrayList);
//			}
//			//System.out.println(Arrays.deepToString(list.toArray()));
//			br.close();
//			int rows = list.size();
//			int cols = list.get(0).size();
//			String[][] array2D = new String[rows][cols];
//			for(int row = 0; row < rows; row++) {
//				for(int col = 0; col < cols; col++) {
//					array2D[row][col] = list.get(row).get(col);
//				}
//			}
//			System.out.println(Arrays.deepToString(array2D));
//			return array2D;
//		}	
	
	
	
	
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