package javadb;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Login {

	private Statement statement;
	private ResultSet data;
	private Connection conn;
	
	public Login() {
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
}
