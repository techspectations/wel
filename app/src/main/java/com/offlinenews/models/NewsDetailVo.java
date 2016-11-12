package com.offlinenews.models;

/**
 * Created by idea on 12-11-2016.
 */
public class NewsDetailVo {
    private String[] tags;
    private AuthorDetails authorDetails;
    private String lastModified;
    private String imgWeb;
    private String articleURL;
    private String imgMob;
    private String otherImages;
    private String avgRating;
    private String articleID;
    private String content;
    private String shareURL;
    private String title;
    private String thumbnail;
    private String[] relatedArticles;
    private String video;
    private String imageDescription;

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public AuthorDetails getAuthorDetails() {
        return authorDetails;
    }

    public void setAuthorDetails(AuthorDetails authorDetails) {
        this.authorDetails = authorDetails;
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

    public String getArticleURL() {
        return articleURL;
    }

    public void setArticleURL(String articleURL) {
        this.articleURL = articleURL;
    }

    public String getImgMob() {
        return imgMob;
    }

    public void setImgMob(String imgMob) {
        this.imgMob = imgMob;
    }

    public String getOtherImages() {
        return otherImages;
    }

    public void setOtherImages(String otherImages) {
        this.otherImages = otherImages;
    }

    public String getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(String avgRating) {
        this.avgRating = avgRating;
    }

    public String getArticleID() {
        return articleID;
    }

    public void setArticleID(String articleID) {
        this.articleID = articleID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getShareURL() {
        return shareURL;
    }

    public void setShareURL(String shareURL) {
        this.shareURL = shareURL;
    }

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

    public String[] getRelatedArticles() {
        return relatedArticles;
    }

    public void setRelatedArticles(String[] relatedArticles) {
        this.relatedArticles = relatedArticles;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getImageDescription() {
        return imageDescription;
    }

    public void setImageDescription(String imageDescription) {
        this.imageDescription = imageDescription;
    }

    @Override
    public String toString() {
        return "ClassPojo [tags = " + tags + ", authorDetails = " + authorDetails + ", lastModified = " + lastModified + ", imgWeb = " + imgWeb + ", articleURL = " + articleURL + ", imgMob = " + imgMob + ", otherImages = " + otherImages + ", avgRating = " + avgRating + ", articleID = " + articleID + ", content = " + content + ", shareURL = " + shareURL + ", title = " + title + ", thumbnail = " + thumbnail + ", relatedArticles = " + relatedArticles + ", video = " + video + ", imageDescription = " + imageDescription + "]";
    }

    public class AuthorDetails {
        private String authorname;

        public String getAuthorname() {
            return authorname;
        }

        public void setAuthorname(String authorname) {
            this.authorname = authorname;
        }

        @Override
        public String toString() {
            return "ClassPojo [authorname = " + authorname + "]";
        }
    }
}
