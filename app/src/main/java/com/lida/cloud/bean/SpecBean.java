package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 商品规格
 * Created by WeiQingFeng on 2017/8/23.
 */

public class SpecBean extends NetResult {

    private List<DataBean> data;

    public static SpecBean parse(String json) throws AppException {
        SpecBean res = new SpecBean();
        try {
            res = gson.fromJson(json, SpecBean.class);
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
         * spec_id : 383
         * sp_id : 391
         * spec_name : 红色
         * spec_cost : 123.00
         * spec_weight : 100
         * spec_image : 20170821\2d61b1a2b1912c61617b083bcc59bc94.png
         * spec_stock : 82
         */

        private String spec_id;
        private String sp_id;
        private String spec_name;
        private String spec_cost;
        private String spec_weight;
        private String spec_image;
        private String spec_stock;

        public String getSpec_id() {
            return spec_id;
        }

        public void setSpec_id(String spec_id) {
            this.spec_id = spec_id;
        }

        public String getSp_id() {
            return sp_id;
        }

        public void setSp_id(String sp_id) {
            this.sp_id = sp_id;
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

        public String getSpec_weight() {
            return spec_weight;
        }

        public void setSpec_weight(String spec_weight) {
            this.spec_weight = spec_weight;
        }

        public String getSpec_image() {
            return spec_image;
        }

        public void setSpec_image(String spec_image) {
            this.spec_image = spec_image;
        }

        public String getSpec_stock() {
            return spec_stock;
        }

        public void setSpec_stock(String spec_stock) {
            this.spec_stock = spec_stock;
        }
    }
}
