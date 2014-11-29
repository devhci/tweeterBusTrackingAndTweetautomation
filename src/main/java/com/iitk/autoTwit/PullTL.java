

package com.iitk.autoTwit;

import twitter4j.*;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

/**
 * @author Yusuke Yamamoto - yusuke at mac.com
 * @since Twitter4J 2.1.7
 */
public class PullTL {
    /**publi
     * arch.SearchTweets [query]
     *
     * @param args
     */
	
	public static int count=0;
	public static void main(String[] args) {
        /*if (args.length < 1) {
            System.out.println("java twitter4j.examples.search.SearchTweets [query]");
            System.exit(-1);
        }*/
    	 
        Twitter twitter = new TwitterFactory().getInstance();
        
        System.out.println("Enter who's Timeline you want to Search ");
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
                    	if(twit.length()<=500)
                    	{
                    	output.printf("%s\r\n ",twit);
                    	count++;
                    	}
            		} 
            		catch (Exception e) {}
                
                
                
                
                }
                
            } while ((query = result.nextQuery()) != null);
            
            System.exit(0);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets:  " + te.getMessage());
            System.exit(-1);
        }
    }
}
