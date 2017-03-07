package com.qf.meilihui.bean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by invoker on 2017/3/6.
 */

public class CategoryBean implements Serializable {

    private String siloNameEn;
    private String displayName;
    private List<Category> items;
    private String id;

    public String getId() {
        return id;
    }

    public String getSiloNameEn() {
        return siloNameEn;
    }

    public String getDisplayName() {
        return displayName;
    }

    public List<Category> getItems() {
        return items;
    }

    public void parseJson(JSONObject object) {
        items = new ArrayList<>();
        if (object != null) {
            try {
                siloNameEn = object.getString("siloNameEn");
                displayName = object.getString("displayName");
                id = object.getString("id");
                JSONArray array = object.getJSONArray("secondCategories");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject json = array.getJSONObject(i);
                    Category category = new Category();
                    category.parseJson(json);
                    items.add(category);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


}
