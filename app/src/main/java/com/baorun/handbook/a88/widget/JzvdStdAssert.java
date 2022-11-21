package com.baorun.handbook.a88.widget;

import android.content.Context;
import android.util.AttributeSet;

import cn.jzvd.JzvdStd;

public class JzvdStdAssert extends JzvdStd {
    public JzvdStdAssert(Context context) {
        super(context);
    }

    public JzvdStdAssert(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public void onPrepared() {
        state = STATE_PREPARED;
        if (!preloading) {
            mediaInterface.start();
            preloading = false;
        }
        fullscreenButton.setVisibility(GONE);
        onStatePlaying();
    }


}
