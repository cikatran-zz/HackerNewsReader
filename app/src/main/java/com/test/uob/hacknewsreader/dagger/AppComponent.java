package com.test.uob.hacknewsreader.dagger;

import com.test.uob.hacknewsreader.api.HackerNewsReaderService;
import com.test.uob.hacknewsreader.base.BaseSchedulerProvider;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, ApiModule.class})
public interface AppComponent {

    BaseSchedulerProvider getSchedulerProvider();
    HackerNewsReaderService provideHackerNewsReaderService();

}
