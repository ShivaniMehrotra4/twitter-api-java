package com.knoldus;


import twitter4j.TwitterException;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class TwitterHandler {

    public static void main(String[] args) throws TwitterException, ExecutionException, InterruptedException {
        System.out.println("hi I imported twitter.");

        //LocalDateTime localDateTime = LocalDateTime.of(1996,3,4,10,10,10);
        Date date = new Date(1996, Calendar.MARCH, 3);

        //SetUpTwitterInstance setUpTwitterInstance = new SetUpTwitterInstance();
        //System.out.println(setUpTwitterInstance.getTweets("#Pi").get());

        TwitterOperations twitterOperations = new TwitterOperations();
        //Latest Post (Newer to Older) with limit
        System.out.println(twitterOperations.getLatestPosts("#Pi", 10).get());
        System.out.println("...................................................................");
        //Older to Newer with limit and offset values
        System.out.println(twitterOperations.getOlderPosts("#Pi", 10, 3).get());
        System.out.println("...................................................................");
        //Number of Retweets (Higher to Lower)
        System.out.println(twitterOperations.getNumberOfRetweets("#Pi").get());
        System.out.println("...................................................................");
        //Number of Likes (Higher to Lower)
        System.out.println(twitterOperations.getNumberOfLikes().get());
        System.out.println("...................................................................");
        //Get the List and number of tweets for an entered date.
        System.out.println(twitterOperations.getListAndNumberOfLikes(date, "#Pi").get());
        System.out.println("...................................................................");
        // Get the number of likes on a particular keyword in a time interval of 15 mins.
        System.out.println(twitterOperations.getNumberOfLikesTimeInterval().get());
        System.out.println("...................................................................");

    }
}
