package com.test.uob.hacknewsreader.api;

import com.test.uob.hacknewsreader.modal.Comment;
import com.test.uob.hacknewsreader.modal.Story;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface HackerNewsReaderService {
    @GET("topstories.json")
    Flowable<List<Integer>> getTopStories();

    @Headers("Content-Type: application/json")
    @GET("item/{id}.json")
    Flowable<Story> getStory(@Path("id") Integer id);

    @Headers("Content-Type: application/json")
    @GET("item/{id}.json")
    Flowable<Comment> getComment(@Path("id") int id);
}
