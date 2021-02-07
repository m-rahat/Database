import java.util.*;
import java.sql.*;

public class test {

	public static void main(String[] args) {

		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testbed", "root", "password");
			Statement statement = con.createStatement();
			
			ResultSet rs = statement.executeQuery("SELECT * from user");

			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String country = rs.getString("country");
				String password = rs.getString("password");
				
				System.out.println(id + ", " + name + ", " + email + ", " + country + ", " + ", " + password);
			}//while

			int b = statement.executeUpdate("DELETE from user where id = \"3\"");
			
			rs = statement.executeQuery("SELECT * from user");
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String country = rs.getString("country");
				String password = rs.getString("password");
				
				System.out.println(id + ", " + name + ", " + email + ", " + country + ", " + ", " + password);
			}//while
		}//try
		catch (SQLException e) {
			e.printStackTrace();
		}//catch

	}// main

}// test
