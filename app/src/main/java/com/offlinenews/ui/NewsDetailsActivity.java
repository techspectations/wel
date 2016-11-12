package com.offlinenews.ui;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.offlinenews.R;
import com.offlinenews.managers.APIManager;
import com.offlinenews.managers.CookieManager;

/**
 * Created by idea on 12-11-2016.
 */
public class NewsDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        ViewCompat.setTransitionName(findViewById(R.id.app_bar_layout), "EXTRA_IMAGE");
        supportPostponeEnterTransition();

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((Toolbar) findViewById(R.id.toolbar)).setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        String itemTitle = getIntent().getStringExtra("TITLE");
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(itemTitle);
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));

        SimpleDraweeView banner = (SimpleDraweeView) findViewById(R.id.banner_iv);
        if (APIManager.newInstance().getNewsList().get(CookieManager.sItemPosition).getImgMob() != null) {
            Uri uri = Uri.parse(APIManager.newInstance().getNewsList().get(CookieManager.sItemPosition).getImgMob());
            banner.setImageURI(uri);
        }

        TextView title = (TextView) findViewById(R.id.title_tv);
        TextView content = (TextView) findViewById(R.id.content_tv);

        title.setText("" + APIManager.newInstance().getNewsList().get(CookieManager.sItemPosition).getTitle());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            content.setText("" + Html.fromHtml(APIManager.newInstance().getNewsList()
                    .get(CookieManager.sItemPosition).getContent(), 0));
        } else {
            content.setText("" + Html.fromHtml(APIManager.newInstance().getNewsList()
                    .get(CookieManager.sItemPosition).getContent()));
        }

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        try {
            return super.dispatchTouchEvent(motionEvent);
        } catch (NullPointerException e) {
            return false;
        }
    }

    /*private void applyPalette(Palette palette) {
        int primaryDark = getResources().getColor(R.color.primary_dark);
        int primary = getResources().getColor(R.color.primary);
        collapsingToolbarLayout.setContentScrimColor(palette.getMutedColor(primary));
        collapsingToolbarLayout.setStatusBarScrimColor(palette.getDarkMutedColor(primaryDark));
        updateBackground((FloatingActionButton) findViewById(R.id.fab), palette);
        supportStartPostponedEnterTransition();
    }

    private void updateBackground(FloatingActionButton fab, Palette palette) {
        int lightVibrantColor = palette.getLightVibrantColor(getResources().getColor(android.R.color.white));
        int vibrantColor = palette.getVibrantColor(getResources().getColor(R.color.accent));

        fab.setRippleColor(lightVibrantColor);
        fab.setBackgroundTintList(ColorStateList.valueOf(vibrantColor));
    }*/
}
