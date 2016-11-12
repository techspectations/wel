package com.offlinenews.custom.fonted;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class MontserratMediumTv extends AppCompatTextView {
    public MontserratMediumTv(Context context) {
        super(context);
        init();
    }

    public MontserratMediumTv(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MontserratMediumTv(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), font);
            this.setTypeface(tf);
        }
    }

    private String font = "fonts/Montserrat-Medium.otf";
}