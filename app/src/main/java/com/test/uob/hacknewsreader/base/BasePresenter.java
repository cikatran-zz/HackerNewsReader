package com.test.uob.hacknewsreader.base;

import android.content.Context;
import android.os.Bundle;

import com.test.uob.hacknewsreader.mvp.Presenter;
import com.test.uob.hacknewsreader.mvp.Scene;

public class BasePresenter<S extends Scene> implements Presenter<S> {

    protected BaseSchedulerProvider provider;

    public BasePresenter(BaseSchedulerProvider provider) {
        this.provider = provider;
    }

    protected S getScene() {
        return scene;
    }

    private S scene;

    @Override
    public void onSceneAdded(S scene, Bundle data, Context context) {
        this.scene = scene;
    }

    @Override
    public void onSceneRemoved() {
        this.scene = null;
    }
}
