package com.iitk.autoTwit;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import twitterJ.twiterJ.Search;
import twitterJ.twiterJ.UniqueLineReader;
import twitterJ.twiterJ.UpdateStatus;
public class AutoTwitMain {
	
	public static String Message[]= new String[10000];
	public static int count=0;
	public static void main(String[] args) throws IOException, InterruptedException {
		
		UpdateStatus up= new UpdateStatus();
		
		//SearchTweets search=new SearchTweets();
		//search.Search();
		FileInputStream fstream = new FileInputStream("C:/Users/krishna/Desktop/sample.txt");
		  UniqueLineReader br1 = new UniqueLineReader(new InputStreamReader(fstream));
		  String strLine;
		  int k=0;
		  
		  // Read File Line By Line
		  while ((strLine = br1.readLine()) != null) {
		      // Print the content on the console
		  	
		  	
		  //	System.out.println("count="+strLine+"\n");
		              
		             Message[k++]=strLine;
						count++;

		      	
		          System.out.println("Count= "+count+" "+strLine);
		  }
		
		for(int i=1;i<51;i++)
		{
			up.update(Message[i]);
			Thread.sleep(1000*10);			
			
		}
		
		br1.close();
	}

}
