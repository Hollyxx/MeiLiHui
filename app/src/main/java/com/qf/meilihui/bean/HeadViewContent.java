package com.qf.meilihui.bean;

/**
 * Created by 肖 on 2017/3/4.
 */

public class HeadViewContent {

    private String imgUrl;
    private String shareUrl;//html
    private String imgAndroid;//欢迎页
    private String shareContent;

    public HeadViewContent(String imgUrl, String bannerLink, String imgAndroid, String shareContent) {
        this.imgUrl = imgUrl;
        this.shareUrl=shareUrl;
        this.imgAndroid = imgAndroid;
        this.shareContent = shareContent;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgAndroid() {
        return imgAndroid;
    }

    public void setImgAndroid(String imgAndroid) {
        this.imgAndroid = imgAndroid;
    }

    public String getShareContent() {
        return shareContent;
    }

    public void setShareContent(String shareContent) {
        this.shareContent = shareContent;
    }
}
