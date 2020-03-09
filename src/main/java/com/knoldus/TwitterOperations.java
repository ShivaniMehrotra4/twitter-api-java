package com.knoldus;

import twitter4j.Status;
import twitter4j.TwitterException;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TwitterOperations {

    SetUpTwitterInstance setUpTwitterInstance;

    TwitterOperations()
    {
        setUpTwitterInstance  = new SetUpTwitterInstance();
    }
// Latest Post (Newer to Older) with limit
    CompletableFuture<List<String>> getLatestPosts(String searchItem,int limit) throws TwitterException {

        CompletableFuture<Stream<Status>> futureTweets = setUpTwitterInstance.getTweets(searchItem);
        return futureTweets.thenApply(tweetsStream -> tweetsStream.map(Status::getText)
                .limit(limit)
                .collect(Collectors.toList()));
    }

    //Older to Newer with limit and offset values
    CompletableFuture<List<String>> getOlderPosts(String searchItem,int limit) throws TwitterException {

        CompletableFuture<Stream<Status>> futureTweets = setUpTwitterInstance.getTweets(searchItem);
        return futureTweets.thenApply(tweetsStream -> tweetsStream.map(Status::getText)
                .sorted(Comparator.reverseOrder())
                .limit(limit)
                .collect(Collectors.toList()));
    }

    //Number of Retweets (Higher to Lower)
    CompletableFuture<List<Integer>> getNumberOfRetweets(String searchItem) throws TwitterException {

        CompletableFuture<Stream<Status>> futureTweets = setUpTwitterInstance.getTweets(searchItem);
        return futureTweets.thenApply(tweetsStream -> tweetsStream.map(Status::getRetweetCount)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList()));
    }

    //Number of Likes (Higher to Lower)
    CompletableFuture<List<Integer>> getNumberOfLikes(String searchItem) throws TwitterException {

        CompletableFuture<Stream<Status>> futureTweets = setUpTwitterInstance.getTweets(searchItem);
        return futureTweets.thenApply(tweetsStream -> tweetsStream.map(Status::getFavoriteCount)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList()));
    }

    //Get the List and number of tweets for an entered date.
    CompletableFuture<List<String>> getListAndNumberOfLikes(Date givenDate , String searchItem) throws TwitterException {

        CompletableFuture<Stream<Status>> futureTweets = setUpTwitterInstance.getTweets(searchItem);
        return futureTweets.thenApply(tweetsStream -> tweetsStream.filter(tweet -> tweet.getCreatedAt() == givenDate)
                .map(Status::getText)
                .collect(Collectors.toList()));
    }

    // Get the number of likes on a particular keyword in a time interval of 15 mins.
    /*CompletableFuture<List<Integer>> getNumberOfLikesTimeInterval(String searchItem) throws TwitterException {

        CompletableFuture<Stream<Status>> futureTweets = setUpTwitterInstance.getTweets(searchItem);

        return futureTweets.thenApply(tweetsStream -> {
                    return tweetsStream.map(tweet -> tweet.getFavoriteCount()).mapToInt(Integer::intValue).sum();
                }
        );
    }*/

}
