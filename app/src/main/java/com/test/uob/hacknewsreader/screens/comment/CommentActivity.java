package com.test.uob.hacknewsreader.screens.comment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.test.uob.hacknewsreader.R;
import com.test.uob.hacknewsreader.adapter.CommentAdapter;
import com.test.uob.hacknewsreader.base.BaseActivity;
import com.test.uob.hacknewsreader.dagger.ActivityComponent;
import com.test.uob.hacknewsreader.modal.Comment;
import com.test.uob.hacknewsreader.screens.comment.contracts.CommentPresenter;
import com.test.uob.hacknewsreader.screens.comment.contracts.CommentScene;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommentActivity extends BaseActivity<CommentPresenter> implements CommentScene {
    @BindView(R.id.comment_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.comment_progressbar)
    ProgressBar progressBar;

    private RecyclerView.LayoutManager mLayoutManager;
    CommentAdapter mAdapter;

    @Override
    protected void setupActivity(Bundle savedInstanceState) {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_comment;
    }

    @Override
    protected void injectFrom(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    public void setCommentListAdapter(CommentAdapter adapter) {
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
        this.mAdapter = adapter;
    }

    @Override
    public void refreshCommentList(List<Comment> commentList) {
        mAdapter.setCommentList(commentList);
    }

    @Override
    public void showProgressBar(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
