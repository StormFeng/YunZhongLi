package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 代理中心-收入
 * Created by xkr on 2017/9/7.
 */

public class ActivityAgentCenterRevenueBean extends NetResult {


    /**
     * data : [{"list":[{"id":9,"shop_id":7,"money":75,"paydate":"2017-09-04","createtime":1504454400,"selshopname":"食全食美"},{"id":10,"shop_id":6,"money":26.38,"paydate":"2017-09-04","createtime":1504454400,"selshopname":"游乐城"}],"sum":101.38,"count":2,"page_limit":10,"page_num":1}]
     * code : 1
     */

    private List<DataBean> data;

    public static ActivityAgentCenterRevenueBean parse(String json) throws AppException {
        ActivityAgentCenterRevenueBean res = new ActivityAgentCenterRevenueBean();
        try {
            res = gson.fromJson(json, ActivityAgentCenterRevenueBean.class);
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

    public static class DataBean extends NetResult{
        /**
         * list : [{"id":9,"shop_id":7,"money":75,"paydate":"2017-09-04","createtime":1504454400,"selshopname":"食全食美"},{"id":10,"shop_id":6,"money":26.38,"paydate":"2017-09-04","createtime":1504454400,"selshopname":"游乐城"}]
         * sum : 101.38
         * count : 2
         * page_limit : 10
         * page_num : 1
         */

        private String sum;
        private int count;
        private int page_limit;
        private int page_num;
        private List<ListBean> list;

        public String getSum() {
            return sum;
        }

        public void setSum(String sum) {
            this.sum = sum;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getPage_limit() {
            return page_limit;
        }

        public void setPage_limit(int page_limit) {
            this.page_limit = page_limit;
        }

        public int getPage_num() {
            return page_num;
        }

        public void setPage_num(int page_num) {
            this.page_num = page_num;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean extends NetResult{
            /**
             * id : 9
             * shop_id : 7
             * money : 75
             * paydate : 2017-09-04
             * createtime : 1504454400
             * selshopname : 食全食美
             */

            private int id;
            private int shop_id;
            private String money;
            private String paydate;
            private int createtime;
            private String selshopname;
            private String selimage;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getShop_id() {
                return shop_id;
            }

            public void setShop_id(int shop_id) {
                this.shop_id = shop_id;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getPaydate() {
                return paydate;
            }

            public void setPaydate(String paydate) {
                this.paydate = paydate;
            }

            public int getCreatetime() {
                return createtime;
            }

            public void setCreatetime(int createtime) {
                this.createtime = createtime;
            }

            public String getSelshopname() {
                return selshopname;
            }

            public void setSelshopname(String selshopname) {
                this.selshopname = selshopname;
            }

            public String getSelimage() {
                return selimage;
            }

            public void setSelimage(String selimage) {
                this.selimage = selimage;
            }
        }
    }
}
