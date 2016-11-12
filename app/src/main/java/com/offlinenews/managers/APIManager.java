package com.offlinenews.managers;

import android.os.Handler;

import com.offlinenews.global.Constants;
import com.offlinenews.models.NewsDetailVo;
import com.offlinenews.models.NewsVo;
import com.offlinenews.network.ApiServices;
import com.offlinenews.network.RestClient;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by idea on 12-11-2016.
 */
public class APIManager {

    private static APIManager sAPIManager;
    private ApiServices mApiServices;
    private List<NewsDetailVo> mNewsList;

    private APIManager() {
        RestClient restClient = new RestClient();
        mApiServices = restClient.getApiServices();
        mNewsList = new ArrayList<>();
    }

    public synchronized static APIManager newInstance() {
        if (sAPIManager == null) {
            sAPIManager = new APIManager();
        }
        return sAPIManager;
    }

    int i;
    int counter = 0;

    public void fetchNews(final Callback callback) {
        mApiServices.getNewsArticles(Constants.AUTH_KEY, 0, 15, new Callback<NewsVo>() {
            @Override
            public void success(final NewsVo newsVos, Response response) {
                if (newsVos.getArticleSummary() != null) {
                    Handler handler = new Handler();
                    counter = 0;
                    for (i = 0; i < newsVos.getArticleSummary().size(); i++) {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                fetchNewsDetail(newsVos.getArticleSummary().get(counter).getArticleID(), callback);
                                counter++;
                            }
                        }, (1200 * i));
                    }
                }
            }

            @Override
            public void failure(RetrofitError error) {
                callback.failure(error);
            }
        });
    }

    public void fetchNewsDetail(String articleId, final Callback callback) {
        mApiServices.getArticleContent(Constants.AUTH_KEY, articleId, new Callback<NewsDetailVo>() {
            @Override
            public void success(NewsDetailVo newsDetailVo, Response response) {
                mNewsList.add(newsDetailVo);
                callback.success(mNewsList, response);
            }

            @Override
            public void failure(RetrofitError error) {
                callback.failure(error);
            }
        });
    }

    public List<NewsDetailVo> getNewsList() {
        return mNewsList;
    }

    public void setNewsList(List<NewsDetailVo> newsList) {
        mNewsList = newsList;
    }
}
