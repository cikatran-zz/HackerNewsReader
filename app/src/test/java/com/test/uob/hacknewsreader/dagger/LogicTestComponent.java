package com.test.uob.hacknewsreader.dagger;

import com.test.uob.hacknewsreader.HackerNewsDataSourceTest;
import com.test.uob.hacknewsreader.MainPresenterImplMockTest;
import com.test.uob.hacknewsreader.MainPresenterImplTest;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {LogicTestModule.class})
public interface LogicTestComponent {

    void inject(MainPresenterImplTest mainPresenterImplMockTest);

    void inject(HackerNewsDataSourceTest hackerNewsDataSourceTest);

    void inject(MainPresenterImplMockTest mainPresenterImplMockTest);
}
