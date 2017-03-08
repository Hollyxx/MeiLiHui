package com.qf.meilihui.bean;

/**
 * Created by 肖 on 2017/3/8.
 */

public class HotBean {

    private String productId ;
    private String imgUrl;
    private String product_name;
    private String brand_name;
    private String market_price;
    private String price;
    private String eventId;

    public HotBean(String productId, String imgUrl, String product_name, String brand_name, String market_price, String price, String eventId) {
        this.productId = productId;
        this.imgUrl = imgUrl;
        this.product_name = product_name;
        this.brand_name = brand_name;
        this.market_price = market_price;
        this.price = price;
        this.eventId = eventId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getMarket_price() {
        return market_price;
    }

    public void setMarket_price(String market_price) {
        this.market_price = market_price;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
}
