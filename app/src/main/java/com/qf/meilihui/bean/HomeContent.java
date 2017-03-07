package com.qf.meilihui.bean;

/**
 * Created by 肖 on 2017/3/1.
 *  "englishName":"全球网红钟爱",
 "chineseName":"女士时尚 鞋包配饰",
 "discountText":"1.4折起",
 */

public class HomeContent {

    private  String  englishName;
    private  String  chineseName;
    private  String  imageUrl;
    private  String  discountText;
    private String  eventId;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDiscountText() {
        return discountText;
    }

    public void setDiscountText(String discountText) {
        this.discountText = discountText;
    }

    public HomeContent(String discountText, String imageUrl, String chineseName, String englishName) {
        this.discountText = discountText;
        this.imageUrl = imageUrl;
        this.chineseName = chineseName;
        this.englishName = englishName;
    }

    public HomeContent(String discountText, String imageUrl, String chineseName, String englishName,String eventId) {
        this.discountText = discountText;
        this.imageUrl = imageUrl;
        this.chineseName = chineseName;
        this.englishName = englishName;
        this.eventId = eventId;
    }
}
