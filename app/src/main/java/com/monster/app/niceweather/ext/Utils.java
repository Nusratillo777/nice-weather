package com.monster.app.niceweather.ext;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;
import android.widget.TimePicker;

import com.monster.app.niceweather.models.ForecastModel;

import dagger.Module;

/**
 * Created by monster on 2/26/18.
 */
public class Utils {

    private Context context;
    private int screenWidth = 0;
    private int screenHeight = 0;
    TimePicker timePicker;
    public Utils(Context context) {
        this.context = context;
        this.timePicker = new TimePicker(context);
    }

    public int getScreenWidth() {
        if (screenWidth == 0) {
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            screenWidth = size.x;
        }
        return screenWidth;
    }

    public int getScreenHeight() {
        if (screenHeight == 0) {
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            screenHeight = size.y;
        }
        return screenHeight;
    }


    public double getRightTemp(ForecastModel.TempModel model) {
        int hour;
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP_MR1){
            hour = timePicker.getHour();
        } else {
            hour = timePicker.getCurrentHour();
        }
        if(hour >= 4 && hour < 12) {
            return model.morn;
        }
        if(hour >= 12 && hour < 17) {
            return model.day;
        }
        if(hour >= 17 && hour < 22) {
            return model.eve;
        }
        return model.night;
    }
}
