package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.io.Serializable;
import java.util.List;

/**
 * 商品详情
 * Created by WeiQingFeng on 2017/8/23.
 */

public class GoodDetailBean extends NetResult {

    /**
     * data : {"goods_id":391,"goods_name":"衣服","goods_image":"http://yzl.gzldrj.com/static/goods/20170901/ddcacbe949d0f847206035e6dfbf453b.png","goods_images":["http://yzl.gzldrj.com/public/static/goods/20170901/1e7408b816afa92dfe64d6d3b059d141.png"],"market_price":"100.00","cost":"111.00","stock":21,"attribute":[["生产许可证编号","QS4452 2401 3585"],["厂名","广东森乐食品有限公司"],["厂址","揭阳市揭东区云路镇埔美村工业区(横路下西片)"],["厂家联系方式","06636116186"],["配料表","鸡蛋 小麦粉 白砂糖 精炼植物油 食用葡萄糖 食用葡萄糖浆 食用盐 全脂奶粉 食品添加剂"],["储藏方法","避光阴凉处储藏"]],"credit":10,"is_spec":"1","status":"1","spec":[{"spec_id":399,"spec_image":"","spec_name":"xl","spec_cost":"100.00","spec_stock":1},{"spec_id":400,"spec_image":"","spec_name":"l","spec_cost":"100.00","spec_stock":10},{"spec_id":401,"spec_image":"","spec_name":"xxl","spec_cost":"100.00","spec_stock":10}],"content":"http://yzl.gzldrj.com/wap/goods/detail?goods_id=391","is_collect":false,"store":{"store_id":40,"store_name":"上海女人","logo":"","area":"上海市浦东"},"comment":{"images":["http://yzl.gzldrj.com/static/goods_comment/20170831/4cb1ecb5ed69bf1c82a2dc483d4727b1.gif","http://yzl.gzldrj.com/static/goods_comment/20170831/af8965ba94757e97de971be983e9c68c.gif","http://yzl.gzldrj.com/static/goods_comment/20170831/75ada4d45938b7a26e0ca719bcd085b3.gif"],"member_name":"r***","member_thumb":"","spec":"无规格","content":"这是极好的","create_time":"2017-08-31 15:54:23"}}
     */

    private List<DataBean> data;

