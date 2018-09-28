package com.test.uob.hacknewsreader.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test.uob.hacknewsreader.R;
import com.test.uob.hacknewsreader.modal.Story;
import com.test.uob.hacknewsreader.screens.comment.CommentActivity;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TopStoriesAdapter extends RecyclerView.Adapter<TopStoriesAdapter.ViewHolder> {

    List<Story> mStoryList;
    Context mContext;

    public TopStoriesAdapter(Context context, List<Story> storyList) {
        this.mContext = context;
        this.mStoryList = storyList;
    }

    public void setStoryList(List<Story> storyList) {
        this.mStoryList = storyList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View convertView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_story, viewGroup, false);
        return new ViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Story story = mStoryList.get(i);
        viewHolder.storyTitle.setText(story.getTitle());
        String timeAgo;
        Date now = new Date();
        long diff = now.getTime() - story.getTime()*1000;
        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;
        long diffDays = diff / (24 * 60 * 60 * 1000);

        if (diffDays > 0) {
            timeAgo = diffDays < 2 ? diffDays + " day ago" : diffDays + " days ago";
        } else if (diffHours > 0) {
            timeAgo = diffHours < 2 ? diffHours + " hour ago" : diffHours + " hours ago";
        } else if (diffMinutes > 0) {
            timeAgo = diffMinutes < 2 ? diffMinutes + " minute ago" : diffMinutes + " minutes ago";
        } else {
            timeAgo = diffSeconds < 2 ? diffSeconds + " second ago" : diffSeconds + " seconds ago";
        }
        String commentCount = story.getKids().size() < 2 ? story.getKids().size() + " comment" : story.getKids().size() + " comments";
        String info = mContext.getString(R.string.story_info, story.getScore(), story.getBy(), timeAgo, commentCount);
        viewHolder.storyInfo.setText(info);
        viewHolder.itemContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, CommentActivity.class);
                intent.putExtra("kids", story.getKids());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mStoryList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.story_info_txt)
        TextView storyInfo;

        @BindView(R.id.story_title_txt)
        TextView storyTitle;

        @BindView(R.id.item_container)
        LinearLayout itemContainer;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
