package com.qf.meilihui.bean;

import org.json.JSONObject;

/**
 * Created by invoker on 2017/3/8.
 */

public class HotProductsOfBrandDetail {

    private String productId;
    private String productName;
    private String brandName;
    private String price;
    private String marketPrice;
    private String fileUrl;
    private String isRecommended;
    private String discount;
    private String stockQty;
    private String eventId;

    public void parseJson(JSONObject object) {
        if (object != null) {
            try {
                productId = object.getString("productId");
                productName = object.getString("productName");
                brandName = object.getString("brandName");
                price = object.getString("price");
                marketPrice = object.getString("marketPrice");
                fileUrl = object.getString("fileUrl");
                isRecommended = object.getString("isRecommended");
                discount = object.getString("discount");
                stockQty = object.getString("stockQty");
                eventId = object.getString("eventId");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public void setIsRecommended(String isRecommended) {
        this.isRecommended = isRecommended;
    }

    public void setStockQty(String stockQty) {
        this.stockQty = stockQty;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
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

    public String getFileUrl() {
        return fileUrl;
    }

    public String getIsRecommended() {
        return isRecommended;
    }

    public String getStockQty() {
        return stockQty;
    }

    public String getEventId() {
        return eventId;
    }
}
