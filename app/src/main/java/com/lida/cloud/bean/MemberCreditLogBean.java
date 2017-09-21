package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 加密字符串
 * Created by WeiQingFeng on 2017/8/23.
 */

public class MemberCreditLogBean extends NetResult {

    private List<DataBean> data;

    public static MemberCreditLogBean parse(String json) throws AppException {
        MemberCreditLogBean res = new MemberCreditLogBean();
        try {
            res = gson.fromJson(json, MemberCreditLogBean.class);
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
         * list : [{"credit":12.1,"log":"从平台购买产品返还信用积分","createtime":"2014-07-09 00:20:07"},{"credit":670,"log":"从平台购买产品返还信用积分","createtime":"2014-07-09 00:58:57"},{"credit":12.1,"log":"从平台购买产品返还信用积分","createtime":"2014-07-09 00:59:07"},{"credit":220,"log":"从店铺购买产品返还信用积分","createtime":"2014-07-09 00:19:54"},{"credit":12.1,"log":"从平台购买产品返还信用积分","createtime":"2014-07-09 00:20:07"},{"credit":670,"log":"从平台购买产品返还信用积分","createtime":"2014-07-09 00:58:57"},{"credit":12.1,"log":"从平台购买产品返还信用积分","createtime":"2014-07-09 00:59:07"},{"credit":220,"log":"从店铺购买产品返还信用积分","createtime":"2014-07-09 00:19:54"},{"credit":12.1,"log":"从平台购买产品返还信用积分","createtime":"2014-07-09 00:20:07"},{"credit":670,"log":"从平台购买产品返还信用积分","createtime":"2014-07-09 00:58:57"}]
         * credit : 15.8
         * page_num : 1
         * page_limit : 10
         * count : 24
         */

        private double credit;
        private String page_num;
        private String page_limit;
        private String count;
        private List<ListBean> list;

        public double getCredit() {
            return credit;
        }

        public void setCredit(double credit) {
            this.credit = credit;
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

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
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
             * credit : 12.1
             * log : 从平台购买产品返还信用积分
             * createtime : 2014-07-09 00:20:07
             */

            private double credit;
            private String log;
            private String createtime;

            public double getCredit() {
                return credit;
            }

            public void setCredit(double credit) {
                this.credit = credit;
            }

            public String getLog() {
                return log;
            }

            public void setLog(String log) {
                this.log = log;
            }

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }
        }
    }
}
