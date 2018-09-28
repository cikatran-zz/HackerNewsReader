package com.test.uob.hacknewsreader.dagger;

import com.test.uob.hacknewsreader.screens.comment.CommentActivity;
import com.test.uob.hacknewsreader.screens.main.MainActivity;

import dagger.Component;

@Component(dependencies = {AppComponent.class},
        modules = ActivityModule.class)
@ActivityScope
public interface ActivityComponent {
    void inject(MainActivity mainActivity);
    void inject(CommentActivity commentActivity);
}
