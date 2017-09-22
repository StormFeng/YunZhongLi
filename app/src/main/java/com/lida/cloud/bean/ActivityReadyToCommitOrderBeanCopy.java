package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 确定订单（购物车进来）
 * Created by WeiQingFeng on 2017/8/23.
 */

public class ActivityReadyToCommitOrderBeanCopy extends NetResult {

    private List<DataBean> data;

    public static ActivityReadyToCommitOrderBeanCopy parse(String json) throws AppException {
        ActivityReadyToCommitOrderBeanCopy res = new ActivityReadyToCommitOrderBeanCopy();
        try {
            res = gson.fromJson(json, ActivityReadyToCommitOrderBeanCopy.class);
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
         * address : []
         * list : [{"shopid":40,"selshopname":"上海女人","goods":[{"specid":397,"total":1,"cost":"55.00","sp_name":"test1","sp_image":"http://www.yzl.com/static/goods/20170823/06ecd425ed0d29d979f081366ab6fae5.png","spec_name":"红色"}]},{"shopid":41,"selshopname":"丫丫呀","goods":[{"specid":397,"total":1,"cost":"55.00","sp_name":"test1","sp_image":"http://www.yzl.com/static/goods/20170823/06ecd425ed0d29d979f081366ab6fae5.png","spec_name":"红色"}]}]
         * total : 2
         * price : 110
         */

        private List<ListBean> list;
        private String total;
        private String price;

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * shopid : 40
             * selshopname : 上海女人
             * goods : [{"specid":397,"total":1,"cost":"55.00","sp_name":"test1","sp_image":"http://www.yzl.com/static/goods/20170823/06ecd425ed0d29d979f081366ab6fae5.png","spec_name":"红色"}]
             */

            private String spec_name;
            private String spec_image;
            private String spec_cost;
            private String sp_id;
            private String sp_selid;
            private String store_name;
            private String sp_name;
            private String sp_image;
            private String market_price;
            private String cost;
            private String stock;
            private String create_time;
            private String status;
            private String total;
            private String remark;

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
            }

            public String getSpec_name() {
                return spec_name;
            }

            public void setSpec_name(String spec_name) {
                this.spec_name = spec_name;
            }

            public String getSpec_image() {
                return spec_image;
            }

            public void setSpec_image(String spec_image) {
                this.spec_image = spec_image;
            }

            public String getSpec_cost() {
                return spec_cost;
            }

            public void setSpec_cost(String spec_cost) {
                this.spec_cost = spec_cost;
            }

            public String getSp_id() {
                return sp_id;
            }

            public void setSp_id(String sp_id) {
                this.sp_id = sp_id;
            }

            public String getSp_selid() {
                return sp_selid;
            }

            public void setSp_selid(String sp_selid) {
                this.sp_selid = sp_selid;
            }

            public String getStore_name() {
                return store_name;
            }

            public void setStore_name(String store_name) {
                this.store_name = store_name;
            }

            public String getSp_name() {
                return sp_name;
            }

            public void setSp_name(String sp_name) {
                this.sp_name = sp_name;
            }

            public String getSp_image() {
                return sp_image;
            }

            public void setSp_image(String sp_image) {
                this.sp_image = sp_image;
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

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }
    }
}
