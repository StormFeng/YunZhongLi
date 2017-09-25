package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 商品列表
 * Created by WeiQingFeng on 2016/10/28 0028.
 */

public class ActivityGoodListBean extends NetResult{

    /**
     * data : {"list":[{"goods_id":266,"goods_image":"http://yzl.com\\public\\static\\goods\\2017-07-22/5973454db6e3a.png","cost":"0.00","goods_name":""}],"page_num":2,"page_limit":10,"count":319}
     */

    private List<DataBean> data;

    public static ActivityGoodListBean parse(String json) throws AppException {
        ActivityGoodListBean res = new ActivityGoodListBean();
        try {
            res = gson.fromJson(json, ActivityGoodListBean.class);
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
        /**
         * list : [{"goods_id":266,"goods_image":"http://yzl.com\\public\\static\\goods\\2017-07-22/5973454db6e3a.png","cost":"0.00","goods_name":""}]
         * page_num : 2
         * page_limit : 10
         * count : 319
         */

        private int page_num;
        private int page_limit;
        private int count;
        private List<ListBean> list;

        public int getPage_num() {
            return page_num;
        }

        public void setPage_num(int page_num) {
            this.page_num = page_num;
        }

        public int getPage_limit() {
            return page_limit;
        }

        public void setPage_limit(int page_limit) {
            this.page_limit = page_limit;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * goods_id : 266
             * goods_image : http://yzl.com\public\static\goods\2017-07-22/5973454db6e3a.png
             * cost : 0.00
             * goods_name :
             */

            private int goods_id;
            private String goods_image;
            private String cost;
            private String goods_name;
            private String city;

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
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

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }
        }
    }
}
