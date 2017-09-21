package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 加密字符串
 * Created by WeiQingFeng on 2017/8/23.
 */

public class BalanceYesterdayBean extends NetResult {

    private List<DataBean> data;

    public static BalanceYesterdayBean parse(String json) throws AppException {
        BalanceYesterdayBean res = new BalanceYesterdayBean();
        try {
            res = gson.fromJson(json, BalanceYesterdayBean.class);
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
         * list : [{"amount":0.47,"create_time":"2017-09-04 09:44:21"},{"amount":0.47,"create_time":"2017-09-04 09:44:18"},{"amount":0.48,"create_time":"2017-09-04 09:44:15"},{"amount":0.48,"create_time":"2017-09-04 09:44:13"},{"amount":0.48,"create_time":"2017-09-04 09:44:09"},{"amount":0.48,"create_time":"2017-09-04 09:43:56"},{"amount":0.48,"create_time":"2017-09-04 09:43:54"},{"amount":0.48,"create_time":"2017-09-04 09:43:07"},{"amount":0.48,"create_time":"2017-09-04 09:43:05"},{"amount":0.48,"create_time":"2017-09-04 09:42:43"}]
         * page_num : 1
         * page_limit : 10
         * count : 589
         * total_balance : 322.41
         */

        private String page_num;
        private String page_limit;
        private String count;
        private double total_balance;
        private List<ListBean> list;

        public String getPage_num() {
            return page_num;
        }

        public void setPage_num(String page_num) {
            this.page_num = page_num;
        }

        public String getPage_limit() {
            return page_limit;
        }

        public void setPage_limit(String page_limit) {
            this.page_limit = page_limit;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public double getTotal_balance() {
            return total_balance;
        }

        public void setTotal_balance(double total_balance) {
            this.total_balance = total_balance;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean extends NetResult{
            /**
             * amount : 0.47
             * create_time : 2017-09-04 09:44:21
             */

            private String amount;
            private String create_time;

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }
        }
    }
}
