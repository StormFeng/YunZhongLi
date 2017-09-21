package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 我的银行卡
 * Created by WeiQingFeng on 2017/8/23.
 */

public class MyBankCardListBean extends NetResult {

    private List<DataBean> data;

    public static MyBankCardListBean parse(String json) throws AppException {
        MyBankCardListBean res = new MyBankCardListBean();
        try {
            res = gson.fromJson(json, MyBankCardListBean.class);
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
         * b_id : 1
         * b_memid : 83
         * b_number : 1234 56
         * b_name : 陈小明
         * b_bankid : 2
         * b_bank_code : CCB
         * b_bank_name : 中国建设银行
         */

        private String b_id;
        private String b_memid;
        private String b_number;
        private String b_name;
        private String b_bankid;
        private String b_bank_code;
        private String b_bank_name;

        public String getB_id() {
            return b_id;
        }

        public void setB_id(String b_id) {
            this.b_id = b_id;
        }

        public String getB_memid() {
            return b_memid;
        }

        public void setB_memid(String b_memid) {
            this.b_memid = b_memid;
        }

        public String getB_number() {
            return b_number;
        }

        public void setB_number(String b_number) {
            this.b_number = b_number;
        }

        public String getB_name() {
            return b_name;
        }

        public void setB_name(String b_name) {
            this.b_name = b_name;
        }

        public String getB_bankid() {
            return b_bankid;
        }

        public void setB_bankid(String b_bankid) {
            this.b_bankid = b_bankid;
        }

        public String getB_bank_code() {
            return b_bank_code;
        }

        public void setB_bank_code(String b_bank_code) {
            this.b_bank_code = b_bank_code;
        }

        public String getB_bank_name() {
            return b_bank_name;
        }

        public void setB_bank_name(String b_bank_name) {
            this.b_bank_name = b_bank_name;
        }
    }
}
