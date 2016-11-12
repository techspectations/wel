package com.offlinenews.custom.fonted;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class MontserratRegularTv extends AppCompatTextView {
    public MontserratRegularTv(Context context) {
        super(context);
        initFonts();
    }

    public MontserratRegularTv(Context context, AttributeSet attrs) {
        super(context, attrs);
        initFonts();
    }

    public MontserratRegularTv(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initFonts();
    }

    public void init() {

    }

    private String font = "fonts/Montserrat-Regular.ttf";

    private void initFonts() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), font);
            this.setTypeface(tf);
        }
    }
}
