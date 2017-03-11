package com.qf.meilihui.bean;

/**
 * Created by 肖 on 2017/3/11.
 */

public class StarRecommendBean {

    private String id;
    private String eventCode;
    private String productId;
    private String eventId;
    private String describe;
    private String productImgUrl;
    private String brand;
    private String price;
    private String marketPrice;
    private String productName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getProductImgUrl() {
        return productImgUrl;
    }

    public void setProductImgUrl(String productImgUrl) {
        this.productImgUrl = productImgUrl;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(String marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public StarRecommendBean(String id, String eventCode, String productId, String describe, String productImgUrl, String brand, String price, String marketPrice, String productName, String eventId) {
        this.id = id;
        this.eventCode = eventCode;
        this.productId = productId;
        this.describe = describe;
        this.productImgUrl = productImgUrl;
        this.brand = brand;
        this.price = price;
        this.marketPrice = marketPrice;
        this.productName = productName;
        this.eventId = eventId;


    }
}
