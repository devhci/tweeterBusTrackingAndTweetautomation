package twitterJ.twiterJ;



import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.iitk.twitter.analysis.ParsingIncomingTwit;

import twitter4j.*;
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
              
                
                UpdateStatus up= new UpdateStatus();
                try {
				
                	up.update("@"+status.getUser().getScreenName()+" " + parsingNewTwit.FindbusNOAndStopName(status.getText())+" "+sdf.format(cal.getTime()));
			
					// StatusUpdate a= new StatusUpdate("status").;
					
					
                } catch (IOException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	
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
