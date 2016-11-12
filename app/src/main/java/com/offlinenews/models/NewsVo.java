package com.offlinenews.models;

import java.util.List;

/**
 * Created by idea on 12-11-2016.
 */
public class NewsVo {
    private String title;
    private String articlesCount;
    private String count;
    private String page;
    private String code;
    private String[] type;
    private List<ArticleSummary> articleSummary;
    private String totalPages;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArticlesCount() {
        return articlesCount;
    }

    public void setArticlesCount(String articlesCount) {
        this.articlesCount = articlesCount;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String[] getType() {
        return type;
    }

    public void setType(String[] type) {
        this.type = type;
    }

    public List<ArticleSummary> getArticleSummary() {
        return articleSummary;
    }

    public void setArticleSummary(List<ArticleSummary> articleSummary) {
        this.articleSummary = articleSummary;
    }

    public String getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(String totalPages) {
        this.totalPages = totalPages;
    }

    @Override
    public String toString() {
        return "ClassPojo [title = " + title + ", articlesCount = " + articlesCount + ", count = " + count + ", page = " + page + ", code = " + code + ", type = " + type + ", articleSummary = " + articleSummary + ", totalPages = " + totalPages + "]";
    }

    public class ArticleSummary {
        private String title;
        private String thumbnail;
        private String lastModified;
        private String imgWeb;
        private String imgMob;
        private String articleURL;
        private String otherImages;
        private String articleID;
        private String video;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getLastModified() {
            return lastModified;
        }

        public void setLastModified(String lastModified) {
            this.lastModified = lastModified;
        }

        public String getImgWeb() {
            return imgWeb;
        }

        public void setImgWeb(String imgWeb) {
            this.imgWeb = imgWeb;
        }

        public String getImgMob() {
            return imgMob;
        }

        public void setImgMob(String imgMob) {
            this.imgMob = imgMob;
        }

        public String getArticleURL() {
            return articleURL;
        }

        public void setArticleURL(String articleURL) {
            this.articleURL = articleURL;
        }

        public String getOtherImages() {
            return otherImages;
        }

        public void setOtherImages(String otherImages) {
            this.otherImages = otherImages;
        }

        public String getArticleID() {
            return articleID;
        }

        public void setArticleID(String articleID) {
            this.articleID = articleID;
        }

        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.video = video;
        }

        @Override
        public String toString() {
            return "ClassPojo [title = " + title + ", thumbnail = " + thumbnail + ", lastModified = " + lastModified + ", imgWeb = " + imgWeb + ", imgMob = " + imgMob + ", articleURL = " + articleURL + ", otherImages = " + otherImages + ", articleID = " + articleID + ", video = " + video + "]";
        }
    }
}
