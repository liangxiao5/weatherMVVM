package com.liangxiao.mvvm;

import android.databinding.ObservableField;

import com.liangxiao.mvvm.api.RetrofitUtil;
import com.liangxiao.mvvm.api.WeatherApi;
import com.liangxiao.mvvm.model.Result;
import com.liangxiao.mvvm.model.Weather;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class WeatherViewModel {
    private ObservableField<Weather> weather;
    private ObservableField<String> cityId;


    public WeatherViewModel() {
        this.weather = new ObservableField<>();
        this.cityId = new ObservableField<>();
    }

    public ObservableField<String> getCityId() {
        return cityId;
    }

    public void setCityId(ObservableField<String> cityId) {
        this.cityId = cityId;
    }

    public ObservableField<Weather> getWeather() {
        return weather;
    }

    public void setWeather(ObservableField<Weather> weather) {
        this.weather = weather;
    }

    public void loadWeather() {
        Disposable disposable = RetrofitUtil.getService(WeatherApi.class)
                .getWeather(cityId.get())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<Result>() {
                    @Override
                    public void accept(Result result) throws Exception {

                        Weather weatherinfo = result.getWeatherinfo();
                        onReceivedWeather(weatherinfo);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });

    }

    private void onReceivedWeather(Weather newWeather) {
        this.weather.set(newWeather);
    }
//    public void loadWeather() {
//        weather.setCity("北京");
//        weather.setTemp("-6度");
//
//        weather.notifyChange();
//    }
}
