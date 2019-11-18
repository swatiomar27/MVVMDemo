package com.swati.mvvmdemo.networking;


import androidx.lifecycle.MutableLiveData;

import com.swati.mvvmdemo.model.NewsResponse;
import com.swati.mvvmdemo.util.Constant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiHitRepository {

    private static ApiHitRepository apiHitRepository;
    private ApiEndPointInterface apiEndPointInterface;

    private ApiHitRepository() {
        apiEndPointInterface = RetrofitService.cteateService(ApiEndPointInterface.class);
    }

    public static ApiHitRepository getInstance() {
        if (apiHitRepository == null) {
            apiHitRepository = new ApiHitRepository();
        }
        return apiHitRepository;
    }

    public MutableLiveData<NewsResponse> getNews(String source, String key) {
        MutableLiveData<NewsResponse> newsData = new MutableLiveData<>();
        apiEndPointInterface.getNewsList(source, key).enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call,
                                   Response<NewsResponse> response) {
                if (response.isSuccessful()) {
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                newsData.setValue(null);
            }
        });
        return newsData;
    }

    public MutableLiveData<NewsResponse> getQuoteOfTheDay() {
        MutableLiveData<NewsResponse> quotesData = new MutableLiveData<>();
        apiEndPointInterface.getQuoteOfTheDay(Constant.QUOTE_OF_THE_DAY_URL).enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                quotesData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                quotesData.setValue(null);
            }
        });
        return quotesData;
    }
}
