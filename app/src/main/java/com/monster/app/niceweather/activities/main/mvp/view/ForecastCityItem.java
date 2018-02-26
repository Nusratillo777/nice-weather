package com.monster.app.niceweather.activities.main.mvp.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.TextView;

import com.monster.app.niceweather.R;
import com.monster.app.niceweather.base.BaseView;
import com.monster.app.niceweather.models.ForecastCityModel;
import com.squareup.picasso.Picasso;

import butterknife.BindView;

import static com.monster.app.niceweather.ext.Constants.BASE_IMAGE_URL;

/**
 * Created by monster on 2/25/18.
 */

public class ForecastCityItem extends BaseView {

    private Picasso picasso;

    @BindView(R.id.city_name)
    TextView cityName;
    @BindView(R.id.weather_description)
    TextView weatherDescription;
    @BindView(R.id.forecast_high)
    TextView forecastLow;
    @BindView(R.id.forecast_low)
    TextView forecastHigh;
    @BindView(R.id.forecast)
    TextView forecast;
    @BindView(R.id.weather_icon)
    ImageView weatherIcon;

    public ForecastCityItem(@NonNull Context context, Picasso picasso) {
        super(context);
        this.picasso = picasso;
        inflate(R.layout.item_forecast_city);
    }

    public void bindView(ForecastCityModel cityItem) {
        cityName.setText(cityItem.name);
        weatherDescription.setText(cityItem.weather.get(0).description);
        forecastLow.setText("" + cityItem.main.temp_min);
        forecastHigh.setText("" + cityItem.main.temp_max);
        forecast.setText("" + cityItem.main.temp);
        picasso.load(BASE_IMAGE_URL + cityItem.weather.get(0).icon + ".png")
                .fit()
                .into(weatherIcon);
    }
}
