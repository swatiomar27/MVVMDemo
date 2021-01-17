package com.swati.mvvmdemo.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.swati.mvvmdemo.model.NewsResponse;
import com.swati.mvvmdemo.networking.ApiHitRepository;


public class ApiViewModel extends ViewModel {

    private MutableLiveData<NewsResponse> mutableLiveData;
    private ApiHitRepository apiHitRepository;

    public void init() {
        if (mutableLiveData != null) {
            return;
        }
        apiHitRepository = ApiHitRepository.getInstance();

    }

    public LiveData<NewsResponse> getApiHitRepository() {
        mutableLiveData = apiHitRepository.getNews("google-news",
                "5406e7c04383410ba9ae4201862a5759");
        return mutableLiveData;
    }

    public LiveData<NewsResponse> getQuotesRepository() {
        mutableLiveData = apiHitRepository.getQuoteOfTheDay();
        return mutableLiveData;
    }
}
