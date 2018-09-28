package com.test.uob.hacknewsreader.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test.uob.hacknewsreader.R;
import com.test.uob.hacknewsreader.modal.Comment;
import com.test.uob.hacknewsreader.utlis.TimeDiffHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder>{

    List<Comment> mCommentList;
    Context mContext;

    public CommentAdapter(Context context, List<Comment> commentList) {
        this.mContext = context;
        this.mCommentList = commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.mCommentList = commentList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View convertView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_comment, viewGroup, false);
        return new CommentAdapter.ViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Comment comment = mCommentList.get(i);
        String timeAgo = TimeDiffHelper.getInstance().timeDiffConverter(comment.getTime());

        String infoTxt = comment.getBy() + " " + timeAgo;
        viewHolder.commentInfo.setText(infoTxt);
        viewHolder.commentText.setText(comment.getText());
        if (comment.getComment().getText() == null) {
            viewHolder.replyExpandBtn.setVisibility(View.GONE);
            viewHolder.replyContainer.setVisibility(View.GONE);
        } else {
            viewHolder.replyExpandBtn.setVisibility(View.VISIBLE);
            viewHolder.replyContainer.setVisibility(View.VISIBLE);
            viewHolder.replyText.setText(comment.getComment().getText());
            String replyInfo = comment.getComment().getBy() + " " + TimeDiffHelper.getInstance().timeDiffConverter(comment.getComment().getTime());
            viewHolder.replyInfo.setText(replyInfo);
        }
    }

    @Override
    public int getItemCount() {
        return mCommentList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.comment_info_txt)
        TextView commentInfo;

        @BindView(R.id.comment_txt)
        TextView commentText;

        @BindView(R.id.reply_expand_btn)
        TextView replyExpandBtn;

        @BindView(R.id.reply_info_txt)
        TextView replyInfo;

        @BindView(R.id.reply_txt)
        TextView replyText;

        @BindView(R.id.reply_container)
        LinearLayout replyContainer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
