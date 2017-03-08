package com.qf.meilihui.bean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by invoker on 2017/3/8.
 */

public class HotProductsOfBrandDetailBean {

    private String size;
    private String number;
    private String totalPages;
    private String numberOfElements;
    private String totalElements;
    private List<HotProductsOfBrandDetail> items;
    private String title;

    public void parseJson(JSONObject object) {
        if (object != null) {
            items = new ArrayList<>();
            try {
                title = "热卖商品";
                size = object.getString("size");
                number = object.getString("number");
                totalPages = object.getString("totalPages");
                numberOfElements = object.getString("numberOfElements");
                totalElements = object.getString("totalElements");
                JSONArray array = object.getJSONArray("content");
                for (int i = 0; i < array.length(); i++) {
                    HotProductsOfBrandDetail item = new HotProductsOfBrandDetail();
                    JSONObject json = array.getJSONObject(i);
                    item.parseJson(json);
                    items.add(item);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String getTitle() {
        return title;
    }

    public String getSize() {
        return size;
    }

    public String getNumber() {
        return number;
    }

    public String getTotalPages() {
        return totalPages;
    }

    public String getNumberOfElements() {
        return numberOfElements;
    }

    public String getTotalElements() {
        return totalElements;
    }

    public List<HotProductsOfBrandDetail> getItems() {
        return items;
    }
}
