package com.iitk.twitter.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

public Connection con;

public Connection getCon() throws SQLException {
	
	try {
		Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	//load the jdbc driver class
	 con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/ncrhousing","root","");/* red colored part has to be as per your database*/

	
	return con;
}







}
