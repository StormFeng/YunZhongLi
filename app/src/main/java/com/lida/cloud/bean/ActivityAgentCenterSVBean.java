package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 代理中心--销售额
 * Created by xkr on 2017/9/7.
 */

public class ActivityAgentCenterSVBean extends NetResult {


    /**
     * data : [{"list":[{"id":1,"shopid":7,"money":111,"paydate":"2017-09-04","createtime":1504483200,"selshopname":"食全食美"},{"id":3,"shopid":8,"money":80,"paydate":"2017-09-04","createtime":1504483200,"selshopname":"康苑养生堂"},{"id":2,"shopid":6,"money":222,"paydate":"2017-09-03","createtime":1504368000,"selshopname":"游乐城"},{"id":4,"shopid":8,"money":50,"paydate":"2017-09-03","createtime":1504368000,"selshopname":"康苑养生堂"},{"id":5,"shopid":7,"money":55,"paydate":"2017-09-03","createtime":1504368000,"selshopname":"食全食美"}],"sum":518,"count":5,"page_limit":10,"page_num":1}]
     * code : 1
     */

    private List<DataBean> data;

    public static ActivityAgentCenterSVBean parse(String json) throws AppException {
        ActivityAgentCenterSVBean res = new ActivityAgentCenterSVBean();
        try {
            res = gson.fromJson(json, ActivityAgentCenterSVBean.class);
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
         * list : [{"id":1,"shopid":7,"money":111,"paydate":"2017-09-04","createtime":1504483200,"selshopname":"食全食美"},{"id":3,"shopid":8,"money":80,"paydate":"2017-09-04","createtime":1504483200,"selshopname":"康苑养生堂"},{"id":2,"shopid":6,"money":222,"paydate":"2017-09-03","createtime":1504368000,"selshopname":"游乐城"},{"id":4,"shopid":8,"money":50,"paydate":"2017-09-03","createtime":1504368000,"selshopname":"康苑养生堂"},{"id":5,"shopid":7,"money":55,"paydate":"2017-09-03","createtime":1504368000,"selshopname":"食全食美"}]
         * sum : 518
         * count : 5
         * page_limit : 10
         * page_num : 1
         */

        private String sum;
        private String count;
        private String page_limit;
        private String page_num;
        private List<ListBean> list;

        public String getSum() {
            return sum;
        }

        public void setSum(String sum) {
            this.sum = sum;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getPage_limit() {
            return page_limit;
        }

        public void setPage_limit(String page_limit) {
            this.page_limit = page_limit;
        }

        public String getPage_num() {
            return page_num;
        }

        public void setPage_num(String page_num) {
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
             * id : 1
             * shopid : 7
             * money : 111
             * paydate : 2017-09-04
             * createtime : 1504483200
             * selshopname : 食全食美
             */

            private String id;
            private String shopid;
            private String money;
            private String paydate;
            private String createtime;
            private String selshopname;
            private String selimage;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getShopid() {
                return shopid;
            }

            public void setShopid(String shopid) {
                this.shopid = shopid;
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

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
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
