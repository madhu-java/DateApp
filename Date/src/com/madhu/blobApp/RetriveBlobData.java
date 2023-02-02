package com.madhu.blobApp;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

import com.madhu.JavaUtil.JavaUtil;
public class RetriveBlobData
{




	public static void main(String[] args) {

Connection connection= null;
PreparedStatement preparedStatement= null;
int id =0;
ResultSet resultSet= null;


try {
	connection= JavaUtil.getJdbcConnection();
	 String sqlSelectStatement= "select id,name,image from persons where id =?";	
	if(connection!=null)
		preparedStatement = connection.prepareStatement(sqlSelectStatement);
	if(preparedStatement!=null) {
		Scanner scanner = new Scanner(System.in);
		if(scanner!=null) {
		System.out.println("enter id:");
		id= scanner.nextInt();
		}
		//set the input values to query
		
		preparedStatement.setInt(1, id);
		
		//execute the query
		
		resultSet = preparedStatement.executeQuery();
		if(resultSet!=null) {
			if(resultSet.next()) {
			int pid = resultSet.getInt(1);
			String nameString = resultSet.getString(2);
			InputStream iStream = resultSet.getBinaryStream(3);
			System.out.println("id\tname\timage");
			
			File file = new File("copied.jpg");
			FileOutputStream fos = new FileOutputStream(file);
//			int i = iStream.read();
//			while(i!= -1) {
//				fos.write(i);
//				i= iStream.read();
//			}
			
			//using buffered data
			/*byte [] b = new byte[2048];
			while(iStream.read(b)>0) {
				fos.write(b);
			}*/
			//cpoying data from is to fos
			IOUtils.copy(iStream, fos);
			System.out.println(id+"\t"+nameString+"\t"+file.getAbsolutePath());
			
			}
		}
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
}
	}

}


