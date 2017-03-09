package com.qf.meilihui.bean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by invoker on 2017/3/6.
 */

public class BrandBean implements Serializable {

    private String siloName;
    private String siloEn;
    private List<Brand> items;
    private String siloId;

    public String getSiloId() {
        return siloId;
    }

    public String getSiloName() {
        return siloName;
    }

    public String getSiloEn() {
        return siloEn;
    }

    public List<Brand> getItems() {
        return items;
    }

    public void parseJson(JSONObject object) {
        items = new ArrayList<>();
        if (object != null) {
            try {
                siloName = object.getString("siloName");
                siloEn = object.getString("siloEn");
                siloId = object.getString("siloId");
                JSONArray array = object.getJSONArray("brandLogo");

                int col = array.length() / 3;
                int length = col * 3 - 1;
                for (int i = 0; i < length; i++) {
                    JSONObject json = array.getJSONObject(i);
                    Brand brand = new Brand();
                    brand.parseJson(json);
                    items.add(brand);
                }

            } catch (Exception e) {

            }
        }


    }

}
