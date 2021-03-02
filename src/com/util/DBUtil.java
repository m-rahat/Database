package com.util;

import java.sql.*;

public class DBUtil {

	public static final String URL = "jdbc:mysql://localhost:3306/testbed";
	public static final String USERNAME = "root";
	public static final String PASSWORD = "password";

	public Connection createConnection() {

		Connection connection = null;

		try {
			connection = DriverManager.getConnection(DBUtil.URL, DBUtil.USERNAME, DBUtil.PASSWORD);
		} // try
		catch (SQLException e) {
			e.printStackTrace();
		} // catch
		finally {
			return connection;
		} // finally

	}// createConnection

	public void closeAllConnection(Connection cn, PreparedStatement ps, Statement s, ResultSet rs) {
		try {
		if (s != null)
			s.close();
		if (rs != null)
			rs.close();
		if (ps != null)
			ps.close();
		if (cn != null)
			cn.close();
		}//try
		catch(SQLException e) {
			e.printStackTrace();
		}//catch
	}

}// DUButil
