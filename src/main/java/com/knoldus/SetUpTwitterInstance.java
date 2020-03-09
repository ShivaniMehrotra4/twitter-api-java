package com.knoldus;

import twitter4j.*;
import twitter4j.auth.AccessToken;

import java.util.List;
import java.util.concurrent.CompletableFuture;


public class SetUpTwitterInstance {

    Twitter twitter;

    SetUpTwitterInstance() {
        TwitterFactory factory = new TwitterFactory();
        twitter = factory.getInstance();

        String consumer_key = System.getenv("CONSUMER_KEY");
        String consumer_secret = System.getenv("CONSUMER_SECRET");
        String token_key = System.getenv("TOKEN_KEY");
        String token_secret = System.getenv("TOKEN_SECRET");

        twitter.setOAuthConsumer(consumer_key, consumer_secret);
        twitter.setOAuthAccessToken(new AccessToken(token_key, token_secret));
    }

    CompletableFuture<List<Status>> getTweets(String searchItem) throws TwitterException {

        Query query = new Query(searchItem);


        return CompletableFuture.supplyAsync(() -> {
            List<Status> tweets = null;
            try {
                tweets = twitter.search(query).getTweets();
            } catch (TwitterException e) {
                e.printStackTrace();
            }
            return tweets;
        });
    }
}
  /*  AtomicReference<ResponseList<Status>> rs = null;
       CompletableFuture.supplyAsync(()-> {
           try {
               rs.set(twitter.getHomeTimeline());
               return rs;
           } catch (TwitterException e) {
               e.printStackTrace();
           }

           return rs;
       });
*/