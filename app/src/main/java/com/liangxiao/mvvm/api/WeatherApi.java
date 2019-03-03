package com.liangxiao.mvvm.api;

import com.liangxiao.mvvm.model.Result;
import com.liangxiao.mvvm.model.Weather;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WeatherApi {
    @GET("sk/{id}.html")
     Single<Result> getWeather(@Path("id") String id);
}
