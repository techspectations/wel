package com.offlinenews.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.offlinenews.R;
import com.offlinenews.managers.CookieManager;
import com.offlinenews.models.NewsDetailVo;
import com.offlinenews.ui.NewsDetailsActivity;

import java.util.List;


/**
 * Created by idea on 12-11-2016.
 */
public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    private Context mContext;
    private LayoutInflater mInflater;

    private List<NewsDetailVo> newsList;

    public NewsAdapter(Context context, List<NewsDetailVo> newsList) {
        mContext = context;
        this.newsList = newsList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new NewsHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((NewsHolder) holder).newsCard.setTag(position);
        ((NewsHolder) holder).title.setText(newsList.get(position).getTitle());
        if (newsList.get(position).getContent() != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                ((NewsHolder) holder).content.setText(Html.fromHtml(newsList.get(position).getContent(), 0));
            } else {
                ((NewsHolder) holder).content.setText(Html.fromHtml(newsList.get(position).getContent()));
            }
        }
        if (newsList.get(position).getImgMob() != null) {
            Uri uri = Uri.parse(newsList.get(position).getImgMob());
            ((NewsHolder) holder).banner.setImageURI(uri);
        }
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.news_card:
                CookieManager.sItemPosition = (int) view.getTag();
                mContext.startActivity(new Intent(mContext, NewsDetailsActivity.class));
                break;
        }
    }

    public class NewsHolder extends RecyclerView.ViewHolder {
        CardView newsCard;
        TextView title;
        TextView content;
        SimpleDraweeView banner;

        public NewsHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title_tv);
            content = (TextView) itemView.findViewById(R.id.content_tv);
            banner = (SimpleDraweeView) itemView.findViewById(R.id.banner_iv);
            newsCard = (CardView) itemView.findViewById(R.id.news_card);

            newsCard.setOnClickListener(NewsAdapter.this);
        }
    }

}
