package com.qf.meilihui.bean;

/**
 * Created by 肖 on 2017/3/3.
 */

public class MatchContent {

    private   String id;
    private String titleNew1;
    private String titleNew2;
    private String imgUrl;
    private String linkUrl;

    public MatchContent(String id, String titleNew1, String titleNew2,String imgUrl,String linkUrl) {
        this.id = id;
        this.titleNew1 = titleNew1;
        this.titleNew2 = titleNew2;
        this.imgUrl=imgUrl;
        this.linkUrl=linkUrl;
    }
    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitleNew1() {
        return titleNew1;
    }

    public void setTitleNew1(String titleNew1) {
        this.titleNew1 = titleNew1;
    }

    public String getTitleNew2() {
        return titleNew2;
    }

    public void setTitleNew2(String titleNew2) {
        this.titleNew2 = titleNew2;
    }
}
