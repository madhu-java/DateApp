package com.madhu.DateApp;

import java.io.CharConversionException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import com.madhu.JavaUtil.JavaUtil;

public class DateRetrieval {

	public static void main(String[] args) {

Connection connection =null;
PreparedStatement preparedStatement = null;
ResultSet resultSet = null;

try {
	connection = JavaUtil.getJdbcConnection();
	String sqlSelectQuery = "Select id,name,dob,dom from users where id =?";
	if(connection!=null) 
		 preparedStatement = connection.prepareStatement(sqlSelectQuery);
		if(preparedStatement!=null) {
			preparedStatement.setInt(1, 1);
			resultSet  = preparedStatement.executeQuery();
			
		}
		if(resultSet!=null) {
			if(resultSet.next()) {
				int id = resultSet.getInt(1);
				String nameString = resultSet.getString(2);
				java.sql.Date dobDate =resultSet.getDate(3);
				java.sql.Date domDate = resultSet.getDate(4);
				System.out.println(id+"\t"+nameString+"\t"+dobDate+"\t"+domDate);
				//formating into the client required date format
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				String dob=sdf.format(dobDate);
				String dom = sdf.format(domDate);
				System.out.println("after formating the string:");
				System.out.println(id+"\t"+nameString+"\t"+dob+"\t"+dom);
				
				
				
			}else {
				System.out.println("Record not availabe:");
			}
		}
	
} catch (SQLException | IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}finally {
	try {
		JavaUtil.closeResources(connection, preparedStatement, resultSet);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


	}

}
