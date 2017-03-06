package com.qf.meilihui.bean;

import org.json.JSONObject;

/**
 * Created by invoker on 2017/3/6.
 */

public class Brand {

    private String logoId;
    private String brandName;
    private String logoUrl;

    public String getLogoId() {
        return logoId;
    }

    public String getBrandName() {
        return brandName;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void parseJson(JSONObject object) {
        if (object != null) {
            try {
                logoId = object.getString("logoId");
                brandName = object.getString("brandName");
                logoUrl = object.getString("logoUrl");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
