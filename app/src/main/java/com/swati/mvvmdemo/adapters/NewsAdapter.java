package com.swati.mvvmdemo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.swati.mvvmdemo.R;
import com.swati.mvvmdemo.callback.IClickListener;
import com.swati.mvvmdemo.model.NewsArticle;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    Context context;
    ArrayList<NewsArticle> articles;
    IClickListener mListener;

    public NewsAdapter(Context context, ArrayList<NewsArticle> articles) {
        this.context = context;
        this.articles = articles;
        mListener = (IClickListener) context;
    }

    @NonNull
    @Override
    public NewsAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.news_item, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.NewsViewHolder holder, int position) {
        holder.onBindContent(articles.get(position));
    }

    @Override
    public int getItemCount() {
        return articles != null ? articles.size() : 0;
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {

        LinearLayout parentLayout;
        TextView tvName;
        TextView tvDesCription;
        ImageView ivNews;

        NewsViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvDesCription = itemView.findViewById(R.id.tvDesCription);
            ivNews = itemView.findViewById(R.id.ivNews);
            parentLayout = itemView.findViewById(R.id.main_news_item);

        }

        void onBindContent(NewsArticle newsArticle) {

            tvName.setText(newsArticle.getTitle());
            tvDesCription.setText(newsArticle.getDescription());
            Picasso.get().load(newsArticle.getUrlToImage()).into(ivNews);

            parentLayout.setOnClickListener(view -> {

                if (mListener != null)
                    mListener.onClickItem(view, getAdapterPosition());
            });
        }
    }
}
