package com.qf.meilihui.bean;

/**
 * Created by 肖 on 2017/3/11.
 */

public class OverSeaProductList {
    private String  id;
    private String  eventId;
    private String productName;
    private String productImgUrl ;
    private String brand;
    private String  price;
    private String  marketPrice;
    private String discount;

    public OverSeaProductList(String id, String eventId, String productName, String productImgUrl, String brand, String price, String marketPrice) {
        this.id = id;
        this.eventId = eventId;
        this.productName = productName;
        this.productImgUrl = productImgUrl;
        this.brand = brand;
        this.price = price;
        this.marketPrice = marketPrice;
    }

    public OverSeaProductList(String id, String eventId, String productName, String productImgUrl, String brand, String price, String marketPrice, String discount) {
        this.id = id;
        this.eventId = eventId;
        this.productName = productName;
        this.productImgUrl = productImgUrl;
        this.brand = brand;
        this.price = price;
        this.marketPrice = marketPrice;
        this.discount = discount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }
}
