package twitterJ.twiterJ;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import twitterJ.twiterJ.UpdateStatus;
public class A {
	
	public static String Message[]= new String[10000];
	public static int count=0;
	public static void main(String[] args) throws IOException, InterruptedException {
		
		UpdateStatus up= new UpdateStatus();
		
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
		
		for(int i=7;i<11;i++)
		{
			up.update(Message[i]);
			Thread.sleep(1000*10);			
			
		}
		
		
	}

}
