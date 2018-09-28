package com.test.uob.hacknewsreader.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.test.uob.hacknewsreader.MainApplication;
import com.test.uob.hacknewsreader.dagger.ActivityComponent;
import com.test.uob.hacknewsreader.dagger.ActivityModule;
import com.test.uob.hacknewsreader.dagger.AppComponent;
import com.test.uob.hacknewsreader.dagger.DaggerActivityComponent;
import com.test.uob.hacknewsreader.mvp.Presenter;
import com.test.uob.hacknewsreader.mvp.Scene;

import javax.inject.Inject;

public abstract class BaseActivity<P extends Presenter> extends AppCompatActivity implements Scene{
    @Inject
    protected P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        AppComponent appComponent = ((MainApplication) getApplication()).getAppComponent();
        ActivityComponent activityComponent = DaggerActivityComponent.builder()
                .appComponent(appComponent)
                .activityModule(new ActivityModule())
                .build();
        injectFrom(activityComponent);
        setupActivity(savedInstanceState);
    }

    protected abstract void setupActivity(Bundle savedInstanceState);

    protected abstract int getLayoutId();

    protected abstract void injectFrom(ActivityComponent activityComponent);

    @Override
    protected void onPause() {
        presenter.onSceneRemoved();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onSceneAdded(this, getIntent().getExtras(), this);
    }

}
