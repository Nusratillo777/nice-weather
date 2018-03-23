package com.monster.app.niceweather.activities.main.mvp

import com.monster.app.niceweather.models.ForecastCityModel

import java.util.ArrayList

/**
 * Created by monster on 3/5/18.
 */

class MainUiModel {
    var data: List<ForecastCityModel>? = null
    var isLoading: Boolean = false
    var success: Boolean = false
    var error: String = ""

    constructor(data: List<ForecastCityModel>) {
        this.data = data
        success = true
    }

    constructor(isLoading: Boolean) {
        this.isLoading = isLoading
    }

    constructor(error: String?) {
        this.error = error ?: ""
    }

    companion object {
        fun stateLoading() = MainUiModel(true)


        fun stateError(message: String?) = MainUiModel(message)

        fun stateSuccess(data: List<ForecastCityModel>) = MainUiModel(data)
    }
}
