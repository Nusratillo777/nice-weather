package com.monster.app.niceweather.activities.detail.mvp;

import com.monster.app.niceweather.models.ForecastCityModel;
import com.monster.app.niceweather.models.ForecastModel;

import java.util.List;

/**
 * Created by monster on 3/5/18.
 */

public class DetailUiState {
    public ForecastCityModel city;
    public List<ForecastModel> data;
    public boolean isLoading;
    public boolean success;
    public String error;

    public DetailUiState(List<ForecastModel> data, ForecastCityModel city) {
        this.data = data;
        this.success = true;
        this.isLoading = false;
        this.error = "";
        this.city = city;
    }

    public DetailUiState(boolean isLoading) {
        this.data = null;
        this.success = false;
        this.isLoading = isLoading;
        this.error = "";
        this.city = null;
    }

    public DetailUiState(String error) {
        this.error = error;
        this.success = false;
        this.isLoading = false;
        this.data = null;
        this.city = null;
    }

    public static DetailUiState stateLoading() {
        return new DetailUiState(true);
    }

    public static DetailUiState stateError(String message) {
        return new DetailUiState(message);
    }

    public static DetailUiState stateSuccess(List<ForecastModel> data, ForecastCityModel city) {
        return new DetailUiState(data, city);
    }
}
