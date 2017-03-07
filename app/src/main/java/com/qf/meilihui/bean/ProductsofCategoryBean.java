package com.qf.meilihui.bean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by invoker on 2017/3/7.
 */

public class ProductsofCategoryBean {

    private String brandsSize;
    private String isFullprice;
    private int totalPages;
    private List<ProductsOfCategory> items;
    private shareContent content;


    public String getBrandsSize() {
        return brandsSize;
    }

    public String getIsFullprice() {
        return isFullprice;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public List<ProductsOfCategory> getItems() {
        return items;
    }

    public shareContent getContent() {
        return content;
    }

    public void parseJson(JSONObject object) {
        items = new ArrayList<>();
        if (object != null) {
            try {
                brandsSize = object.getString("brandsSize");
                isFullprice = object.getString("isFullprice");
                totalPages = object.getInt("totalPages");
                content = new shareContent();
                content.parseJson(object.getJSONObject("shareContent"));
                JSONArray array = object.getJSONArray("products");
                int length = array.length();
                for (int i = 0; i < length; i++) {
                    ProductsOfCategory products = new ProductsOfCategory();
                    products.parseJson(array.getJSONObject(i));
                    items.add(products);
                }
            } catch (Exception e) {

            }
        }
    }


    /**
     * "shareInfo":"服装 更有新会员大礼！",
     * "shareTag":"@魅力惠GlamourSales",
     * "shareImage":"",
     * "shareLinkWx":"http://www.mei.com/",
     * "shareLinkWxcircle":"http://www.mei.com/",
     * "shareLinkWb":"http://www.mei.com/"
     */
    public class shareContent {
        private String shareInfo;
        private String shareTag;
        private String shareImage;
        private String shareLinkWx;
        private String shareLinkWxcircle;
        private String shareLinkWb;

        public void parseJson(JSONObject object) {
            if (object != null) {
                try {
                    shareInfo = object.getString("shareInfo");
                    shareTag = object.getString("shareTag");
                    shareImage = object.getString("shareImage");
                    shareLinkWx = object.getString("shareLinkWx");
                    shareLinkWxcircle = object.getString("shareLinkWxcircle");
                    shareLinkWb = object.getString("shareLinkWb");

                } catch (Exception e) {

                }
            }
        }

    }
}
