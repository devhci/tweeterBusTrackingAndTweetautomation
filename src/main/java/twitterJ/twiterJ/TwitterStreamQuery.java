package twitterJ.twiterJ;



import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import twitter4j.*;
import twitterJ.twiterJ.UpdateStatus;


public final class TwitterStreamQuery {
    /**
     * Main entry of this application.
     *
     * @param args
     * 
     * 
     */
	
	public static int  count=0;
    public static void main(String[] args) throws TwitterException, IOException {
    	
        TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
        
        
        
  	  final File file = new File("C:/Users/krishna/Desktop/sample1.txt");
  
      final String Arr[]= new String[1500];
      

      
    	
        StatusListener listener = new StatusListener() {
        	int i=0 ;
        	
      PrintWriter  	printWriter = new  PrintWriter(file);
        	@Override
            public void onStatus(Status status) {
                //System.out.println("Count= " +count++);
        	
        		
        	
                
              Arr[i++]=  status.getText();
              
              if (i==200)
              {
            	
            	  
      		
            	  PrintWriter 	printWriter = null;
				try {
					printWriter = new  PrintWriter(file);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
      			
      			
      			for(int j=1;j<i;j++)
				{
					
      				
      				//if (Arr[j].)
      				if(Arr[j].length()<144){
      					
      				printWriter.append(Arr[j].trim());
					
				     printWriter.append('\n');
      				}
				}
			
      			i=0;
				
				printWriter.close();
      			
         
            	  
            	  
              }
        		
        	    	//cal.getTime();
        		//System.out.println("withoutformat: " +cal.getTime());
            	
            	//System.out.println( sdf.format(cal.getTime()) );
        		
                //System.out.println("devender your status is updated ");
                System.out.println("Count= "+i+ " @" + status.getUser().getScreenName() + " - " + status.getText());
                
                /*UpdateStatus up= new UpdateStatus();
                try {
					up.update("@"+status.getUser().getScreenName()+" " +"FromIITK->7:30PM/9:30/5:50, StationtoIITK->9:00PM/5:00PM"+" "+sdf.format(cal.getTime()));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
        	
        	}

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
               // System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
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
        String keywords[] = {"Arvindkejriwal,AAP"};

        fq.track(keywords);
        
        twitterStream.addListener(listener);
       twitterStream.filter(fq);
    }
}
