package com.monster.app.niceweather.activities.main.mvp.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.monster.app.niceweather.base.BaseForecastAdapter;
import com.monster.app.niceweather.models.ForecastCityModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CitiesAdapter extends BaseForecastAdapter<ForecastCityModel> {

    private Picasso picasso;
    public CitiesAdapter(Picasso picasso) {
        this.picasso = picasso;
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
}
