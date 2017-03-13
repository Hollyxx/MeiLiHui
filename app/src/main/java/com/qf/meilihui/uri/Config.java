package com.qf.meilihui.uri;

/**
 * Created by 肖 on 2017/3/1.
 */

public class Config {
    //今日热门内容

    public static final String TODAY_CONTENT = "http://www.mei.com/appapi/home/event/v3?summary=0720346859018fb21dac9f2ff05e054f&timeStamp=20170228213558&pageIndex=1";

    public static final String TODAY_HEADVIEW = "http://www.mei.com/appapi/home/marketingBannerNew/v3?summary=ecabd4072745241950c2d5141497976d&timeStamp=20170228213547";
    public static final String TODAY_WOMEN = "http://www.mei.com/appapi/silo/event/v3?summary=6ba1fc7758ba52987ad25312a98c8559&timeStamp=20170228213558&pageIndex=1&categoryId=2013000100000000001";

    public static final String TODAY_MEN = "http://www.mei.com/appapi/silo/event/v3?summary=699a94983d328867cf3e7214d35f0fe1&timeStamp=20170228213558&pageIndex=1&categoryId=2013000100000000002";

    public static final String TODAY_FURNISHING = "http://www.mei.com/appapi/silo/event/v3?summary=2db074c5e0d27bbbee4a3ed4b5db6115&timeStamp=20170228220347&pageIndex=1&categoryId=2013000100000000004";

    public static final String TODAY_BABY = "http://www.mei.com/appapi/silo/event/v3?summary=7be7294fe5663e693ad83a0d4037e54f&timeStamp=20170228220347&pageIndex=1&categoryId=2013000100000000005";

    public static final String TODAY_NEW = "http://www.mei.com/appapi/upcoming/index/v3?summary=814b9a24bd8c6891c25767087ed5d835&timeStamp=20170228220347&userId=";

    public static final String MATCH = "http://www.mei.com/appapi/eventdays/list/v3?summary=0720346859018fb21dac9f2ff05e054f&timeStamp=20170228213558&pageIndex=1";

    public static final String TODAY_BEAUTIFUL = "http://www.mei.com/appapi/silo/event/v3?summary=3db30a9882c84a440d26b93afae78f92&timeStamp=20170228220347&pageIndex=1&categoryId=2013000100000000003";

    public static final String TODAY_BEAU_HEADVIEW = "http://www.mei.com/appapi/home/marketingBannerNew/v3?summary=fa6360109874208c2b2c03e5fc480020&timeStamp=20170228213558&silo_code=3";

    public static final String TODAY_BEAU_SCROLLVIEW = "http://www.mei.com/appapi/beauty/todaySale/v3?summary=814b9a24bd8c6891c25767087ed5d835&timeStamp=20170228220347";

    //分类页面的类别一级接口

    public static final String Category_Kinds = "http://www.mei.com/appapi/secondcategory/list/v3?summary=814b9a24bd8c6891c25767087ed5d835&timeStamp=20170228220347";

    //分类页面的品牌一级接口

    public static final String Category_Brand = "http://www.mei.com/appapi/brand/list/v3?summary=814b9a24bd8c6891c25767087ed5d835&timeStamp=20170228220347";

    public static final String TODAY_BEAU_IM = "http://www.mei.com/appapi/beauty/getStarAndOverSeaPic/v3?summary=814b9a24bd8c6891c25767087ed5d835&timeStamp=20170228220347&credential=";

    public static final String TODAY_BEAU_GRIDVIEW = "http://www.mei.com/appapi/beauty/beautyChannelBrands/v3?summary=814b9a24bd8c6891c25767087ed5d835&timeStamp=20170228220347";

    public static final String TODAY_SECOND_CONTENT = "http://www.mei.com/appapi/event/product/v3?sort=&categoryId=";
    public static final String TODAY_THIRD_CONTENT = "http://www.mei.com/appapi/product/detail/v3?&productId=";

    //分类页面的类别二级页面      需要拼接的属性siloId、categoryId  displayName、pageIndex
    //http://www.mei.com/appapi/secondcategory/product/v3?siloId=2013000100000000001&categoryId=1000000337&summary=男士&pageIndex=1
    public static final String Category_Second_Kinds = "http://www.mei.com/appapi/secondcategory/product/v3?";

    //分类的品牌页面的二级页面  需要拼接的属性 logoId
    public static final String Category_Second_Brand = "http://www.mei.com/appapi/brand/product/v3?logoId=";

    //详情页的推荐
    public static final String Hot_recommendation = "http://www.mei.com/appapi/product/hot/v3?&productId=";

    //品牌详情页面内部的热门商品
    public static final String Category_Second_Brand_Detail_HotProducts = "http://www.mei.com/appapi/brand/product/hot/v3?logoId=";

    //所有的品牌页面详情 拼接属性 siloId
    //http://www.mei.com/appapi/brand/more/v3?siloId=2013000100000000001
    public static final String All_Brand="http://www.mei.com/appapi/brand/more/v3?siloId=";

    //分类页面商品列表折扣排序  拼接的属性为 siloId  sort   key    pageIndex   categoryId
    //http://www.mei.com/appapi/secondcategory/product/v3?summary=756217611843dba127c0ebb1fee51a9b&siloId=2013000100000000001&sort=ASC&timeStamp=20170310182125&key=1&pageIndex=1&categoryId=1000000337
    public static final String Kinds_Products_Sort_Discount = "http://www.mei.com/appapi/secondcategory/product/v3?siloId=";

    //海外直销二级页面跳转 http://www.mei.com/appapi/event/product/v3?&categoryId=2040204090000002556&pageIndex=1
    public static final String Home_Second_products="http://www.mei.com/appapi/event/product/v3?&categoryId=";

}
