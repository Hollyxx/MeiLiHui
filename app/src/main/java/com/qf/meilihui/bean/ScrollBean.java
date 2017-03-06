package com.qf.meilihui.bean;

/**
 * Created by 肖 on 2017/3/5.
 */

public class ScrollBean {
    private String   eventId;
    private String   productId;
    private String   brandName;
    private String   productName;
    private String   price;
    private String   picUrl;
    private String   glsCode;

    public ScrollBean(String eventId, String productId, String brandName, String productName, String price, String picUrl, String glsCode) {
        this.eventId = eventId;
        this.productId = productId;
        this.brandName = brandName;
        this.productName = productName;
        this.price = price;
        this.picUrl = picUrl;
        this.glsCode = glsCode;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getGlsCode() {
        return glsCode;
    }

    public void setGlsCode(String glsCode) {
        this.glsCode = glsCode;
    }
}
