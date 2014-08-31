package com.iitk.twitter.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GetDataFomDB {



	public String checkBusNOAndBusStopExistOrNOT(String busNo,String busStop) throws SQLException
	{
		DBConnect dbconnect= new DBConnect();
		
		PreparedStatement statement = dbconnect.getCon().prepareStatement("Select * from   busstoplist where BusNO = "+busNo +" And BusStationName LIKE "+"'%"+busStop+"%'" );
		/*sql structure to select instances from the table*/
		ResultSet result = statement.executeQuery();
		
		
		
		while(result.next()){ 
			
			System.out.println(result.getString(1) +"\t"+ result.getString(2)+ "\t" + result.getString(3)+ "\t"   );
			
			return result.getString(3);
		
		}
		return null;
		
	}
	
	
	public String  NextBusTime(String busNo, String busStop) throws SQLException
{
	DBConnect dbconnect= new DBConnect();
	String A="";
	String B="";
	
	String BusEndStation ="";
	
	String BusStartstation ="";
	
	String  finalMessage="";
	 Calendar cal = Calendar.getInstance();
     SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	cal.getTime();

	 
	PreparedStatement statement = dbconnect.getCon().prepareStatement("Select * from "+" " +busStop+ " "+ "where BusNO ="+busNo);
	/*sql structure to select instances from the table*/
	ResultSet result = statement.executeQuery();
	/*execution of the database query*/ 
	
int j=0,i=0;
	while(result.next()){
		System.out.println(result.getString(1) +"\t"+ result.getString(2)+ "\t" + result.getString(3)+ "\t"+ result.getString(4)+ "\t" + result.getString(5)+ "\t"+ result.getString(6)+ "\t"   );
		
		BusEndStation=result.getString(6);
		BusStartstation=result.getString(5);
		/*print the result with three attributes from the table 'products in my case' */
		
		if(java.sql.Time.valueOf(result.getString(3)).getHours()>java.sql.Time.valueOf(sdf.format(cal.getTime())).getHours()&& i<3){
		A+= java.sql.Time.valueOf(result.getString(3)).getHours()+"" +java.sql.Time.valueOf(result.getString(3)).getMinutes()+" ,";
		
		i++;
		}
		else if((java.sql.Time.valueOf(result.getString(3)).getHours()==java.sql.Time.valueOf(sdf.format(cal.getTime())).getHours() ) && java.sql.Time.valueOf(result.getString(3)).getMinutes()>=java.sql.Time.valueOf(sdf.format(cal.getTime()) ).getMinutes()&& i<3)
		{
			A+= java.sql.Time.valueOf(result.getString(3)).getHours()+"" +java.sql.Time.valueOf(result.getString(3)).getMinutes()+" ,";
		
			i++;
		}
		
		
		if(java.sql.Time.valueOf(result.getString(4)).getHours()>java.sql.Time.valueOf(sdf.format(cal.getTime())).getHours()&& j<3){
			B+= java.sql.Time.valueOf(result.getString(4)).getHours()+"" +java.sql.Time.valueOf(result.getString(4)).getMinutes()+",";
			
			j++;
			}
			else if((java.sql.Time.valueOf(result.getString(4)).getHours()==java.sql.Time.valueOf(sdf.format(cal.getTime())).getHours() ) && java.sql.Time.valueOf(result.getString(4)).getMinutes()>=java.sql.Time.valueOf(sdf.format(cal.getTime()) ).getMinutes()&& j<3)
			{
				B+= java.sql.Time.valueOf(result.getString(4)).getHours()+"" +java.sql.Time.valueOf(result.getString(4)).getMinutes()+" ,";
			
				j++;
			}
		
		
	}

	/*if (A!=null)
	{
		finalMessage =busNo+" "+busStop+" "+"To "+BusEndStation+" " +A +" ";
		
	}
	
	if (B!=null)
	{
		finalMessage +=busNo+" "+busStop+" "+" To "+BusStartstation+" "+B ;
		
	}
	*/
	System.out.println("UPdoming bus timing are = "+A);
	
	if(A.length()==0 && B.length()!=0)
	{
		finalMessage =busNo+" "+busStop+" To "+BusStartstation+" "+B+"  "  ;
	}
	
	if(B.length()==0 && A.length()!=0)
	{
		finalMessage =busNo+" From"+busStop+" To "+BusEndStation+" "+A ;
	}
	if(B.length()!=0 && A.length()!=0)
	{
		finalMessage =busNo+" From "+busStop+" "+"To "+BusEndStation+" " +A +" "+busStop+" To "+BusStartstation+" "+B ;
	}
	
	if(B.length()==0 && A.length()==0)
	{
		finalMessage ="No More Bus "+busNo+" From  "+busStop+" For Today " ;
	}
	
	
	//finalMessage =busNo+" "+busStop+" "+"To "+BusEndStation+" " +A +" "+busStop+" To "+BusStartstation+" "+B ;
	return finalMessage;

	
	 

}



}
