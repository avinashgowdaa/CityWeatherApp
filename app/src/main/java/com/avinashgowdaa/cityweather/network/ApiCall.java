package com.avinashgowdaa.cityweather.network;

import com.avinashgowdaa.cityweather.model.WeatherModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiCall {
    @GET("data/2.5/forecast")
    Call<WeatherModel> getWeatherDataByCity(@Query("q") String cityName, @Query("appid") String apiKey);
}
