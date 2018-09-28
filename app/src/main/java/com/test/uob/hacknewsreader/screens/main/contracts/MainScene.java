package com.test.uob.hacknewsreader.screens.main.contracts;

import com.test.uob.hacknewsreader.adapter.TopStoriesAdapter;
import com.test.uob.hacknewsreader.modal.Story;
import com.test.uob.hacknewsreader.mvp.Scene;

import java.util.List;

public interface MainScene extends Scene {
    void setStoryListAdapter(TopStoriesAdapter adapter);
    void refreshStoryList(List<Story> storyList);
    void showProgressBar(boolean show);
}
