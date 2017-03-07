package com.qf.meilihui.bean;

import org.json.JSONObject;

/**
 * Created by invoker on 2017/3/7.
 */

public class ProductsOfCategory {

    private String productId;
    private String productName;
    private String glsCode;
    private String product_type;
    private String brandName;
    private String price;
    private String marketPrice;
    private String imageUrl;
    private String stockStatus;
    private String isNewProduct;
    private String isCrossBorder;
    private String ev_url_key;
    private String event_id;
    private String isRecommend;
    private String saleableQty;

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getGlsCode() {
        return glsCode;
    }

    public String getProduct_type() {
        return product_type;
    }

    public String getBrandName() {
        return brandName;
    }

    public String getPrice() {
        return price;
    }

    public String getMarketPrice() {
        return marketPrice;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getIsRecommend() {
        return isRecommend;
    }

    public String getStockStatus() {
        return stockStatus;
    }

    public String getIsNewProduct() {
        return isNewProduct;
    }

    public String getIsCrossBorder() {
        return isCrossBorder;
    }

    public String getEv_url_key() {
        return ev_url_key;
    }

    public String getSaleableQty() {
        return saleableQty;
    }

    public String getEvent_id() {
        return event_id;
    }

    public void parseJson(JSONObject object) {
        if (object != null) {
            try {
                productId = object.getString("productId");
                productName = object.getString("productName");
                glsCode = object.getString("glsCode");
                product_type = object.getString("product_type");
                brandName = object.getString("brandName");
                price = object.getString("price");
                marketPrice = object.getString("marketPrice");
                imageUrl = object.getString("imageUrl");
                stockStatus = object.getString("stockStatus");
                isNewProduct = object.getString("isNewProduct");
                isCrossBorder = object.getString("isCrossBorder");
                ev_url_key = object.getString("ev_url_key");
                event_id = object.getString("event_id");
                isRecommend = object.getString("isRecommend");
                saleableQty = object.getString("saleableQty");
            } catch (Exception e) {

            }
        }
    }


}
