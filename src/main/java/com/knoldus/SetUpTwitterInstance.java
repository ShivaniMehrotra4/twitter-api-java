package com.knoldus;

import twitter4j.*;
import twitter4j.auth.AccessToken;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class SetUpTwitterInstance {

    Twitter twitter;
    SetUpTwitterInstance() {
        TwitterFactory factory = new TwitterFactory();
        twitter = factory.getInstance();
        twitter.setOAuthConsumer("e6uS4phTxImI68qlA6h4V3zwR",
                "M8b4Q3sudgU9mNZgJx1onUlqQYi5h5YCK1GVacjAc8yHDAohFc");
        twitter.setOAuthAccessToken(new AccessToken(
                "160922224-AKOoOasbqi3huqT7uyq4Og0Oqlucn8rKeD9IcUvU",
                "7HgIJUmjOX2AZThvVp7RPWsZwOrW1ffpvkEpjeBSQynnH"));
    }

    CompletableFuture<Stream<Status>> getTweets(String searchItem) throws TwitterException {

        Query query = new Query(searchItem);
        return CompletableFuture.supplyAsync(()-> {
            Stream<Status> tweets = null;
            try{
                tweets = twitter.search(query).getTweets().stream();
            } catch (TwitterException e) {
                e.printStackTrace();
            }
            return tweets;
        });
    }
}
