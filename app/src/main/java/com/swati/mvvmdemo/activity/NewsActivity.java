package com.swati.mvvmdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.swati.mvvmdemo.R;
import com.swati.mvvmdemo.adapters.NewsAdapter;
import com.swati.mvvmdemo.callback.IClickListener;
import com.swati.mvvmdemo.model.NewsArticle;
import com.swati.mvvmdemo.util.Constant;
import com.swati.mvvmdemo.viewmodels.ApiViewModel;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity implements IClickListener {

    ArrayList<NewsArticle> articleArrayList = new ArrayList<>();
    NewsAdapter newsAdapter;
    RecyclerView rvHeadline;
    ApiViewModel apiViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvHeadline = findViewById(R.id.rvNews);

        apiViewModel = ViewModelProviders.of(this).get(ApiViewModel.class);
        apiViewModel.init();
        apiViewModel.getApiHitRepository().observe(this, newsResponse -> {
            List<NewsArticle> newsArticles = newsResponse.getArticles();
            articleArrayList.addAll(newsArticles);
            newsAdapter.notifyDataSetChanged();
        });

        setupRecyclerView();
    }

    private void setupRecyclerView() {
        if (newsAdapter == null) {
            newsAdapter = new NewsAdapter(NewsActivity.this, articleArrayList);
            rvHeadline.setLayoutManager(new LinearLayoutManager(this));
            rvHeadline.setAdapter(newsAdapter);
            rvHeadline.setItemAnimator(new DefaultItemAnimator());
            rvHeadline.setNestedScrollingEnabled(true);
        } else {
            newsAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClickItem(View view, int adapterPosition) {

        Intent intent = new Intent(NewsActivity.this, NewsDetailActivity.class);
        intent.putExtra(Constant.NEWS_DETAIL, articleArrayList.get(adapterPosition));
        startActivity(intent);
    }
}
