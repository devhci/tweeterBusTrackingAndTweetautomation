package twitterJ.twiterJ;





import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import twitter4j.*;


public final class ExtractTwitesForAnalysis {
    
	
	public static int  count=0;
    public static void main(String[] args) throws TwitterException, IOException {
    	
        TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
        
		        
        StatusListener listener = new StatusListener() {

        	
        	@Override
            public void onStatus(Status status) {
                System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
               
              
               
            	
       
            
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
        String keywords[] = {"AAP,Kejriwal"};

        fq.track(keywords);
        
        twitterStream.addListener(listener);
       twitterStream.filter(fq);
    }
}
