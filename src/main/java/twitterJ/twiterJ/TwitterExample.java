package twitterJ.twiterJ;

import java.util.List;
import java.util.Scanner;

import org.scribe.builder.*;
import org.scribe.builder.api.*;
import org.scribe.model.*;
import org.scribe.oauth.*;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;

public class TwitterExample
{
	  public static int  count=0;
  private static final String PROTECTED_RESOURCE_URL = "https://api.twitter.com/1.0/account/verify_credentials.json";
  
  public static void main(String[] args)
  {
    // If you choose to use a callback, "oauth_verifier" will be the return value by Twitter (request param)
    OAuthService service = new ServiceBuilder()
                                .provider(TwitterApi.SSL.class)
                                .apiKey("krszf2oniWOCBgw0PJ8g")
                                .apiSecret("bhVBqx5wj7w1eahEV9LY1uILvHRXo4Z08fw6jWl4Vv0")
                                .build();
    Scanner in = new Scanner(System.in);

    System.out.println("=== Twitter's OAuth Workflow ===");
    System.out.println();

    // Obtain the Request Token
    System.out.println("Fetching the Request Token...");
    Token requestToken = service.getRequestToken();
    System.out.println("Got the Request Token!");
    System.out.println();

    System.out.println("Now go and authorize Scribe here:");
    System.out.println(service.getAuthorizationUrl(requestToken));
    System.out.println("And paste the verifier here");
    System.out.print(">>");
    Verifier verifier = new Verifier(in.nextLine());
    System.out.println();

    // Trade the Request Token and Verfier for the Access Token
    System.out.println("Trading the Request Token for an Access Token...");
    Token accessToken = service.getAccessToken(requestToken, verifier);
    System.out.println("Got the Access Token!");
    System.out.println("(if your curious it looks like this: " + accessToken + " )");
    System.out.println();

    // Now let's go and ask for a protected resource!
    System.out.println("Now we're going to access a protected resource...");
    OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
    request.addBodyParameter("status", "this is sparta! *");
    service.signRequest(accessToken, request);
    Response response = request.send();
    System.out.println("Got it! Lets see what we found...");
    System.out.println();
    System.out.println(response.getBody());
    
    try {
        // gets Twitter instance with default credentials
        Twitter twitter = new TwitterFactory().getInstance();
        User user = twitter.verifyCredentials();
        List<Status> statuses = twitter.getHomeTimeline();
        System.out.println("Showing @" + user.getScreenName() + "'s home timeline.");
        for (Status status : statuses) {
            System.out.println("count = "+count++ +" @" + status.getUser().getScreenName() + " - " + status.getText());
        }
    } catch (TwitterException te) {
        te.printStackTrace();
        System.out.println("Failed to get timeline: " + te.getMessage());
        System.exit(-1);
    }
}

    

}