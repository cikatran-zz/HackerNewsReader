package com.test.uob.hacknewsreader;

import com.test.uob.hacknewsreader.screens.main.MainPresenterImpl;
import com.test.uob.hacknewsreader.screens.main.contracts.MainScene;
import com.test.uob.hacknewsreader.dagger.BasicLogicTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.inject.Inject;

import static org.mockito.Mockito.reset;

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterImplTest extends BasicLogicTest{
    @Inject
    HackerNewsDataSource hackerNewsDataSource;
    @Mock
    MainScene mainSceneMock;

    @Override
    public void setUp() throws Exception {
        getComponent().inject(this);
        super.setUp();
    }

    @Test
    public void testOnSceneAdded() {
        reset(mainSceneMock);

        MainPresenterImpl presenter = new MainPresenterImpl(schedulersProvider, hackerNewsDataSource);

        presenter.onSceneAdded(mainSceneMock, null, null);

        testScheduler.triggerActions();
    }

    @Test
    public void testOnSceneRemoved() {
        MainPresenterImpl presenter = new MainPresenterImpl(schedulersProvider, hackerNewsDataSource);

        presenter.onSceneRemoved();

        testScheduler.triggerActions();
    }

    @Test
    public void getScene() {
        testScheduler.triggerActions();
    }
}
