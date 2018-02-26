package com.monster.app.niceweather.base;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.widget.FrameLayout;

import butterknife.ButterKnife;

/**
 * Created by monster on 2/25/18.
 */

public class BaseView extends FrameLayout {

    public BaseView(Context context) {
        super(context);
    }

    public void inflate(@LayoutRes int layoutId) {
        inflate(getContext(), layoutId, this);
        ButterKnife.bind(this);
    }
}
