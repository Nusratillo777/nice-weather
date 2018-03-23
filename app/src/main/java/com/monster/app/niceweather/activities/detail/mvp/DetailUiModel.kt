package com.monster.app.niceweather.activities.detail.mvp

import com.monster.app.niceweather.activities.main.mvp.MainUiModel
import com.monster.app.niceweather.models.ForecastCityModel
import com.monster.app.niceweather.models.ForecastModel

/**
 * Created by monster on 3/5/18.
 */

class DetailUiModel {
    var city: ForecastCityModel? = null
    var data: List<ForecastModel>? = null
    var isLoading: Boolean = false
    var success: Boolean = false
    var error: String = ""

    constructor(data: List<ForecastModel>, city: ForecastCityModel) {
        this.data = data
        this.success = true
        this.city = city
    }

    constructor(isLoading: Boolean) {
        this.isLoading = isLoading
    }

    constructor(error: String?) {
        this.error = error ?: ""
    }

    companion object {

        val stateLoading = DetailUiModel(true)

        fun stateError(message: String?) = DetailUiModel(message)

        fun stateSuccess(data: List<ForecastModel>, city: ForecastCityModel) = DetailUiModel(data, city)
    }
}
