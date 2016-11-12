package com.offlinenews.network;

/**
 * Created by idea on 12-11-2016.
 */
public interface EndPointsConfig {

    String HTTP = "http://";
    String HTTPS = "https://";
    String IP_WEB_ADDRESS = "developer.manoramaonline.com";
    String SERVER_ADDRESS = HTTPS + IP_WEB_ADDRESS;
    String API_ROUTE = "/api/editions/en";

    // Get News Articles
    String GET_NEWS_ARTICLES = API_ROUTE + "/sections/news_world/articles"; //?page=1&size=10
    String GET_NEWS_ARTICLES_DETAILS = API_ROUTE + "/articles/{articleId}"; //c70b9fe3dfb36586703cfc18d3f91a59


}
