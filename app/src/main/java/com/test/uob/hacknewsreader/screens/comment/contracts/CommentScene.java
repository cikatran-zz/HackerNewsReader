package com.test.uob.hacknewsreader.screens.comment.contracts;


import com.test.uob.hacknewsreader.adapter.CommentAdapter;
import com.test.uob.hacknewsreader.modal.Comment;
import com.test.uob.hacknewsreader.mvp.Scene;

import java.util.List;

public interface CommentScene extends Scene {
    void setCommentListAdapter(CommentAdapter commentListAdapter);
    void refreshCommentList(List<Comment> commentList);
    void showProgressBar(boolean show);
}
