package com.avinashgowdaa.cityweather.viewmodels;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.avinashgowdaa.cityweather.model.WeatherModel;
import com.avinashgowdaa.cityweather.repository.WeatherRepository;

public class MainActivityViewModel extends ViewModel {

    WeatherRepository weatherRepository;

    public MainActivityViewModel(Context context) {
        weatherRepository = new WeatherRepository(context);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public MutableLiveData<WeatherModel> getWeatherDetails(String cityName) {
        return weatherRepository.getWeatherDataByCity(cityName);
    }
}
