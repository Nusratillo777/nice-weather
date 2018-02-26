package com.monster.app.niceweather.activities.main.mvp.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.jakewharton.rxbinding2.support.v4.widget.RxSwipeRefreshLayout;
import com.jakewharton.rxbinding2.widget.RxAbsListView;
import com.jakewharton.rxbinding2.widget.RxAdapterView;
import com.monster.app.niceweather.R;
import com.monster.app.niceweather.base.BaseView;
import com.monster.app.niceweather.models.ForecastCityModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.Observable;

/**
 * Created by monster on 2/25/18.
 */

@SuppressLint("ViewConstructor")
public class MainViewImpl extends BaseView implements MainView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.forecast_list)
    ListView forecastList;

    @BindView(R.id.refresh)
    SwipeRefreshLayout refreshLayout;

    CitiesAdapter adapter;

    public MainViewImpl(Context context, Picasso picasso) {
        super(context);
        inflate(R.layout.activity_main);
        adapter = new CitiesAdapter(picasso);
        forecastList.setAdapter(adapter);
    }

    @Override
    public Observable<Object> observeRefresh() {
        return RxSwipeRefreshLayout.refreshes(refreshLayout);
    }

    @Override
    public Observable<Integer> observeItemClick() {
        return RxAdapterView.itemClicks(forecastList).map(position -> adapter.getItem(position).id);
    }

    @Override
    public void setLoading(boolean isLoading) {
        refreshLayout.setRefreshing(isLoading);
    }

    @Override
    public void setForecastCityItems(List<ForecastCityModel> items) {
        adapter.swapData(items);
    }

    @Override
    public View getView() {
        return this;
    }
}
