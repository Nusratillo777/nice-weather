package com.monster.app.niceweather.activities.main.mvp;

import com.monster.app.niceweather.models.ForecastCityModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by monster on 3/5/18.
 */

public final class MainUiModel {
    public List<ForecastCityModel> data;
    public boolean isLoading;
    public boolean success;
    public String error;

    public MainUiModel(List<ForecastCityModel> data) {
        this.data = data;
        this.success = true;
        this.isLoading = false;
        error = "";
    }

    public MainUiModel(boolean isLoading) {
        this.data = null;
        this.success = false;
        this.isLoading = isLoading;
        this.error = "";
    }

    public MainUiModel(String error) {
        this.error = error;
        this.success = false;
        this.isLoading = false;
        this.data = null;
    }

    public static MainUiModel stateLoading() {
        return new MainUiModel(true);
    }

    public static MainUiModel stateError(String message) {
        return new MainUiModel(message);
    }

    public static MainUiModel stateSuccess(List<ForecastCityModel> data) {
        return new MainUiModel(data);
    }
}
