package com.avinashgowdaa.cityweather.viewmodels;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

public class MainViewModelFactory extends ViewModelProvider.NewInstanceFactory {

        Context context;

        public MainViewModelFactory(Context context) {
           this.context=context;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            return (T) new MainActivityViewModel(context);
        }
}

