package com.swati.mvvmdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.swati.mvvmdemo.R;
import com.swati.mvvmdemo.model.NewsArticle;
import com.swati.mvvmdemo.util.Constant;

public class NewsDetailActivity extends AppCompatActivity {

    private NewsArticle mNewsArticle;
    private TextView mNewsTitle;
    private Button mBtnQuotes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        getNewsArticleList();
        init();
        initListenersAndSetTitle();
    }

    private void initListenersAndSetTitle() {
        mNewsTitle.setText(mNewsArticle.getTitle());
        mBtnQuotes.setOnClickListener(view -> navigateToQuoteOfTheDay());
    }

    private void navigateToQuoteOfTheDay() {
        Intent intent = new Intent(NewsDetailActivity.this, QuotesActivity.class);
        startActivity(intent);
    }

    private void init() {
        mNewsTitle = findViewById(R.id.news_title);
        mBtnQuotes = findViewById(R.id.btn_quotes);
    }

    private void getNewsArticleList() {
        if (getIntent() != null)
            mNewsArticle = (NewsArticle) getIntent().getSerializableExtra(Constant.NEWS_DETAIL);
    }
}
