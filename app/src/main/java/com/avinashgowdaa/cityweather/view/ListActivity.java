package com.avinashgowdaa.cityweather.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.avinashgowdaa.cityweather.R;
import com.avinashgowdaa.cityweather.adapters.ListItemAdapter;
import com.avinashgowdaa.cityweather.model.WeatherModel;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.util.Objects;

public class ListActivity extends AppCompatActivity {

    private WeatherModel weatherModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        String data = getIntent().getStringExtra("weatherData");

        if(data != null) {
            Gson g = new Gson();
            weatherModel = g.fromJson(data, WeatherModel.class);
            getSupportActionBar().setTitle(weatherModel.getCity().getName());
            RecyclerView recyclerView = findViewById(R.id.recyclerView);
            ListItemAdapter adapter = new ListItemAdapter(weatherModel.getListData(), this,
                    data1 -> {
                        Gson gson = new Gson();
                        String json = gson.toJson(data1);
                        Intent intent = new Intent(ListActivity.this, DetailsActivity.class);
                        intent.putExtra("weatherDetails", json);
                        intent.putExtra("title", weatherModel.getCity().getName());
                        startActivity(intent);
                    });
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
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