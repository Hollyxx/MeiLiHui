package com.qf.meilihui.bean;

/**
 * Created by 肖 on 2017/3/7.
 */

public class TodaySecondDetails {

    private  String productId ;
    private  String productName;
    private String product_type;
    private  String  brandName;
    private String  price;
    private  String  marketPrice;
    private  String  imageUrl;
    private String  discount;

    public TodaySecondDetails(String productId, String productName, String product_type, String brandName, String price, String marketPrice, String imageUrl, String discount) {
        this.productId = productId;
        this.productName = productName;
        this.product_type = product_type;
        this.brandName = brandName;
        this.price = price;
        this.marketPrice = marketPrice;
        this.imageUrl = imageUrl;
        this.discount = discount;
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

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }
}
