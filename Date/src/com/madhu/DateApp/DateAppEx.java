package com.madhu.DateApp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import com.madhu.JavaUtil.JavaUtil;

public class DateAppEx {

	public static void main(String[] args) throws ParseException {
		Connection connection= null;
		PreparedStatement preparedStatement = null;
		Scanner scanner= null;
		String uname=null;
		String dob=null;
		String dom=null;
		java.sql.Date dobDate=null;
		java.sql.Date domDate =null;
		
		try {
			connection=JavaUtil.getJdbcConnection();
			if(connection!=null) {
				scanner = new Scanner(System.in);
				
				System.out.println("enter user name:");
				uname= scanner.next();
				
				System.out.println("enter user DOB(dd-MM-yyyy):");
				dob= scanner.next();
				
				System.out.println("enter user DOM in (yyyy-MM-dd) format:");
				dom= scanner.next();
				
				
				String sqlInsertQueryString="insert into users(`name`,`dob`,`dom`) values(?,?,?)";
				preparedStatement=connection.prepareStatement(sqlInsertQueryString);
				
			}
			if(dob!=null) {
				SimpleDateFormat sFormat = new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date uDate=sFormat.parse(dob);
				long value = uDate.getTime();
				 dobDate = new java.sql.Date(value);
			}
			if(dom!=null) {
				domDate = java.sql.Date.valueOf(dom);
				}
			if(preparedStatement!=null) {
				preparedStatement.setString(1, uname);
				preparedStatement.setDate(2, dobDate);
				preparedStatement.setDate(3, domDate);
				
				int rowsAffected=preparedStatement.executeUpdate();
				System.out.println("no of rows inserted:"+rowsAffected);
			}
			
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				JavaUtil.closeResources(connection, preparedStatement, null);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			scanner.close();
		}

	}

}
