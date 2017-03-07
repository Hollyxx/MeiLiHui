package com.qf.meilihui.bean;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by invoker on 2017/3/6.
 */

public class Category implements Serializable {

    private String categoryId;
    private String categoryName;
    private String categoryCode;
    private String categoryLogoUrl;

    public String getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public String getCategoryLogoUrl() {
        return categoryLogoUrl;
    }

    public void parseJson(JSONObject object) {
        if (object != null) {
            try {
                categoryId = object.getString("categoryId");
                categoryName = object.getString("categoryName");
                categoryCode = object.getString("categoryCode");
                categoryLogoUrl = object.getString("categoryLogoUrl");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
