package com.monster.app.niceweather.models;

import java.util.List;

/**
 * Created by monster on 2/24/18.
 */

public class ForecastModel {
    public TempModel temp;
    public List<ForecastCityModel.WeatherModel> weather;

    public static class TempModel {
        public double day;
        public double min;
        public double max;
        public double night;
        public double eve;
        public double morn;
    }
}
