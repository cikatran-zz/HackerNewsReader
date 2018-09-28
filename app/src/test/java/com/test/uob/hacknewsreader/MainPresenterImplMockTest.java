package com.test.uob.hacknewsreader;

import com.test.uob.hacknewsreader.screens.main.MainPresenterImpl;
import com.test.uob.hacknewsreader.screens.main.contracts.MainScene;
import com.test.uob.hacknewsreader.dagger.BasicLogicTest;
import com.test.uob.hacknewsreader.modal.Story;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterImplMockTest extends BasicLogicTest{
    @Mock
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

        when(hackerNewsDataSource.getTopStoryItems())
                .thenReturn(getMockTopStoryItemsObservable());

        presenter.onSceneAdded(mainSceneMock, null, null);

        testScheduler.triggerActions();


        verify(mainSceneMock, times(1)).setStoryListAdapter(any());
        verify(mainSceneMock, times(0)).refreshStoryList(anyList());

    }

    private Single<List<Story>> getMockTopStoryItemsObservable() {
        return Single.just(new ArrayList<>());
    }
}
