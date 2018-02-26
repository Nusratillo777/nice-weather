package com.monster.app.niceweather.activities.main.mvp.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.monster.app.niceweather.models.ForecastCityModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CitiesAdapter extends BaseAdapter {

    private Picasso picasso;
    private final List<ForecastCityModel> forecastList = new ArrayList<>(0);

    public CitiesAdapter(Picasso picasso) {
        this.picasso = picasso;
    }

    @Override
    public int getCount() {
        return forecastList.size();
    }

    @Override
    public ForecastCityModel getItem(int position) {
        return forecastList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return forecastList.get(position).id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ForecastCityItem listItem;

        if (convertView == null) {
            listItem = new ForecastCityItem(parent.getContext(), picasso);
        } else {
            listItem = ForecastCityItem.class.cast(convertView);
        }
        listItem.bindView(forecastList.get(position));
        return listItem;
    }

    public void swapData(List<ForecastCityModel> repoList) {
        this.forecastList.clear();
        if (repoList != null && !repoList.isEmpty()) {
            this.forecastList.addAll(repoList);
        }
        notifyDataSetChanged();
    }
}