    public static GoodDetailBean parse(String json) throws AppException {
        GoodDetailBean res = new GoodDetailBean();
        try {
            res = gson.fromJson(json, GoodDetailBean.class);
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

    public static class DataBean implements Serializable{
        /**
         * goods_id : 391
         * goods_name : 衣服
         * goods_image : http://yzl.gzldrj.com/static/goods/20170901/ddcacbe949d0f847206035e6dfbf453b.png
         * goods_images : ["http://yzl.gzldrj.com/public/static/goods/20170901/1e7408b816afa92dfe64d6d3b059d141.png"]
         * market_price : 100.00
         * cost : 111.00
         * stock : 21
         * attribute : [["生产许可证编号","QS4452 2401 3585"],["厂名","广东森乐食品有限公司"],["厂址","揭阳市揭东区云路镇埔美村工业区(横路下西片)"],["厂家联系方式","06636116186"],["配料表","鸡蛋 小麦粉 白砂糖 精炼植物油 食用葡萄糖 食用葡萄糖浆 食用盐 全脂奶粉 食品添加剂"],["储藏方法","避光阴凉处储藏"]]
         * credit : 10
         * is_spec : 1
         * status : 1
         * spec : [{"spec_id":399,"spec_image":"","spec_name":"xl","spec_cost":"100.00","spec_stock":1},{"spec_id":400,"spec_image":"","spec_name":"l","spec_cost":"100.00","spec_stock":10},{"spec_id":401,"spec_image":"","spec_name":"xxl","spec_cost":"100.00","spec_stock":10}]
         * content : http://yzl.gzldrj.com/wap/goods/detail?goods_id=391
         * is_collect : false
         * store : {"store_id":40,"store_name":"上海女人","logo":"","area":"上海市浦东"}
         * comment : {"images":["http://yzl.gzldrj.com/static/goods_comment/20170831/4cb1ecb5ed69bf1c82a2dc483d4727b1.gif","http://yzl.gzldrj.com/static/goods_comment/20170831/af8965ba94757e97de971be983e9c68c.gif","http://yzl.gzldrj.com/static/goods_comment/20170831/75ada4d45938b7a26e0ca719bcd085b3.gif"],"member_name":"r***","member_thumb":"","spec":"无规格","content":"这是极好的","create_time":"2017-08-31 15:54:23"}
         */

        private String goods_id;
        private String goods_name;
        private String goods_image;
        private String market_price;
        private String cost;
        private String stock;
        private String credit;
        private String is_spec;
        private String status;
        private String content;
        private String url;
        private boolean is_collect;
        private StoreBean store;
        private List<String> goods_images;
        private List<List<String>> attribute;
        private List<SpecBean> spec;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean is_collect() {
            return is_collect;
        }

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
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

        public String getMarket_price() {
            return market_price;
        }

        public void setMarket_price(String market_price) {
            this.market_price = market_price;
        }

        public String getCost() {
            return cost;
        }

        public void setCost(String cost) {
            this.cost = cost;
        }

        public String getStock() {
            return stock;
        }

        public void setStock(String stock) {
            this.stock = stock;
        }

        public String getCredit() {
            return credit;
        }

        public void setCredit(String credit) {
            this.credit = credit;
        }

        public String getIs_spec() {
            return is_spec;
        }

        public void setIs_spec(String is_spec) {
            this.is_spec = is_spec;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public boolean isIs_collect() {
            return is_collect;
        }

        public void setIs_collect(boolean is_collect) {
            this.is_collect = is_collect;
        }

        public StoreBean getStore() {
            return store;
        }

        public void setStore(StoreBean store) {
            this.store = store;
        }

        public List<String> getGoods_images() {
            return goods_images;
        }

        public void setGoods_images(List<String> goods_images) {
            this.goods_images = goods_images;
        }

        public List<List<String>> getAttribute() {
            return attribute;
        }

        public void setAttribute(List<List<String>> attribute) {
            this.attribute = attribute;
        }

        public List<SpecBean> getSpec() {
            return spec;
        }

        public void setSpec(List<SpecBean> spec) {
            this.spec = spec;
        }

        public static class StoreBean implements Serializable{
            /**
             * store_id : 40
             * store_name : 上海女人
             * logo : 
             * area : 上海市浦东
             */

            private String store_id;
            private String store_name;
            private String logo;
            private String area;

            public String getStore_id() {
                return store_id;
            }

            public void setStore_id(String store_id) {
                this.store_id = store_id;
            }

            public String getStore_name() {
                return store_name;
            }

            public void setStore_name(String store_name) {
                this.store_name = store_name;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }
        }

        public static class CommentBean implements Serializable{
            /**
             * images : ["http://yzl.gzldrj.com/static/goods_comment/20170831/4cb1ecb5ed69bf1c82a2dc483d4727b1.gif","http://yzl.gzldrj.com/static/goods_comment/20170831/af8965ba94757e97de971be983e9c68c.gif","http://yzl.gzldrj.com/static/goods_comment/20170831/75ada4d45938b7a26e0ca719bcd085b3.gif"]
             * member_name : r***
             * member_thumb : 
             * spec : 无规格
             * content : 这是极好的
             * create_time : 2017-08-31 15:54:23
             */

            private String member_name;
            private String member_thumb;
            private String spec;
            private String content;
            private String create_time;
            private List<String> images;

            public String getMember_name() {
                return member_name;
            }

            public void setMember_name(String member_name) {
                this.member_name = member_name;
            }

            public String getMember_thumb() {
                return member_thumb;
            }

            public void setMember_thumb(String member_thumb) {
                this.member_thumb = member_thumb;
            }

            public String getSpec() {
                return spec;
            }

            public void setSpec(String spec) {
                this.spec = spec;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public List<String> getImages() {
                return images;
            }

            public void setImages(List<String> images) {
                this.images = images;
            }
        }

        public static class SpecBean implements Serializable{
            /**
             * spec_id : 399
             * spec_image : 
             * spec_name : xl
             * spec_cost : 100.00
             * spec_stock : 1
             */

            private String spec_id;
            private String spec_image;
            private String spec_name;
            private String spec_cost;
            private String spec_stock;

            public String getSpec_id() {
                return spec_id;
            }

            public void setSpec_id(String spec_id) {
                this.spec_id = spec_id;
            }

            public String getSpec_image() {
                return spec_image;
            }

            public void setSpec_image(String spec_image) {
                this.spec_image = spec_image;
            }

            public String getSpec_name() {
                return spec_name;
            }

            public void setSpec_name(String spec_name) {
                this.spec_name = spec_name;
            }

            public String getSpec_cost() {
                return spec_cost;
            }

            public void setSpec_cost(String spec_cost) {
                this.spec_cost = spec_cost;
            }

            public String getSpec_stock() {
                return spec_stock;
            }

            public void setSpec_stock(String spec_stock) {
                this.spec_stock = spec_stock;
            }
        }
    }
}
