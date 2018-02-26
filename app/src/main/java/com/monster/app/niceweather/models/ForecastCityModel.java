package com.monster.app.niceweather.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by monster on 2/24/18.
 */

public class ForecastCityModel implements Serializable{

    public int id;
    public String name;
    public CoordModel coord;
    public TempModel main;
    public List<WeatherModel> weather;


    public static class CoordModel implements Serializable{
        public double Lat;
        public double Lon;
    }


    public static class TempModel implements Serializable{
        public double temp;
        public double temp_min;
        public double temp_max;
    }

    public static class WeatherModel implements Serializable{
        public String description;
        public String icon;
    }
}
