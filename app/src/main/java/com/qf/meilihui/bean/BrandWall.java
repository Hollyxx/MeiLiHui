package com.qf.meilihui.bean;

/**
 * Created by 肖 on 2017/3/6.
 */

public class BrandWall {
    private String id;
    private String  brandName;
    private  String   brandLogoUrl;
    private String eventId;

    public BrandWall(String id, String brandName, String brandLogoUrl, String eventId) {
        this.id = id;
        this.brandName = brandName;
        this.brandLogoUrl = brandLogoUrl;
        this.eventId = eventId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandLogoUrl() {
        return brandLogoUrl;
    }

    public void setBrandLogoUrl(String brandLogoUrl) {
        this.brandLogoUrl = brandLogoUrl;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
}
