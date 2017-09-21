package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * Created by xkr on 2017/9/7.
 */

public class MyRecommendBean extends NetResult {


    /**
     * data : [{"list":[{"memid":83,"mem_tname":"hao","mem_name":"appletext","mem_hname":"苹果审核账号，别删","mem_nc":"昵称","mem_registertime":"0000-00-00 00:00:00","credit":0.3}],"count":1,"page_num":1,"page_limit":10,"total":0.3}]
     * code : 1
     * message : success
     */

    private List<DataBean> data;

    public static MyRecommendBean parse(String json) throws AppException {
        MyRecommendBean res = new MyRecommendBean();
        try {
            res = gson.fromJson(json, MyRecommendBean.class);
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
         * list : [{"memid":83,"mem_tname":"hao","mem_name":"appletext","mem_hname":"苹果审核账号，别删","mem_nc":"昵称","mem_registertime":"0000-00-00 00:00:00","credit":0.3}]
         * count : 1
         * page_num : 1
         * page_limit : 10
         * total : 0.3
         */

        private String count;
        private String page_num;
        private String page_limit;
        private double total;
        private List<ListBean> list;

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

        public double getTotal() {
            return total;
        }

        public void setTotal(double total) {
            this.total = total;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean extends NetResult{
            /**
             * memid : 83
             * mem_tname : hao
             * mem_name : appletext
             * mem_hname : 苹果审核账号，别删
             * mem_nc : 昵称
             * mem_registertime : 0000-00-00 00:00:00
             * credit : 0.3
             */

            private String memid;
            private String mem_tname;
            private String mem_name;
            private String mem_hname;
            private String mem_nc;
            private String mem_registertime;
            private String credit;

            public String getMemid() {
                return memid;
            }

            public void setMemid(String memid) {
                this.memid = memid;
            }

            public String getMem_tname() {
                return mem_tname;
            }

            public void setMem_tname(String mem_tname) {
                this.mem_tname = mem_tname;
            }

            public String getMem_name() {
                return mem_name;
            }

            public void setMem_name(String mem_name) {
                this.mem_name = mem_name;
            }

            public String getMem_hname() {
                return mem_hname;
            }

            public void setMem_hname(String mem_hname) {
                this.mem_hname = mem_hname;
            }

            public String getMem_nc() {
                return mem_nc;
            }

            public void setMem_nc(String mem_nc) {
                this.mem_nc = mem_nc;
            }

            public String getMem_registertime() {
                return mem_registertime;
            }

            public void setMem_registertime(String mem_registertime) {
                this.mem_registertime = mem_registertime;
            }

            public String getCredit() {
                return credit;
            }

            public void setCredit(String credit) {
                this.credit = credit;
            }
        }
    }
}
