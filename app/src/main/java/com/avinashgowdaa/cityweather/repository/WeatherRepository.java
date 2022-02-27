package com.avinashgowdaa.cityweather.repository;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.avinashgowdaa.cityweather.model.WeatherModel;
import com.avinashgowdaa.cityweather.network.ApiCall;
import com.avinashgowdaa.cityweather.network.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRepository {

    private ApiCall apiInterface;
    private final String apiKey = "65d00499677e59496ca2f318eb68c049";
    Context context;


    public WeatherRepository(Context context) {
        this.context = context;
    }

    public MutableLiveData<WeatherModel> getWeatherDataByCity(String cityName) {
        final MutableLiveData<WeatherModel> weatherLiveData = new MutableLiveData<>();
        apiInterface = ApiClient.getClientAuthentication().create(ApiCall.class);
        Call<WeatherModel> call = apiInterface.getWeatherDataByCity(cityName, apiKey);
        call.enqueue(new Callback<WeatherModel>() {
            @Override
            public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
                if(response.body()!=null) {
                    weatherLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<WeatherModel> call, Throwable t) {
                String ss = t.getMessage();
                weatherLiveData.setValue(null);
            }
        });
        return weatherLiveData;
    }
}
