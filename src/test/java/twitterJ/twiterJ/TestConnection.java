package twitterJ.twiterJ;

import java.sql.*;

import com.iitk.twitter.db.GetDataFomDB;

public class TestConnection {
public static void main(String[] args) throws Exception
{
	/*//main class
	
	Class.forName("com.mysql.jdbc.Driver");
	//load the jdbc driver class
	Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/ncrhousing","root",""); red colored part has to be as per your database
	make connection with the database(db name ecommerce, user is root and password is not set in my case put yours in those places with password if you have set password for the database
	PreparedStatement statement = con.prepareStatement("Select * from kalyanpur ");
	sql structure to select instances from the table
	ResultSet result = statement.executeQuery();
	execution of the database query 
	
	while(result.next()){
		System.out.println(result.getString(1) +"\t"+ result.getString(2)+ "\t" + result.getString(3)+ "\t"+ result.getString(4)+ "\t" + result.getString(5)+ "\t"+ result.getString(6)+ "\t"   );
		print the result with three attributes from the table 'products in my case' 
		}*/

	
	GetDataFomDB getdateformdb= new GetDataFomDB();
	//getdateformdb.NextBusTime("73","gurudev");
	
	System.out.println("returndststion Name ="+getdateformdb.checkBusNOAndBusStopExistOrNOT("93","kal"));
}
}