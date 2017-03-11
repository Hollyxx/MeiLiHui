package com.qf.meilihui.bean;

import java.util.List;

/**
 * Created by 肖 on 2017/3/11.
 */

public class OverSeaEvent {

    private String id;
    private String code;
    private String name;
    private String englishName;
    private String imgUrl;
    private String discount;
    private List<OverSeaProductList>  data;

    public OverSeaEvent(String id, String code, String name, String englishName, String imgUrl, String discount, List<OverSeaProductList> data) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.englishName = englishName;
        this.imgUrl = imgUrl;
        this.discount = discount;
        this.data = data;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public List<OverSeaProductList> getData() {
        return data;
    }

    public void setData(List<OverSeaProductList> data) {
        this.data = data;
    }
}
