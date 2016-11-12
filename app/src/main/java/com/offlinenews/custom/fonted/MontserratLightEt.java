package com.offlinenews.custom.fonted;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

public class MontserratLightEt extends AppCompatEditText {
    public MontserratLightEt(Context context) {
        super(context);
        init();
    }

    public MontserratLightEt(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MontserratLightEt(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Montserrat-ExtraLight.otf");
            setTypeface(tf);
        }
    }
}