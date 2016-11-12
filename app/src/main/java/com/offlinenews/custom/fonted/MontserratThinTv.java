package com.offlinenews.custom.fonted;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class MontserratThinTv extends AppCompatTextView {


    public MontserratThinTv(Context context) {
        super(context);
        initFonts();
    }

    public MontserratThinTv(Context context, AttributeSet attrs) {
        super(context, attrs);
        initFonts();
    }

    public MontserratThinTv(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initFonts();
    }

    public void init() {

    }

    private String font = "fonts/Montserrat-ExtraLight.otf";

    private void initFonts() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), font);
            this.setTypeface(tf);
        }
    }
}