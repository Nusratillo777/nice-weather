package com.monster.app.niceweather.activities.detail.mvp;

import com.monster.app.niceweather.activities.main.mvp.MainUiModel;
import com.monster.app.niceweather.models.ForecastCityModel;
import com.monster.app.niceweather.models.ForecastModel;

import java.util.List;

/**
 * Created by monster on 3/5/18.
 */

public class DetailUiModel {
    public ForecastCityModel city;
    public List<ForecastModel> data;
    public boolean isLoading;
    public boolean success;
    public String error;

    public DetailUiModel(List<ForecastModel> data, ForecastCityModel city) {
        this.data = data;
        this.success = true;
        this.isLoading = false;
        this.error = "";
        this.city = city;
    }

    public DetailUiModel(boolean isLoading) {
        this.data = null;
        this.success = false;
        this.isLoading = isLoading;
        this.error = "";
        this.city = null;
    }

    public DetailUiModel(String error) {
        this.error = error;
        this.success = false;
        this.isLoading = false;
        this.data = null;
        this.city = null;
    }

    public static DetailUiModel stateLoading() {
        return new DetailUiModel(true);
    }

    public static DetailUiModel stateError(String message) {
        return new DetailUiModel(message);
    }

    public static DetailUiModel stateSuccess(List<ForecastModel> data, ForecastCityModel city) {
        return new DetailUiModel(data, city);
    }
}
