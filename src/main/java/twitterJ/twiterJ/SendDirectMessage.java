
package twitterJ.twiterJ;

import twitter4j.DirectMessage;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

/**
 * Example application that sends a message to specified Twitter-er from specified account.<br>
 *
 * @author Yusuke Yamamoto - yusuke at mac.com
 */
public final class SendDirectMessage {
    /**
     * Usage: java twitter4j.examples.directMessage.DirectMessage [recipient screen name] [message]
     *
     * @param args String[]
     */
    public static void main(String[] args) {
       /* if (args.length < 2) {
            System.out.println("Usage: java twitter4j.examples.directmessage.SendDirectMessage [recipient screen name] [message]");
            System.exit(-1);
        }*/
    	
    /*	args[0]={"geek_iitk"};
    	args[1]="Test Message";*/
    	
        Twitter twitter = new TwitterFactory().getInstance();
        try {
            DirectMessage message = twitter.sendDirectMessage("geek_iitk", "#paid media #YoMediaSoHonest ");
            System.out.println("Direct message successfully sent to " + message.getRecipientScreenName());
            System.exit(0);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to send a direct message: " + te.getMessage());
            System.exit(-1);
        }
    }
}