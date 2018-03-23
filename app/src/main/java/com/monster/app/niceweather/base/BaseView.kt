package com.monster.app.niceweather.base

import android.content.Context
import android.support.annotation.LayoutRes
import android.view.View
import android.widget.FrameLayout

/**
 * Created by monster on 2/25/18.
 */

open class BaseView(context: Context) : FrameLayout(context) {

    fun inflate(@LayoutRes layoutId: Int) {
        View.inflate(context, layoutId, this)
    }
}
