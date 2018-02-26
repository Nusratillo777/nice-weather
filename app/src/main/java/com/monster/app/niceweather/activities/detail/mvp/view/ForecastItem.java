package com.monster.app.niceweather.activities.detail.mvp.view;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.monster.app.niceweather.R;
import com.monster.app.niceweather.base.BaseView;
import com.monster.app.niceweather.ext.Utils;
import com.monster.app.niceweather.models.ForecastCityModel;
import com.monster.app.niceweather.models.ForecastModel;
import com.squareup.picasso.Picasso;

import java.time.LocalTime;

import butterknife.BindView;

import static com.monster.app.niceweather.ext.Constants.BASE_IMAGE_URL;

/**
 * Created by monster on 2/26/18.
 */

public class ForecastItem extends BaseView {

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

    private final Picasso picasso;
    private final Utils utils;

    public ForecastItem(Context context, Picasso picasso, Utils utils) {
        super(context);
        inflate(R.layout.item_forecast);
        this.picasso = picasso;
        this.utils = utils;
    }

    public void bindView(ForecastModel forecastModel) {
        weatherDescription.setText(forecastModel.weather.get(0).description);
        forecastLow.setText("" + forecastModel.temp.min);
        forecastHigh.setText("" + forecastModel.temp.max);
        forecast.setText("" + utils.getRightTemp(forecastModel.temp));
        picasso.load(BASE_IMAGE_URL + forecastModel.weather.get(0).icon + ".png")
                .fit()
                .centerCrop()
                .into(weatherIcon);
    }
}
