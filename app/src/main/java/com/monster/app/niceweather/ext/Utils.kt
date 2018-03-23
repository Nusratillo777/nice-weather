package com.monster.app.niceweather.ext

import android.content.Context
import android.graphics.Point
import android.view.Display
import android.view.WindowManager
import android.widget.TimePicker

import com.monster.app.niceweather.models.ForecastModel

import dagger.Module

/**
 * Created by monster on 2/26/18.
 */
class Utils(private val context: Context) {
    val screenWidth: Int by lazy {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        val size = Point()
        display.getSize(size)
         size.x
    }
    val screenHeight: Int by lazy {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        val size = Point()
        display.getSize(size)
        size.y
    }
    private var timePicker: TimePicker = TimePicker(context)

    fun getRightTemp(model: ForecastModel.TempModel): Double {
        val hour = if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP_MR1)
            timePicker.hour
        else
            timePicker.currentHour
        return when(hour) {
            in 4..11 -> model.morn
            in 12..16 -> model.day
            in 17..21 -> model.eve
            else -> model.night
        }
    }
}
