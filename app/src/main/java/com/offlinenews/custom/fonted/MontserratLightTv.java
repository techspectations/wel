package com.offlinenews.custom.fonted;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class MontserratLightTv extends AppCompatTextView {
    public MontserratLightTv(Context context) {
        super(context);
        init();
    }

    public MontserratLightTv(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MontserratLightTv(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Montserrat-Light.otf");
            setTypeface(tf);
        }
    }
}