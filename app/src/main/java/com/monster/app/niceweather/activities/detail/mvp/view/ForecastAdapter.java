package com.monster.app.niceweather.activities.detail.mvp.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.monster.app.niceweather.activities.main.mvp.view.ForecastCityItem;
import com.monster.app.niceweather.base.BaseForecastAdapter;
import com.monster.app.niceweather.ext.Utils;
import com.monster.app.niceweather.models.ForecastModel;
import com.squareup.picasso.Picasso;

/**
 * Created by monster on 2/26/18.
 */

public class ForecastAdapter extends BaseForecastAdapter<ForecastModel> {

    private Picasso picasso;
    private Utils utils;

    public ForecastAdapter(Picasso picasso, Utils utils) {
        this.picasso = picasso;
        this.utils = utils;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ForecastItem listItem;

        if (convertView == null) {
            listItem = new ForecastItem(parent.getContext(), picasso, utils);
        } else {
            listItem = ForecastItem.class.cast(convertView);
        }
        listItem.bindView(forecastList.get(position));
        return listItem;
    }
}
