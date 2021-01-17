package com.swati.mvvmdemo.networking;

import com.swati.mvvmdemo.model.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ApiEndPointInterface {

    @GET("top-headlines")
    Call<NewsResponse> getNewsList(@Query("sources") String newsSource,
                                   @Query("apiKey") String apiKey);

    @GET()
    Call<NewsResponse> getQuoteOfTheDay(@Url String url);
}
