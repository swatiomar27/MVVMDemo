package com.swati.mvvmdemo.networking;


import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.swati.mvvmdemo.model.NewsResponse;
import com.swati.mvvmdemo.util.Constant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiHitRepository {

    private static ApiHitRepository mApiHitRepository;
    private final ApiEndPointInterface mApiEndPointInterface;

    private ApiHitRepository() {
        mApiEndPointInterface = RetrofitService.createService(ApiEndPointInterface.class);
    }

    public static ApiHitRepository getInstance() {
        if (mApiHitRepository == null) {
            mApiHitRepository = new ApiHitRepository();
        }
        return mApiHitRepository;
    }

    public MutableLiveData<NewsResponse> getNews(String source, String key) {
        MutableLiveData<NewsResponse> newsData = new MutableLiveData<>();
        mApiEndPointInterface.getNewsList(source, key).enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(@NonNull Call<NewsResponse> call,
                                   @NonNull Response<NewsResponse> response) {
                if (response.isSuccessful()) {
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<NewsResponse> call, @NonNull Throwable t) {
                newsData.setValue(null);
            }
        });
        return newsData;
    }

    public MutableLiveData<NewsResponse> getQuoteOfTheDay() {
        MutableLiveData<NewsResponse> quotesData = new MutableLiveData<>();
        mApiEndPointInterface.getQuoteOfTheDay(Constant.QUOTE_OF_THE_DAY_URL).enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(@NonNull Call<NewsResponse> call, @NonNull Response<NewsResponse> response) {
                quotesData.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<NewsResponse> call, @NonNull Throwable t) {
                quotesData.setValue(null);
            }
        });
        return quotesData;
    }
}
