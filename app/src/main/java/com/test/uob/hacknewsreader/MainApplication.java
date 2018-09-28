package com.test.uob.hacknewsreader;

import android.app.Application;

import com.test.uob.hacknewsreader.dagger.ApiModule;
import com.test.uob.hacknewsreader.dagger.AppComponent;
import com.test.uob.hacknewsreader.dagger.AppModule;
import com.test.uob.hacknewsreader.dagger.DaggerAppComponent;

public class MainApplication extends Application {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initComponent();
    }

    private void initComponent() {
        this.appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .apiModule(new ApiModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
