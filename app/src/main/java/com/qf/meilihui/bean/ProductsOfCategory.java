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
            } catch (Exception e) {

            }
        }
    }


}
