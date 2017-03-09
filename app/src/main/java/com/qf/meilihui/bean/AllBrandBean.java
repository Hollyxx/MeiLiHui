package com.qf.meilihui.bean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by invoker on 2017/3/9.
 */

public class AllBrandBean {
    private List<Brand> items;

    public List<Brand> getItems() {
        return items;
    }

    public void parseJson(JSONObject object) {
        if (object != null) {
            items = new ArrayList<>();
            try {
                JSONObject body = object.getJSONObject("body");
                JSONArray array = body.getJSONArray("brandList");
                int length = array.length();
                for (int i = 0; i < length; i++) {
                    JSONObject json = array.getJSONObject(i);
                    Brand brand = new Brand();
                    brand.parseJson(json);
                    items.add(brand);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
