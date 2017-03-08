package com.qf.meilihui.bean;

import java.util.List;

/**
 * Created by invoker on 2017/3/8.
 */

public class BrandDetailBean {

    private String errorNum;
    private String errorInfo;
    private BodyBrandDetailBean body;

    public String getErrorNum() {
        return errorNum;
    }

    public void setErrorNum(String errorNum) {
        this.errorNum = errorNum;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public BodyBrandDetailBean getBody() {
        return body;
    }

    public void setBody(BodyBrandDetailBean body) {
        this.body = body;
    }

    public static class BodyBrandDetailBean {
        /**
         * brandDetail : {"logoId":"3616200100000001365","brandName":"LA PERLA","logoUrl":"http://cdn13.mei.com/category/20160615/20160615155236005.jpg","brandPageImage":"http://cdn13.mei.com/category/20160823/20160823105453121.jpg","brandDependency":"","brandStoryText":"洋溢古老人文气息而又华丽不羁的意大利，是无数女人梦想中的诱惑天堂。世界顶级意大利内衣品牌LA PERLA正是出自于这样一个艺术肆意流淌，震慑人心的人文艺术之都。1954 年，LA PERLA 创始人Ada Masotti 在高级面料制造商的中心\u2014博洛尼亚开了一家小小的胸衣店，名称满怀希望，叫 LA PERLA。顶级的手工艺艺术，重视设计环节，透彻了解女性身体，融合创新与传统，带来意大利特色与国际化定位，遵守整体品质准则。这些被视为 LA PERLA 的精髓，它们也是创始人 Ada Masotti 女士真正的传承。她凭借在式样方面的丰富经验，教导助手们通过在女性身体上不断试穿的方法来创造胸衣。如今，所有新款式的设计仍然采用这种方式来完成，它继续显示了 Ada Masotti 视为真正品质象征的东西\u2014\u2014对每个细节倾注的巨大热情。之后的时间中，LA PERLA不断创造出新的超前系列，但同时也疲于过多的改变而渴望永恒、回归的经典。如今，LA PERLA已然成为女人们最理想的内衣顾问，衣橱中的最终梦想。","followed":"","bazzar":""}
         * newProduct : [{"eventId":"2042204290000001934","eventName":"尊贵奢华内衣","product":[{"productId":"2042204299000327258","glsCode":"LA0-121-03466","productName":"FELINE CHIC系列黑色抹胸式文胸","eventId":"2042204290000001934","brandName":"LA PERLA","discount":"4.0","price":"1340","marketPrice":"3350","purchaserImg":"","purchaserName":"","isRecommended":"1","recommended":"","isTopSalesProduct":"0","isExistImg":"1","fileUrl":"http://cdn15.mei.com/product/LA0-121-03466/LA0-121-03466a.jpg@300w_400h_2e_75q","isHaveStock":"1","productStock":"4","stockQty":"4","isNewProduct":"0","purchaseType":"C-Consignment","isCrossBorder":"0"},{"productId":"2016201699000030585","glsCode":"LA0-121-02207","productName":"GraphiqueCouture系列白色条纹透视3/4杯钢圈文胸","eventId":"2042204290000001934","brandName":"LA PERLA","discount":"3.0","price":"525","marketPrice":"1750","purchaserImg":"","purchaserName":"","isRecommended":"1","recommended":"","isTopSalesProduct":"0","isExistImg":"1","fileUrl":"http://cdn15.mei.com/product/LA0-121-02208/LA0-121-02208a.jpg@300w_400h_2e_75q","isHaveStock":"1","productStock":"20","stockQty":"20","isNewProduct":"0","purchaseType":"C-Consignment","isCrossBorder":"0"},{"productId":"2041204199000280548","glsCode":"LA0-121-03304","productName":"BEGONIA系列黑色蕾丝边3/4杯文胸","eventId":"2042204290000001934","brandName":"LA PERLA","discount":"3.0","price":"375","marketPrice":"1250","purchaserImg":"","purchaserName":"","isRecommended":"1","recommended":"","isTopSalesProduct":"0","isExistImg":"1","fileUrl":"http://cdn15.mei.com/product/LA0-121-03304/LA0-121-03304a.jpg@300w_400h_2e_75q","isHaveStock":"1","productStock":"3","stockQty":"3","isNewProduct":"0","purchaseType":"C-Consignment","isCrossBorder":"0"},{"productId":"2042204299000327221","glsCode":"LA0-121-03429","productName":"PRIMULA系列白色蕾丝双肩带3/4杯文胸","eventId":"2042204290000001934","brandName":"LA PERLA","discount":"3.0","price":"345","marketPrice":"1150","purchaserImg":"","purchaserName":"","isRecommended":"1","recommended":"","isTopSalesProduct":"0","isExistImg":"1","fileUrl":"http://cdn15.mei.com/product/LA0-121-03429/LA0-121-03429a.jpg@300w_400h_2e_75q","isHaveStock":"1","productStock":"1","stockQty":"1","isNewProduct":"0","purchaseType":"C-Consignment","isCrossBorder":"0"},{"productId":"1250977","glsCode":"LA0-121-01887","productName":"ShapeCouture系列黑色透视镂空吊带连体裤","eventId":"2042204290000001934","brandName":"LA PERLA","discount":"4.0","price":"1575","marketPrice":"3938","purchaserImg":"","purchaserName":"","isRecommended":"0","recommended":"","isTopSalesProduct":"0","isExistImg":"1","fileUrl":"http://cdn13.mei.com/product/LA0-121-01887/LA0-121-01887a.jpg@300w_400h_2e_75q","isHaveStock":"1","productStock":"3","stockQty":"3","isNewProduct":"0","purchaseType":"C-Consignment","isCrossBorder":"0"},{"productId":"2041204199000007264","glsCode":"LA0-121-02576","productName":"TulleSoutache系列黑色网纹条纹饰3/4杯钢圈文胸","eventId":"2042204290000001934","brandName":"LA PERLA","discount":"3.0","price":"915","marketPrice":"3050","purchaserImg":"","purchaserName":"","isRecommended":"0","recommended":"","isTopSalesProduct":"0","isExistImg":"0","fileUrl":"http://cdn15.mei.com/product/LA0-121-02577/LA0-121-02577a.jpg@300w_400h_2e_75q","isHaveStock":"1","productStock":"4","stockQty":"4","isNewProduct":"0","purchaseType":"C-Consignment","isCrossBorder":"0"},{"productId":"2042204299000184275","glsCode":"LA0-121-02968","productName":"CLIO系列黑色蕾丝花纹3/4杯无插垫有钢圈文胸","eventId":"2042204290000001934","brandName":"LA PERLA","discount":"5.0","price":"575","marketPrice":"1150","purchaserImg":"","purchaserName":"","isRecommended":"0","recommended":"","isTopSalesProduct":"0","isExistImg":"1","fileUrl":"http://cdn13.mei.com/product/20161130/20161130100832858.jpg@300w_400h_2e_75q","isHaveStock":"1","productStock":"1","stockQty":"1","isNewProduct":"0","purchaseType":"C-Consignment","isCrossBorder":"0"},{"productId":"1230123099900007109","glsCode":"LA0-121-02440","productName":"GraphiqueCouture系列黑色条饰3/4杯钢圈文胸","eventId":"2042204290000001934","brandName":"LA PERLA","discount":"3.0","price":"615","marketPrice":"2050","purchaserImg":"","purchaserName":"","isRecommended":"0","recommended":"","isTopSalesProduct":"0","isExistImg":"1","fileUrl":"http://cdn15.mei.com/product/LA0-121-02442/LA0-121-02442a.jpg@300w_400h_2e_75q","isHaveStock":"1","productStock":"12","stockQty":"12","isNewProduct":"0","purchaseType":"C-Consignment","isCrossBorder":"0"},{"productId":"2041204199000263291","glsCode":"LA0-121-03234","productName":"GRETA系列黑色花边3/4杯文胸","eventId":"2042204290000001934","brandName":"LA PERLA","discount":"3.0","price":"525","marketPrice":"1750","purchaserImg":"","purchaserName":"","isRecommended":"0","recommended":"","isTopSalesProduct":"0","isExistImg":"1","fileUrl":"http://cdn15.mei.com/product/LA0-121-03234/LA0-121-03234a.jpg@300w_400h_2e_75q","isHaveStock":"1","productStock":"2","stockQty":"2","isNewProduct":"0","purchaseType":"C-Consignment","isCrossBorder":"0"},{"productId":"2041204199000007242","glsCode":"LA0-121-02565","productName":"TulleSoutache系列深咖色网纹条纹饰1/2杯钢圈文胸","eventId":"2042204290000001934","brandName":"LA PERLA","discount":"3.0","price":"1080","marketPrice":"3600","purchaserImg":"","purchaserName":"","isRecommended":"0","recommended":"","isTopSalesProduct":"0","isExistImg":"1","fileUrl":"http://cdn13.mei.com/product/20170301/20170301142642220.jpg@300w_400h_2e_75q","isHaveStock":"1","productStock":"21","stockQty":"21","isNewProduct":"0","purchaseType":"C-Consignment","isCrossBorder":"0"}]}]
         * upcomingProduct : []
         */

        private BrandDetailBrandDetailBean brandDetail;
        private List<NewProductBrandDetailBean> newProduct;
        private List<?> upcomingProduct;

        public BrandDetailBrandDetailBean getBrandDetail() {
            return brandDetail;
        }

        public void setBrandDetail(BrandDetailBrandDetailBean brandDetail) {
            this.brandDetail = brandDetail;
        }

        public List<NewProductBrandDetailBean> getNewProduct() {
            return newProduct;
        }

        public void setNewProduct(List<NewProductBrandDetailBean> newProduct) {
            this.newProduct = newProduct;
        }

        public List<?> getUpcomingProduct() {
            return upcomingProduct;
        }

        public void setUpcomingProduct(List<?> upcomingProduct) {
            this.upcomingProduct = upcomingProduct;
        }

        public static class BrandDetailBrandDetailBean {
            /**
             * logoId : 3616200100000001365
             * brandName : LA PERLA
             * logoUrl : http://cdn13.mei.com/category/20160615/20160615155236005.jpg
             * brandPageImage : http://cdn13.mei.com/category/20160823/20160823105453121.jpg
             * brandDependency :
             * brandStoryText : 洋溢古老人文气息而又华丽不羁的意大利，是无数女人梦想中的诱惑天堂。世界顶级意大利内衣品牌LA PERLA正是出自于这样一个艺术肆意流淌，震慑人心的人文艺术之都。1954 年，LA PERLA 创始人Ada Masotti 在高级面料制造商的中心—博洛尼亚开了一家小小的胸衣店，名称满怀希望，叫 LA PERLA。顶级的手工艺艺术，重视设计环节，透彻了解女性身体，融合创新与传统，带来意大利特色与国际化定位，遵守整体品质准则。这些被视为 LA PERLA 的精髓，它们也是创始人 Ada Masotti 女士真正的传承。她凭借在式样方面的丰富经验，教导助手们通过在女性身体上不断试穿的方法来创造胸衣。如今，所有新款式的设计仍然采用这种方式来完成，它继续显示了 Ada Masotti 视为真正品质象征的东西——对每个细节倾注的巨大热情。之后的时间中，LA PERLA不断创造出新的超前系列，但同时也疲于过多的改变而渴望永恒、回归的经典。如今，LA PERLA已然成为女人们最理想的内衣顾问，衣橱中的最终梦想。
             * followed :
             * bazzar :
             */

            private String logoId;
            private String brandName;
            private String logoUrl;
            private String brandPageImage;
            private String brandDependency;
            private String brandStoryText;
            private String followed;
            private String bazzar;

            public String getLogoId() {
                return logoId;
            }

            public void setLogoId(String logoId) {
                this.logoId = logoId;
            }

            public String getBrandName() {
                return brandName;
            }

            public void setBrandName(String brandName) {
                this.brandName = brandName;
            }

            public String getLogoUrl() {
                return logoUrl;
            }

            public void setLogoUrl(String logoUrl) {
                this.logoUrl = logoUrl;
            }

            public String getBrandPageImage() {
                return brandPageImage;
            }

            public void setBrandPageImage(String brandPageImage) {
                this.brandPageImage = brandPageImage;
            }

            public String getBrandDependency() {
                return brandDependency;
            }

            public void setBrandDependency(String brandDependency) {
                this.brandDependency = brandDependency;
            }

            public String getBrandStoryText() {
                return brandStoryText;
            }

            public void setBrandStoryText(String brandStoryText) {
                this.brandStoryText = brandStoryText;
            }

            public String getFollowed() {
                return followed;
            }

            public void setFollowed(String followed) {
                this.followed = followed;
            }

            public String getBazzar() {
                return bazzar;
            }

            public void setBazzar(String bazzar) {
                this.bazzar = bazzar;
            }
        }

        public static class NewProductBrandDetailBean {
            /**
             * eventId : 2042204290000001934
             * eventName : 尊贵奢华内衣
             * product : [{"productId":"2042204299000327258","glsCode":"LA0-121-03466","productName":"FELINE CHIC系列黑色抹胸式文胸","eventId":"2042204290000001934","brandName":"LA PERLA","discount":"4.0","price":"1340","marketPrice":"3350","purchaserImg":"","purchaserName":"","isRecommended":"1","recommended":"","isTopSalesProduct":"0","isExistImg":"1","fileUrl":"http://cdn15.mei.com/product/LA0-121-03466/LA0-121-03466a.jpg@300w_400h_2e_75q","isHaveStock":"1","productStock":"4","stockQty":"4","isNewProduct":"0","purchaseType":"C-Consignment","isCrossBorder":"0"},{"productId":"2016201699000030585","glsCode":"LA0-121-02207","productName":"GraphiqueCouture系列白色条纹透视3/4杯钢圈文胸","eventId":"2042204290000001934","brandName":"LA PERLA","discount":"3.0","price":"525","marketPrice":"1750","purchaserImg":"","purchaserName":"","isRecommended":"1","recommended":"","isTopSalesProduct":"0","isExistImg":"1","fileUrl":"http://cdn15.mei.com/product/LA0-121-02208/LA0-121-02208a.jpg@300w_400h_2e_75q","isHaveStock":"1","productStock":"20","stockQty":"20","isNewProduct":"0","purchaseType":"C-Consignment","isCrossBorder":"0"},{"productId":"2041204199000280548","glsCode":"LA0-121-03304","productName":"BEGONIA系列黑色蕾丝边3/4杯文胸","eventId":"2042204290000001934","brandName":"LA PERLA","discount":"3.0","price":"375","marketPrice":"1250","purchaserImg":"","purchaserName":"","isRecommended":"1","recommended":"","isTopSalesProduct":"0","isExistImg":"1","fileUrl":"http://cdn15.mei.com/product/LA0-121-03304/LA0-121-03304a.jpg@300w_400h_2e_75q","isHaveStock":"1","productStock":"3","stockQty":"3","isNewProduct":"0","purchaseType":"C-Consignment","isCrossBorder":"0"},{"productId":"2042204299000327221","glsCode":"LA0-121-03429","productName":"PRIMULA系列白色蕾丝双肩带3/4杯文胸","eventId":"2042204290000001934","brandName":"LA PERLA","discount":"3.0","price":"345","marketPrice":"1150","purchaserImg":"","purchaserName":"","isRecommended":"1","recommended":"","isTopSalesProduct":"0","isExistImg":"1","fileUrl":"http://cdn15.mei.com/product/LA0-121-03429/LA0-121-03429a.jpg@300w_400h_2e_75q","isHaveStock":"1","productStock":"1","stockQty":"1","isNewProduct":"0","purchaseType":"C-Consignment","isCrossBorder":"0"},{"productId":"1250977","glsCode":"LA0-121-01887","productName":"ShapeCouture系列黑色透视镂空吊带连体裤","eventId":"2042204290000001934","brandName":"LA PERLA","discount":"4.0","price":"1575","marketPrice":"3938","purchaserImg":"","purchaserName":"","isRecommended":"0","recommended":"","isTopSalesProduct":"0","isExistImg":"1","fileUrl":"http://cdn13.mei.com/product/LA0-121-01887/LA0-121-01887a.jpg@300w_400h_2e_75q","isHaveStock":"1","productStock":"3","stockQty":"3","isNewProduct":"0","purchaseType":"C-Consignment","isCrossBorder":"0"},{"productId":"2041204199000007264","glsCode":"LA0-121-02576","productName":"TulleSoutache系列黑色网纹条纹饰3/4杯钢圈文胸","eventId":"2042204290000001934","brandName":"LA PERLA","discount":"3.0","price":"915","marketPrice":"3050","purchaserImg":"","purchaserName":"","isRecommended":"0","recommended":"","isTopSalesProduct":"0","isExistImg":"0","fileUrl":"http://cdn15.mei.com/product/LA0-121-02577/LA0-121-02577a.jpg@300w_400h_2e_75q","isHaveStock":"1","productStock":"4","stockQty":"4","isNewProduct":"0","purchaseType":"C-Consignment","isCrossBorder":"0"},{"productId":"2042204299000184275","glsCode":"LA0-121-02968","productName":"CLIO系列黑色蕾丝花纹3/4杯无插垫有钢圈文胸","eventId":"2042204290000001934","brandName":"LA PERLA","discount":"5.0","price":"575","marketPrice":"1150","purchaserImg":"","purchaserName":"","isRecommended":"0","recommended":"","isTopSalesProduct":"0","isExistImg":"1","fileUrl":"http://cdn13.mei.com/product/20161130/20161130100832858.jpg@300w_400h_2e_75q","isHaveStock":"1","productStock":"1","stockQty":"1","isNewProduct":"0","purchaseType":"C-Consignment","isCrossBorder":"0"},{"productId":"1230123099900007109","glsCode":"LA0-121-02440","productName":"GraphiqueCouture系列黑色条饰3/4杯钢圈文胸","eventId":"2042204290000001934","brandName":"LA PERLA","discount":"3.0","price":"615","marketPrice":"2050","purchaserImg":"","purchaserName":"","isRecommended":"0","recommended":"","isTopSalesProduct":"0","isExistImg":"1","fileUrl":"http://cdn15.mei.com/product/LA0-121-02442/LA0-121-02442a.jpg@300w_400h_2e_75q","isHaveStock":"1","productStock":"12","stockQty":"12","isNewProduct":"0","purchaseType":"C-Consignment","isCrossBorder":"0"},{"productId":"2041204199000263291","glsCode":"LA0-121-03234","productName":"GRETA系列黑色花边3/4杯文胸","eventId":"2042204290000001934","brandName":"LA PERLA","discount":"3.0","price":"525","marketPrice":"1750","purchaserImg":"","purchaserName":"","isRecommended":"0","recommended":"","isTopSalesProduct":"0","isExistImg":"1","fileUrl":"http://cdn15.mei.com/product/LA0-121-03234/LA0-121-03234a.jpg@300w_400h_2e_75q","isHaveStock":"1","productStock":"2","stockQty":"2","isNewProduct":"0","purchaseType":"C-Consignment","isCrossBorder":"0"},{"productId":"2041204199000007242","glsCode":"LA0-121-02565","productName":"TulleSoutache系列深咖色网纹条纹饰1/2杯钢圈文胸","eventId":"2042204290000001934","brandName":"LA PERLA","discount":"3.0","price":"1080","marketPrice":"3600","purchaserImg":"","purchaserName":"","isRecommended":"0","recommended":"","isTopSalesProduct":"0","isExistImg":"1","fileUrl":"http://cdn13.mei.com/product/20170301/20170301142642220.jpg@300w_400h_2e_75q","isHaveStock":"1","productStock":"21","stockQty":"21","isNewProduct":"0","purchaseType":"C-Consignment","isCrossBorder":"0"}]
             */

            private String eventId;
            private String eventName;
            private List<ProductBrandDetailBean> product;

            public String getEventId() {
                return eventId;
            }

            public void setEventId(String eventId) {
                this.eventId = eventId;
            }

            public String getEventName() {
                return eventName;
            }

            public void setEventName(String eventName) {
                this.eventName = eventName;
            }

            public List<ProductBrandDetailBean> getProduct() {
                return product;
            }

            public void setProduct(List<ProductBrandDetailBean> product) {
                this.product = product;
            }

            public static class ProductBrandDetailBean {
                /**
                 * productId : 2042204299000327258
                 * glsCode : LA0-121-03466
                 * productName : FELINE CHIC系列黑色抹胸式文胸
                 * eventId : 2042204290000001934
                 * brandName : LA PERLA
                 * discount : 4.0
                 * price : 1340
                 * marketPrice : 3350
                 * purchaserImg :
                 * purchaserName :
                 * isRecommended : 1
                 * recommended :
                 * isTopSalesProduct : 0
                 * isExistImg : 1
                 * fileUrl : http://cdn15.mei.com/product/LA0-121-03466/LA0-121-03466a.jpg@300w_400h_2e_75q
                 * isHaveStock : 1
                 * productStock : 4
                 * stockQty : 4
                 * isNewProduct : 0
                 * purchaseType : C-Consignment
                 * isCrossBorder : 0
                 */

                private String productId;
                private String glsCode;
                private String productName;
                private String eventId;
                private String brandName;
                private String discount;
                private String price;
                private String marketPrice;
                private String purchaserImg;
                private String purchaserName;
                private String isRecommended;
                private String recommended;
                private String isTopSalesProduct;
                private String isExistImg;
                private String fileUrl;
                private String isHaveStock;
                private String productStock;
                private String stockQty;
                private String isNewProduct;
                private String purchaseType;
                private String isCrossBorder;

                public String getProductId() {
                    return productId;
                }

                public void setProductId(String productId) {
                    this.productId = productId;
                }

                public String getGlsCode() {
                    return glsCode;
                }

                public void setGlsCode(String glsCode) {
                    this.glsCode = glsCode;
                }

                public String getProductName() {
                    return productName;
                }

                public void setProductName(String productName) {
                    this.productName = productName;
                }

                public String getEventId() {
                    return eventId;
                }

                public void setEventId(String eventId) {
                    this.eventId = eventId;
                }

                public String getBrandName() {
                    return brandName;
                }

                public void setBrandName(String brandName) {
                    this.brandName = brandName;
                }

                public String getDiscount() {
                    return discount;
                }

                public void setDiscount(String discount) {
                    this.discount = discount;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getMarketPrice() {
                    return marketPrice;
                }

                public void setMarketPrice(String marketPrice) {
                    this.marketPrice = marketPrice;
                }

                public String getPurchaserImg() {
                    return purchaserImg;
                }

                public void setPurchaserImg(String purchaserImg) {
                    this.purchaserImg = purchaserImg;
                }

                public String getPurchaserName() {
                    return purchaserName;
                }

                public void setPurchaserName(String purchaserName) {
                    this.purchaserName = purchaserName;
                }

                public String getIsRecommended() {
                    return isRecommended;
                }

                public void setIsRecommended(String isRecommended) {
                    this.isRecommended = isRecommended;
                }

                public String getRecommended() {
                    return recommended;
                }

                public void setRecommended(String recommended) {
                    this.recommended = recommended;
                }

                public String getIsTopSalesProduct() {
                    return isTopSalesProduct;
                }

                public void setIsTopSalesProduct(String isTopSalesProduct) {
                    this.isTopSalesProduct = isTopSalesProduct;
                }

                public String getIsExistImg() {
                    return isExistImg;
                }

                public void setIsExistImg(String isExistImg) {
                    this.isExistImg = isExistImg;
                }

                public String getFileUrl() {
                    return fileUrl;
                }

                public void setFileUrl(String fileUrl) {
                    this.fileUrl = fileUrl;
                }

                public String getIsHaveStock() {
                    return isHaveStock;
                }

                public void setIsHaveStock(String isHaveStock) {
                    this.isHaveStock = isHaveStock;
                }

                public String getProductStock() {
                    return productStock;
                }

                public void setProductStock(String productStock) {
                    this.productStock = productStock;
                }

                public String getStockQty() {
                    return stockQty;
                }

                public void setStockQty(String stockQty) {
                    this.stockQty = stockQty;
                }

                public String getIsNewProduct() {
                    return isNewProduct;
                }

                public void setIsNewProduct(String isNewProduct) {
                    this.isNewProduct = isNewProduct;
                }

                public String getPurchaseType() {
                    return purchaseType;
                }

                public void setPurchaseType(String purchaseType) {
                    this.purchaseType = purchaseType;
                }

                public String getIsCrossBorder() {
                    return isCrossBorder;
                }

                public void setIsCrossBorder(String isCrossBorder) {
                    this.isCrossBorder = isCrossBorder;
                }
            }
        }
    }

}
