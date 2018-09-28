package com.test.uob.hacknewsreader.screens.main;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.test.uob.hacknewsreader.R;
import com.test.uob.hacknewsreader.adapter.TopStoriesAdapter;
import com.test.uob.hacknewsreader.base.BaseActivity;
import com.test.uob.hacknewsreader.screens.main.contracts.MainPresenter;
import com.test.uob.hacknewsreader.screens.main.contracts.MainScene;
import com.test.uob.hacknewsreader.dagger.ActivityComponent;
import com.test.uob.hacknewsreader.modal.Story;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<MainPresenter> implements MainScene {
    @BindView(R.id.stories_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.main_progressbar)
    ProgressBar progressBar;

    TopStoriesAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void setupActivity(Bundle savedInstanceState) {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void injectFrom(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    public void setStoryListAdapter(TopStoriesAdapter adapter) {
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
        this.mAdapter = adapter;
    }

    @Override
    public void refreshStoryList(List<Story> storyList) {
        mAdapter.setStoryList(storyList);
    }

    @Override
    public void showProgressBar(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
