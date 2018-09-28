package com.test.uob.hacknewsreader;

import com.test.uob.hacknewsreader.api.HackerNewsReaderService;
import com.test.uob.hacknewsreader.modal.Comment;
import com.test.uob.hacknewsreader.modal.Story;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Single;

public class HackerNewsDataSource {
    private final HackerNewsReaderService mHackerNewsReaderService;

    @Inject
    public HackerNewsDataSource(HackerNewsReaderService mHackerNewsReaderService) {
        this.mHackerNewsReaderService = mHackerNewsReaderService;
    }


    public Flowable<Story> getStory(int id) {
        return mHackerNewsReaderService.getStory(id);
    }

    public Flowable<Comment> getComment(int id) {
        return mHackerNewsReaderService.getComment(id).flatMap(comment -> {
            if (comment.getKids() != null && comment.getKids().size() > 0) {
                return this.getSubComment(comment.getKids().get(0));
            }
            Comment newComment = new Comment();
            return Flowable.just(newComment);

        }, (comment, comment2) -> {
            comment.setComment(comment2);
            return comment;
        });
    }

    public Flowable<Comment> getSubComment(int id) {
        return mHackerNewsReaderService.getComment(id);
    }

    public Single<List<Story>> getTopStoryItems() {
        return mHackerNewsReaderService.getTopStories()
                .flatMapIterable(ids -> ids).take(10)
                .flatMap(this::getStory)
                .toList();
    }

    public Single<List<Comment>> getComments(ArrayList<Integer> kids) {
        return Flowable.fromArray(kids).flatMapIterable(ids -> ids).take(10)
                .flatMap(this::getComment)
                .toList();


    }

}
