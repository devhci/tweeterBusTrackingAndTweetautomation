package com.iitk.TwitMainClass;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.iitk.twitter.analysis.ParsingIncomingTwit;

import twitter4j.*;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitterJ.twiterJ.UpdateStatus;


public final class BusServiceMain {
    /**
     * Main entry of this application.
     *
     * @param args
     * 
     * 
     */
		
	public static int  count=0;
	
    public static void main(String[] args) throws TwitterException {
    	
        TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
      
       
     
        StatusListener listener = new StatusListener() {

        	
        	
        	@Override
            public void onStatus(Status status) {
                //System.out.println("Count= " +count++);
        	
        	
        		    Calendar cal = Calendar.getInstance();
        		        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        	    	cal.getTime();
        		//System.out.println("withoutformat: " +cal.getTime());
            	
            	//System.out.println( sdf.format(cal.getTime()) );
        		
                //System.out.println("devender your status is updated ");
                System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
                ParsingIncomingTwit  parsingNewTwit= new  ParsingIncomingTwit();
              
                
                
                
                try {
                    Twitter twitter = new TwitterFactory().getInstance();
                    try {
                        // get request token.
                        // this will throw IllegalStateException if access token is already available
                        RequestToken requestToken = twitter.getOAuthRequestToken();
                        System.out.println("Got request token.");
                        System.out.println("Request token: " + requestToken.getToken());
                        System.out.println("Request token secret: " + requestToken.getTokenSecret());
                        AccessToken accessToken = null;

                        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                        while (null == accessToken) {
                            System.out.println("Open the following URL and grant access to your account:");
                            System.out.println(requestToken.getAuthorizationURL());
                            System.out.print("Enter the PIN(if available) and hit enter after you granted access.[PIN]:");
                            String pin = br.readLine();
                            try {
                                if (pin.length() > 0) {
                                    accessToken = twitter.getOAuthAccessToken(requestToken, pin);
                                } else {
                                    accessToken = twitter.getOAuthAccessToken(requestToken);
                                }
                            } catch (TwitterException te) {
                                if (401 == te.getStatusCode()) {
                                    System.out.println("Unable to get the access token.");
                                } else {
                                    te.printStackTrace();
                                }
                            }
                        }
                        System.out.println("Got access token.");
                        System.out.println("Access token: " + accessToken.getToken());
                        System.out.println("Access token secret: " + accessToken.getTokenSecret());
                    } catch (IllegalStateException ie) {
                        // access token is already available, or consumer key/secret is not set.
                        if (!twitter.getAuthorization().isEnabled()) {
                            System.out.println("OAuth consumer key/secret is not set.");
                            System.exit(-1);
                        }
                    }
                    //String tag="Julian Assange never said Modi as Incorruptible Bhakt Jan do you happen to know what truth is #ModiLeaks pic.twitter.com/6tYdPY1CYC";
                   // for(int i=0;i<1;i++) {
                   try {
					twitter.updateStatus(new StatusUpdate("@"+status.getUser().getScreenName()+" " + parsingNewTwit.FindbusNOAndStopName(status.getText())+" Current Time("+sdf.format(cal.getTime())+")").inReplyToStatusId(status.getId()));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                    System.out.println("Successfully updated the status to [" + status.getText() + "].");
                 //   }
                    //System.exit(0);
                } catch (TwitterException te) {
                    te.printStackTrace();
                    System.out.println("Failed to get timeline: " + te.getMessage());
                    System.exit(-1);
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                    System.out.println("Failed to read the system input.");
                    System.exit(-1);
                }

         
                
                
                
          /*      
                UpdateStatus up= new UpdateStatus();
                try {
					up.update("@"+status.getUser().getScreenName()+" " + parsingNewTwit.FindbusNOAndStopName(status.getText())+" "+sdf.format(cal.getTime()));
			
					// StatusUpdate a= new StatusUpdate("status").;
					
					
                } catch (IOException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
        	
        	}

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
                System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
            }

            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
              //  System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
            }

            @Override
            public void onScrubGeo(long userId, long upToStatusId) {
               // System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
            }

            @Override
            public void onStallWarning(StallWarning warning) {
              //  System.out.println("Got stall warning:" + warning);
            }

            @Override
            public void onException(Exception ex) {
                ex.printStackTrace();
            }
        };
        
        FilterQuery fq = new FilterQuery();
        String keywords[] = {"iitkbus"};

        fq.track(keywords);
        
        twitterStream.addListener(listener);
       twitterStream.filter(fq);
    }
}
