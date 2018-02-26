package com.monster.app.niceweather.models;

import java.util.List;

/**
 * Created by monster on 2/24/18.
 */

public class ForecastCityModel {

    public int id;
    public String name;
    public CoordModel coord;
    public TempModel main;
    public List<WeatherModel> weather;


    public static class CoordModel {
        public double Lat;
        public double Lon;
    }


    public static class TempModel {
        public double temp;
        public double temp_min;
        public double temp_max;
    }

    public static class WeatherModel {
        public String description;
        public String icon;
    }
}
