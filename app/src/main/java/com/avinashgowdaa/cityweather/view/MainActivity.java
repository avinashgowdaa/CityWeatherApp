package com.avinashgowdaa.cityweather.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.avinashgowdaa.cityweather.R;
import com.avinashgowdaa.cityweather.model.WeatherModel;
import com.avinashgowdaa.cityweather.viewmodels.MainActivityViewModel;
import com.avinashgowdaa.cityweather.viewmodels.MainViewModelFactory;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    MainActivityViewModel activityViewModel;
    Button lookupBtn;
    ProgressDialog progressDialog;
    EditText editTextCityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainViewModelFactory factory = new MainViewModelFactory(this);
        activityViewModel = ViewModelProviders.of(this, factory).get(MainActivityViewModel.class);
        //activityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        lookupBtn = findViewById(R.id.btn_lookup);
        editTextCityName = findViewById(R.id.editTextCityName);
        lookupBtn.setOnClickListener(view -> getWeatherData());
    }

    private void getWeatherData() {
        if(editTextCityName.getText() == null || editTextCityName.getText().toString().trim().length()<=2) {
            showMessage(getString(R.string.invalid_city_name));
        } else {
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Fetching "+ editTextCityName.getText().toString()+ " Weather Details");
            progressDialog.show();

            activityViewModel.getWeatherDetails(editTextCityName.getText().toString()).observe(this, new Observer<WeatherModel>() {
                @Override
                public void onChanged(WeatherModel weatherModel) {
                    if(weatherModel != null) {
                        //showMessage(weatherModel.getCity().getName());

                        Gson gson = new Gson();
                        String json = gson.toJson(weatherModel);

                        progressDialog.dismiss();
                        Intent intent = new Intent(MainActivity.this, ListActivity.class);
                        intent.putExtra("weatherData", json);
                        startActivity(intent);
                    } else {
                        progressDialog.dismiss();
                        showMessage(getString(R.string.no_data));
                    }
                }
            });
        }
    }

    void showToast(String name) {
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
    }

    void showMessage(String message) {
        ConstraintLayout layout = findViewById(R.id.layout);
        Snackbar snackbar = Snackbar.make(layout, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}