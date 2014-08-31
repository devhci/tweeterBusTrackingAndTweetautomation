/*
 * Copyright 2007 Yusuke Yamamoto
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package twitterJ.twiterJ;

import twitter4j.*;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;


public class Search {
  
	
	public static int count=0;
	public  void Search() {
        /*if (args.length < 1) {
            System.out.println("java twitter4j.examples.search.SearchTweets [query]");
            System.exit(-1);
        }*/
    	 
        Twitter twitter = new TwitterFactory().getInstance();
        
        System.out.println("Enter who's Timeline you want to Pull  ");
        Scanner scan = new Scanner(System.in);
        String s = scan.next();
        System.out.println("Scaned value is= "+s);
        try {
            Query query = new Query(s);
            QueryResult result;
            do {
                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                for (Status tweet : tweets) {
                    System.out.println( "count = " +count++ + "  @" + tweet.getUser().getScreenName() + " - "+" ' " + tweet.getText()+" '");
                    
                    try(PrintWriter output = new PrintWriter(new FileWriter("C:/Users/krishna/Desktop/Search.txt",true))) 
            		{
            		    // + tweet.getUser().getScreenName() +
                    	String twit= tweet.getText().trim();
                    	if(twit.length()!=0)
                    	{
                    	output.printf("%s\r\n ",twit);
                    	count++;
                    	}
            		} 
            		catch (Exception e) {}
                
                
                
                
                }
                
            } while ((query = result.nextQuery()) != null);
      
            
            
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets:  " + te.getMessage());
            System.exit(-1);
        }
    }
}