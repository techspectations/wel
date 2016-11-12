package com.offlinenews.network;


import com.offlinenews.models.NewsDetailVo;
import com.offlinenews.models.NewsVo;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by idea on 12-11-2016.
 */
public interface ApiServices {

    @Headers("Content-Type: application/json")
    @GET(EndPointsConfig.GET_NEWS_ARTICLES)
    void getNewsArticles(@Header("Authorization") String Authorization,
                         @Query("page") int page, @Query("size") int size, Callback<NewsVo> callback);

    @GET(EndPointsConfig.GET_NEWS_ARTICLES_DETAILS)
    void getArticleContent(@Header("Authorization") String Authorization,
                           @Path("articleId") String articleId, Callback<NewsDetailVo> callback);

}
