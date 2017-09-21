package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 余额记录
 * Created by WeiQingFeng on 2017/8/23.
 */

public class BalanceListBean extends NetResult {

    /**
     * data : {"list":[{"amount":525.57,"create_time":"2017-08-04 00:00:04","type":"cz"}],"page_num":1,"page_limit":10,"count":14}
     */

    private List<DataBean> data;

    public static BalanceListBean parse(String json) throws AppException {
        BalanceListBean res = new BalanceListBean();
        try {
            res = gson.fromJson(json, BalanceListBean.class);
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
         * list : [{"amount":525.57,"create_time":"2017-08-04 00:00:04","type":"cz"}]
         * page_num : 1
         * page_limit : 10
         * count : 14
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

        public static class ListBean extends NetResult{
            /**
             * amount : 525.57
             * create_time : 2017-08-04 00:00:04
             * type : cz
             */

            private double amount;
            private String create_time;
            private String type;

            public double getAmount() {
                return amount;
            }

            public void setAmount(double amount) {
                this.amount = amount;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }
}
