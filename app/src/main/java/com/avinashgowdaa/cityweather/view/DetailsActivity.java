package com.avinashgowdaa.cityweather.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.avinashgowdaa.cityweather.R;
import com.avinashgowdaa.cityweather.model.WeatherModel;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.util.Objects;

public class DetailsActivity extends AppCompatActivity {

    private WeatherModel.ListData weatherData;
    private TextView curTemp, feelsLikeTemp, weatherType, weatherDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        curTemp = findViewById(R.id.curTemp);
        feelsLikeTemp = findViewById(R.id.feelsLike);
        weatherType = findViewById(R.id.weather);
        weatherDesc = findViewById(R.id.weatherDescription);

        String data = getIntent().getStringExtra("weatherDetails");
        String title = getIntent().getStringExtra("title");

        if(data != null) {
            Gson g = new Gson();
            weatherData = g.fromJson(data, WeatherModel.ListData.class);
            getSupportActionBar().setTitle(title);
            curTemp.setText(String.valueOf(weatherData.getMain().getTemp()));
            feelsLikeTemp.setText(getString(R.string.feels_like_temp,
                    String.valueOf(weatherData.getMain().getFeelsLike())));
            weatherType.setText(getString(R.string.weather_type, weatherData.getWeather().get(0).getMain()));
            weatherDesc.setText(weatherData.getWeather().get(0).getDescription());
        } else {
            showMessage(getString(R.string.no_data));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void showMessage(String message) {
        ConstraintLayout layout = findViewById(R.id.layout);
        Snackbar snackbar = Snackbar.make(layout, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}