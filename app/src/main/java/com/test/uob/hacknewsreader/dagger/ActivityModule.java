package com.test.uob.hacknewsreader.dagger;

import com.test.uob.hacknewsreader.screens.comment.CommentPresenterImpl;
import com.test.uob.hacknewsreader.screens.comment.contracts.CommentPresenter;
import com.test.uob.hacknewsreader.screens.main.MainPresenterImpl;
import com.test.uob.hacknewsreader.screens.main.contracts.MainPresenter;

import dagger.Module;
import dagger.Provides;

@Module
@ActivityScope
public class ActivityModule {

    @ActivityScope
    @Provides
    public MainPresenter provideHomePresenter(MainPresenterImpl presenter) {
        return presenter;
    }

    @ActivityScope
    @Provides
    public CommentPresenter provideCommentPresenter(CommentPresenterImpl presenter) {
        return presenter;
    }

}
