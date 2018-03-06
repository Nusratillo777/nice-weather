package com.monster.app.niceweather.activities.detail.mvp.view;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.jakewharton.rxbinding2.support.v4.widget.RxSwipeRefreshLayout;
import com.monster.app.niceweather.R;
import com.monster.app.niceweather.base.BaseView;
import com.monster.app.niceweather.ext.Utils;
import com.monster.app.niceweather.models.ForecastModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;

/**
 * Created by monster on 2/26/18.
 */

public class DetailViewImpl extends BaseView implements DetailView{

    @BindView(R.id.map_image)
    ImageView mapImage;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.forecast_list)
    ListView forecastList;

    @BindView(R.id.refresh)
    SwipeRefreshLayout refreshLayout;

    private ForecastAdapter adapter;
    private Picasso picasso;

    public DetailViewImpl(Context context, Picasso picasso, Utils utils) {
        super(context);
        this.picasso = picasso;
        inflate(R.layout.activity_detail);
        adapter = new ForecastAdapter(picasso, utils);
        forecastList.setAdapter(adapter);
    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public void setMapImage(String mapImageUrl) {
        picasso.load(mapImageUrl).into(mapImage);
    }

    @Override
    public Observable<Object> observeRefresh() {
        return RxSwipeRefreshLayout.refreshes(refreshLayout);
    }

    @Override
    public void setLoading(boolean loading) {
        refreshLayout.setRefreshing(loading);
    }

    @Override
    public void setForecastItems(List<ForecastModel> items) {
        adapter.swapData(items);
    }

    @Override
    public void setTitle(String title) {
        toolbar.setTitle(title);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }
}
