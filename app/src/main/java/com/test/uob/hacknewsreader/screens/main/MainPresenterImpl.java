package com.test.uob.hacknewsreader.screens.main;

import android.content.Context;
import android.os.Bundle;

import com.test.uob.hacknewsreader.HackerNewsDataSource;
import com.test.uob.hacknewsreader.adapter.TopStoriesAdapter;
import com.test.uob.hacknewsreader.base.BasePresenter;
import com.test.uob.hacknewsreader.base.BaseSchedulerProvider;
import com.test.uob.hacknewsreader.screens.main.contracts.MainPresenter;
import com.test.uob.hacknewsreader.screens.main.contracts.MainScene;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

public class MainPresenterImpl extends BasePresenter<MainScene> implements MainPresenter{

    private final HackerNewsDataSource mHackerNewsDataSource;
    private Disposable disposable;
    @Inject
    public MainPresenterImpl(BaseSchedulerProvider schedulerProvider, HackerNewsDataSource hackerNewsDataSource) {
        super(schedulerProvider);
        this.mHackerNewsDataSource = hackerNewsDataSource;
    }

    @Override
    protected MainScene getScene() {
        return super.getScene();
    }

    @Override
    public void onSceneAdded(MainScene scene, Bundle data, Context context) {
        super.onSceneAdded(scene, data, context);

        getScene().showProgressBar(true);
        disposable = mHackerNewsDataSource.getTopStoryItems()
                .subscribeOn(provider.io())
                .observeOn(provider.ui())
                .subscribe(storyList -> {
                    if (getScene() != null) {
                        TopStoriesAdapter adapter = new TopStoriesAdapter(context,storyList);
                        getScene().setStoryListAdapter(adapter);
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
