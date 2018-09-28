package com.test.uob.hacknewsreader.dagger;

import android.app.Application;

import com.test.uob.hacknewsreader.base.BaseSchedulerProvider;
import com.test.uob.hacknewsreader.logic.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return application;
    }

    @Provides
    @Singleton
    BaseSchedulerProvider providerSchedulerProvider(SchedulerProvider provider) {
        return provider;
    }


}
