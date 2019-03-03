package com.liangxiao.mvvm.api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil {
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://www.weather.com.cn/data/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

    public static <T> T getService(Class<T> service) {
        return retrofit.create(service);
    }
}
