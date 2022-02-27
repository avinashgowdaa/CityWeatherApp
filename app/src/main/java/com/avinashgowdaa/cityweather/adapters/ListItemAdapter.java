package com.avinashgowdaa.cityweather.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.avinashgowdaa.cityweather.R;
import com.avinashgowdaa.cityweather.model.WeatherModel;
import com.avinashgowdaa.cityweather.view.ListActivity;

import java.util.List;

public class ListItemAdapter  extends RecyclerView.Adapter<ListItemAdapter.ViewHolder>{

    private List<WeatherModel.ListData> listdata;
    private Context context;
    private final OnItemClickListener listener;

    // RecyclerView recyclerView;
    public ListItemAdapter(List<WeatherModel.ListData> listdata, Context context, OnItemClickListener listener) {
        this.listdata = listdata;
        this.context = context;
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(WeatherModel.ListData data);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final WeatherModel.ListData myListData = listdata.get(position);
        holder.weatherType.setText(context.getString(R.string.weather_type,
                listdata.get(position).getWeather().get(0).getMain()));
        holder.currentTemp.setText(context.getString(R.string.current_temp,
                String.valueOf(listdata.get(position).getMain().getTemp())));
        holder.minTemp.setText(context.getString(R.string.min_temp,
                String.valueOf(listdata.get(position).getMain().getTempMin())));
        /*holder.maxTemp.setText(context.getString(R.string.max_temp,
                String.valueOf(listdata.get(position).getMain().getTempMax())));*/

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(myListData);
            }
        });
    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView weatherType, currentTemp, maxTemp, minTemp;
        public ConstraintLayout layout;
        public ViewHolder(View itemView) {
            super(itemView);
            this.layout = itemView.findViewById(R.id.layout);
            this.weatherType = itemView.findViewById(R.id.weatherType);
            this.currentTemp = itemView.findViewById(R.id.currentTemp);
            //this.maxTemp = itemView.findViewById(R.id.maxTemp);
            this.minTemp = itemView.findViewById(R.id.minTemp);
        }
    }
}
