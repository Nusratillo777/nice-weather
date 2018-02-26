package com.monster.app.niceweather.base;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.monster.app.niceweather.activities.main.mvp.view.ForecastCityItem;
import com.monster.app.niceweather.models.ForecastCityModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by monster on 2/26/18.
 */

public abstract class BaseForecastAdapter<T> extends BaseAdapter {

    protected final List<T> forecastList = new ArrayList<>(0);

    @Override
    public int getCount() {
        return forecastList.size();
    }

    @Override
    public T getItem(int position) {
        return forecastList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return forecastList.get(position).hashCode();
    }

    public void swapData(List<T> repoList) {
        this.forecastList.clear();
        if (repoList != null && !repoList.isEmpty()) {
            this.forecastList.addAll(repoList);
        }
        notifyDataSetChanged();
    }
}
