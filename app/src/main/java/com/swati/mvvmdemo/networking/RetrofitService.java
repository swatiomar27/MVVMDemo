package com.swati.mvvmdemo.networking;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    public static <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }

}
