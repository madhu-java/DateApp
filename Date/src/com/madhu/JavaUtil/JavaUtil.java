package com.madhu.JavaUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JavaUtil {

	//1. loading and registering the driver
	static {
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	//2.establish the connection
	public static Connection getJdbcConnection() throws SQLException, IOException {
		FileInputStream fis = new FileInputStream("C:\\Users\\madha\\git\\DateApp\\Date\\src\\com\\madhu\\properties\\application.properties");
		Properties properties = new Properties();
		properties.load(fis);
		
		Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("user"), properties.getProperty("password"));
		System.out.println("connection established");
		return connection;
	}
	
	public static void closeResources(Connection connection, Statement statement,ResultSet resultset) throws SQLException {
		if(connection!=null) {
			connection.close();
		}
		if(statement!=null) {
			statement.close();
		}
		if(resultset!=null) {
			resultset.close();
		}
	}
	
	
}
