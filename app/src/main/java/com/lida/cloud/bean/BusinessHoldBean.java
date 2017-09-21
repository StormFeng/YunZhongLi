package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 商家库存积分记录
 * Created by WeiQingFeng on 2017/8/23.
 */

public class BusinessHoldBean extends NetResult {

    /**
     * data : {"credit":0,"count":3,"record":[{"out_id":1,"out_selid":353,"out_name":"Jack","out_credit":100,"out_time":"2017-06-13 16:53:41","status":0},{"out_id":2,"out_selid":353,"out_name":"Jack","out_credit":100,"out_time":"2017-06-13 18:15:54","status":0},{"out_id":3,"out_selid":353,"out_name":"Jack","out_credit":100,"out_time":"2017-06-13 18:19:25","status":0}],"page_num":1,"page_limit":8}
     */

    private List<DataBean> data;

    public static BusinessHoldBean parse(String json) throws AppException {
        BusinessHoldBean res = new BusinessHoldBean();
        try {
            res = gson.fromJson(json, BusinessHoldBean.class);
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
         * credit : 0
         * count : 3
         * record : [{"out_id":1,"out_selid":353,"out_name":"Jack","out_credit":100,"out_time":"2017-06-13 16:53:41","status":0},{"out_id":2,"out_selid":353,"out_name":"Jack","out_credit":100,"out_time":"2017-06-13 18:15:54","status":0},{"out_id":3,"out_selid":353,"out_name":"Jack","out_credit":100,"out_time":"2017-06-13 18:19:25","status":0}]
         * page_num : 1
         * page_limit : 8
         */

        private String credit;
        private String count;
        private String page_num;
        private String page_limit;
        private List<RecordBean> record;

        public String getCredit() {
            return credit;
        }

        public void setCredit(String credit) {
            this.credit = credit;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

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

        public List<RecordBean> getRecord() {
            return record;
        }

        public void setRecord(List<RecordBean> record) {
            this.record = record;
        }

        public static class RecordBean extends NetResult{
            /**
             * out_id : 1
             * out_selid : 353
             * out_name : Jack
             * out_credit : 100
             * out_time : 2017-06-13 16:53:41
             * status : 0
             */

            private String out_id;
            private String out_selid;
            private String out_name;
            private String out_credit;
            private String out_time;
            private String status;

            public String getOut_id() {
                return out_id;
            }

            public void setOut_id(String out_id) {
                this.out_id = out_id;
            }

            public String getOut_selid() {
                return out_selid;
            }

            public void setOut_selid(String out_selid) {
                this.out_selid = out_selid;
            }

            public String getOut_name() {
                return out_name;
            }

            public void setOut_name(String out_name) {
                this.out_name = out_name;
            }

            public String getOut_credit() {
                return out_credit;
            }

            public void setOut_credit(String out_credit) {
                this.out_credit = out_credit;
            }

            public String getOut_time() {
                return out_time;
            }

            public void setOut_time(String out_time) {
                this.out_time = out_time;
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
