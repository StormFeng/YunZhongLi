package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;
import java.util.List;

/**
 * 评价商品信息
 * Created by WeiQingFeng on 2017/8/23.
 */

public class OrderEvaluateBean extends NetResult {
    private List<DataBean> data;

    public static OrderEvaluateBean parse(String json) throws AppException {
        OrderEvaluateBean res = new OrderEvaluateBean();
        try {
            res = gson.fromJson(json, OrderEvaluateBean.class);
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
         * order_id : 106
         * goods_id : 392
         * spec_id : 0
         * spec_name : 无规格
         * goods_name : 华为
         */

        private int order_id;
        private int goods_id;
        private int spec_id;
        private String spec_name;
        private String goods_name;
        private String con;
        private List<String> pics;

        public String getCon() {
            return con;
        }

        public void setCon(String con) {
            this.con = con;
        }

        public List<String> getPics() {
            if(pics==null){
                pics = new ArrayList<>();
                pics.add("");
                return pics;
            }
            return pics;
        }

        public void setPics(List<String> pics) {
            this.pics = pics;
        }

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }

        public int getSpec_id() {
            return spec_id;
        }

        public void setSpec_id(int spec_id) {
            this.spec_id = spec_id;
        }

        public String getSpec_name() {
            return spec_name;
        }

        public void setSpec_name(String spec_name) {
            this.spec_name = spec_name;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }
    }
}
