package com.swati.mvvmdemo.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.swati.mvvmdemo.R;
import com.swati.mvvmdemo.model.Contents;
import com.swati.mvvmdemo.viewmodels.ApiViewModel;

public class QuotesActivity extends AppCompatActivity {

    private TextView mTvQod;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_quotes);

        initViewModelImplementation();
    }

    private void initViewModelImplementation() {
        mTvQod = findViewById(R.id.tv_qod);

        ApiViewModel quotesViewModel = ViewModelProviders.of(this).get(ApiViewModel.class);
        quotesViewModel.init();

        quotesViewModel.getQuotesRepository().observe(this, newsResponse -> {
            Contents contents = newsResponse.getContents();
            String quote = contents.getQuotes().get(0).getQuote();
            mTvQod.setText(quote);
        });
    }

}
