package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 首页
 * Created by WeiQingFeng on 2017/8/23.
 */

public class IndexBean extends NetResult {

    /**
     * data : {"banner":[{"id":5,"banner_image":"yzl.com\\public\\static\\base\\2017-06-15/59422840becb7.jpg"}],"category":[{"cate_id":2,"cate_name":"餐饮","logo":"yzl.com\\public\\static\\category\\20170822\\56bf084bc19606c5177486e183ff9859.png"}],"goods":[{"goods_id":382,"goods_name":"1112233","goods_image":"yzl.com\\public\\static\\goods\\20170821\\31cb23df16431e8a862e61e681ccab18.png","cost":"11.00"}],"store":[{"store_id":19,"store_name":"銘诚微商商贸","store_address":"常泰路附近","logo":"yzl.compublic\\static\\license\\"}]}
     */

    private List<DataBean> data;

    public static IndexBean parse(String json) throws AppException {
        IndexBean res = new IndexBean();
        try {
            res = gson.fromJson(json, IndexBean.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            throw AppException.json(e);
        }
        return res;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private List<BannerBean> banner;
        private List<CategoryBean> category;
        private List<GoodsBean> goods;
        private List<StoreBean> store;
        private List<NewsBean> news;
        private List<HotBean> hot;

        public List<HotBean> getHot() {
            return hot;
        }

        public void setHot(List<HotBean> hot) {
            this.hot = hot;
        }

        public List<NewsBean> getNews() {
            return news;
        }

        public void setNews(List<NewsBean> news) {
            this.news = news;
        }

        public List<BannerBean> getBanner() {
            return banner;
        }

        public void setBanner(List<BannerBean> banner) {
            this.banner = banner;
        }

        public List<CategoryBean> getCategory() {
            return category;
        }

        public void setCategory(List<CategoryBean> category) {
            this.category = category;
        }

        public List<GoodsBean> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsBean> goods) {
            this.goods = goods;
        }

        public List<StoreBean> getStore() {
            return store;
        }

        public void setStore(List<StoreBean> store) {
            this.store = store;
        }

        public static class BannerBean {
            /**
             * id : 5
             * banner_image : yzl.com\public\static\base\2017-06-15/59422840becb7.jpg
             */

            private int id;
            private String banner_image;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getBanner_image() {
                return banner_image;
            }

            public void setBanner_image(String banner_image) {
                this.banner_image = banner_image;
            }
        }

        public static class CategoryBean {
            /**
             * cate_id : 2
             * cate_name : 餐饮
             * logo : yzl.com\public\static\category\20170822\56bf084bc19606c5177486e183ff9859.png
             */

            private int cate_id;
            private String cate_name;
            private String logo;

            public int getCate_id() {
                return cate_id;
            }

            public void setCate_id(int cate_id) {
                this.cate_id = cate_id;
            }

            public String getCate_name() {
                return cate_name;
            }

            public void setCate_name(String cate_name) {
                this.cate_name = cate_name;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }
        }

        public static class GoodsBean {
            /**
             * goods_id : 382
             * goods_name : 1112233
             * goods_image : yzl.com\public\static\goods\20170821\31cb23df16431e8a862e61e681ccab18.png
             * cost : 11.00
             */

            private int goods_id;
            private String goods_name;
            private String goods_image;
            private String cost;

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getGoods_image() {
                return goods_image;
            }

            public void setGoods_image(String goods_image) {
                this.goods_image = goods_image;
            }

            public String getCost() {
                return cost;
            }

            public void setCost(String cost) {
                this.cost = cost;
            }
        }

        public static class HotBean {
            /**
             * goods_id : 382
             * goods_name : 1112233
             * goods_image : yzl.com\public\static\goods\20170821\31cb23df16431e8a862e61e681ccab18.png
             * cost : 11.00
             */

            private int goods_id;
            private String goods_name;
            private String goods_image;
            private String cost;

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getGoods_image() {
                return goods_image;
            }

            public void setGoods_image(String goods_image) {
                this.goods_image = goods_image;
            }

            public String getCost() {
                return cost;
            }

            public void setCost(String cost) {
                this.cost = cost;
            }
        }

        public static class StoreBean {
            /**
             * store_id : 19
             * store_name : 銘诚微商商贸
             * store_address : 常泰路附近
             * logo : yzl.compublic\static\license\
             */

            private int store_id;
            private String store_name;
            private String store_address;
            private String logo;

            public int getStore_id() {
                return store_id;
            }

            public void setStore_id(int store_id) {
                this.store_id = store_id;
            }

            public String getStore_name() {
                return store_name;
            }

            public void setStore_name(String store_name) {
                this.store_name = store_name;
            }

            public String getStore_address() {
                return store_address;
            }

            public void setStore_address(String store_address) {
                this.store_address = store_address;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }
        }

        public static class NewsBean {
            private int newid;
            private String new_title;
            private String url;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getNewid() {
                return newid;
            }

            public void setNewid(int newid) {
                this.newid = newid;
            }

            public String getNew_title() {
                return new_title;
            }

            public void setNew_title(String new_title) {
                this.new_title = new_title;
            }
        }
    }
}
