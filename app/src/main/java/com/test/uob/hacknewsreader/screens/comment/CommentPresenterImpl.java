package com.test.uob.hacknewsreader.screens.comment;

import android.content.Context;
import android.os.Bundle;

import com.test.uob.hacknewsreader.HackerNewsDataSource;
import com.test.uob.hacknewsreader.adapter.CommentAdapter;
import com.test.uob.hacknewsreader.base.BasePresenter;
import com.test.uob.hacknewsreader.base.BaseSchedulerProvider;
import com.test.uob.hacknewsreader.screens.comment.contracts.CommentPresenter;
import com.test.uob.hacknewsreader.screens.comment.contracts.CommentScene;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

public class CommentPresenterImpl extends BasePresenter<CommentScene> implements CommentPresenter {

    private final HackerNewsDataSource mHackerNewsDataSource;
    private Disposable disposable;
    @Inject
    public CommentPresenterImpl(BaseSchedulerProvider schedulerProvider, HackerNewsDataSource hackerNewsDataSource) {
        super(schedulerProvider);
        this.mHackerNewsDataSource = hackerNewsDataSource;
    }

    @Override
    protected CommentScene getScene() {
        return super.getScene();
    }

    @Override
    public void onSceneAdded(CommentScene scene, Bundle data, Context context) {
        super.onSceneAdded(scene, data, context);
        ArrayList<Integer> kids = data.getIntegerArrayList("kids");
        getScene().showProgressBar(true);
        disposable = mHackerNewsDataSource.getComments(kids)
                .subscribeOn(provider.io())
                .observeOn(provider.ui())
                .subscribe(commentList -> {
                    if (getScene() != null) {
                        CommentAdapter adapter = new CommentAdapter(context, commentList);
                        getScene().setCommentListAdapter(adapter);
                        getScene().showProgressBar(false);
                    }
                }, error -> {
                    getScene().showProgressBar(false);
                    error.printStackTrace();
                });
    }

    @Override
    public void onSceneRemoved() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
        super.onSceneRemoved();
    }
}
