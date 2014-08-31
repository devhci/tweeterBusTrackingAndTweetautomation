package com.iitk.twitter.analysis;

import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;

import com.iitk.twitter.db.GetDataFomDB;

public class ParsingIncomingTwit {

	public String FinalTwitText;
	
	public   String  FindbusNOAndStopName(String incomingText) throws SQLException {
		GetDataFomDB getdateformdb= new GetDataFomDB();
		
		incomingText+=" "+incomingText;
		//incomingText+incomingText;
		
		
		String storeBusStopName; 
		 String arr[]=incomingText.split("[ ]+");
		
		String BusNo=arr[1];
		
	
		
		//String BusStop= arr[3];
		if(arr[2].equalsIgnoreCase("from")==false)
		{
			arr[3]=arr[2];
		}
	
		storeBusStopName=arr[3];
		String	BusStop=arr[3].substring(0,4);
	              
		
		/*if(!BusNo.equals("73")||!BusNo.equals("83")){
			
			
			return "this bus no doent exist ";
			
		}*/
		
	
	String teststop = null;
	try {
		teststop = getdateformdb.checkBusNOAndBusStopExistOrNOT(BusNo, BusStop);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		
		return  "Pls type<'Bus NO  from  'Bus Stop Name'> like<73 form kalyanpur>" ;
	}
	
	if(teststop==null)
	{
		
		return "Bus " + BusNo +" From "+storeBusStopName+ " doesn't exist.Pls type correct BusStop Name Like (73 From Kalyanpur) ";
		
	}
		
		System.out.println("BusStop="+BusStop);
		BusStop=getdateformdb.checkBusNOAndBusStopExistOrNOT(BusNo, BusStop);

		
		//getdateformdb.NextBusTime(BusNo,BusStop);
		
		
		return getdateformdb.NextBusTime(BusNo,BusStop);	
	}


}
