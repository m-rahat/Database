import java.sql.*;
import java.io.*;

public class Database {

	public static final String URL = "jdbc:mysql://localhost:3306/testbed";
	public static final String USERNAME = "root";
	public static final String PASSWORD = "password";

	public void printUsers() {

		final String QUERY = "select * from user";

		System.out.println("********************");

		try {
			Connection connection = DriverManager.getConnection(Database.URL, Database.USERNAME, Database.PASSWORD);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(QUERY);

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String email = resultSet.getString("email");
				String country = resultSet.getString("country");
				String password = resultSet.getString("password");

				System.out.println(id + ", " + name + ", " + email + ", " + country + ", " + password);
			} // while

			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("********************");
	}// printUsers

	public void addUser(int id, String name, String email, String country, String password) {

		final String QUERY = "insert into user(id, name, email, country, password) Values" + "(?,?,?,?,?);";

		try {
			Connection connection = DriverManager.getConnection(Database.URL, Database.USERNAME, Database.PASSWORD);
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, name);
			preparedStatement.setString(3, email);
			preparedStatement.setString(4, country);
			preparedStatement.setString(5, password);

//			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();

			connection.close();

		} catch (SQLException e) {
//			e.printStackTrace();
			System.out.println("[Error]: " + e.getMessage());
		}

	}// addUser

	public void deleteUser(int id) {
		
		final String QUERY = "delete from user where id = ?";
		
		try {
			Connection connection = DriverManager.getConnection(Database.URL, Database.USERNAME, Database.PASSWORD);
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1, 1);
			
			preparedStatement.executeUpdate();
			
			connection.close();
		} catch (SQLException e) {
			System.out.println("[Error]: " + e.getMessage());
		}
		
	}//deleteUser
	
	public void downloadFile(int id) {
		
		String QUERY = "select file from file where id=?;";
		try {
			Connection connection = DriverManager.getConnection(Database.URL, Database.USERNAME, Database.PASSWORD);
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			File file = new File("ctci.pdf");
			try {
				FileOutputStream output = new FileOutputStream(file);
				System.out.println("Writing to file " + file.getAbsolutePath());
				
				while(resultSet.next()) {
					InputStream input = resultSet.getBinaryStream("file");
					byte[] buffer = new byte[1024];
					try {
						while (input.read(buffer) > 0) {
							output.write(buffer);
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}//while
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}//downloadFile
	
	public void addFile(int id, String fileName) throws FileNotFoundException, SQLException{
		
		File file = new File(fileName);
		
		final String QUERY = "insert into file(id, file) values (?,?);";
		
		Connection connection;
		connection = DriverManager.getConnection(Database.URL, Database.USERNAME, Database.PASSWORD);
		PreparedStatement ps = connection.prepareStatement(QUERY);
		ps.setInt(1, id);
		ps.setBinaryStream(2, new FileInputStream(file));
		
		ps.executeUpdate();
		connection.close();
		
	}//addFile
	
	
	public static void main(String[] args) {

		Database db = new Database();
//		db.printUsers();
		db.downloadFile(1);

	}// main

}// Operations
