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

    ArrayList<NewsArticle> mArticlesArrayList = new ArrayList<>();
    NewsAdapter mNewsAdapter;
    RecyclerView mHeadlineRecyclerView;
    ApiViewModel mApiViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHeadlineRecyclerView = findViewById(R.id.rvNews);

        //Get View model
        mApiViewModel = ViewModelProviders.of(this).get(ApiViewModel.class);
        //Initialize api repository
        mApiViewModel.init();
        //Observe data changes through Live data
        mApiViewModel.getApiHitRepository().observe(this, newsResponse -> {
            List<NewsArticle> newsArticles = newsResponse.getArticles();
            mArticlesArrayList.addAll(newsArticles);
            mNewsAdapter.notifyDataSetChanged();
        });

        setupRecyclerView();
    }

    private void setupRecyclerView() {
        if (mNewsAdapter == null) {
            mNewsAdapter = new NewsAdapter(NewsActivity.this, mArticlesArrayList);
            mHeadlineRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mHeadlineRecyclerView.setAdapter(mNewsAdapter);
            mHeadlineRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mHeadlineRecyclerView.setNestedScrollingEnabled(true);
        } else {
            mNewsAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClickItem(View view, int adapterPosition) {

        Intent intent = new Intent(NewsActivity.this, NewsDetailActivity.class);
        intent.putExtra(Constant.NEWS_DETAIL, mArticlesArrayList.get(adapterPosition));
        startActivity(intent);
    }
}
