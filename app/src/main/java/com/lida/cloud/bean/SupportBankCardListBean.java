package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 我的银行卡
 * Created by WeiQingFeng on 2017/8/23.
 */

public class SupportBankCardListBean extends NetResult {

    private List<DataBean> data;

    public static SupportBankCardListBean parse(String json) throws AppException {
        SupportBankCardListBean res = new SupportBankCardListBean();
        try {
            res = gson.fromJson(json, SupportBankCardListBean.class);
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
         * bank_id : 1
         * bank_name : 中国银行
         * bank_image : http://www.yzl.com/static/uploads/20170824/1d7a68b19c9f8ffd2206b805f35faf10.jpg
         * status : 1
         * bank_code : CCB
         */

        private String bank_id;
        private String bank_name;
        private String bank_image;
        private String status;
        private String bank_code;

        public String getBank_id() {
            return bank_id;
        }

        public void setBank_id(String bank_id) {
            this.bank_id = bank_id;
        }

        public String getBank_name() {
            return bank_name;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }

        public String getBank_image() {
            return bank_image;
        }

        public void setBank_image(String bank_image) {
            this.bank_image = bank_image;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getBank_code() {
            return bank_code;
        }

        public void setBank_code(String bank_code) {
            this.bank_code = bank_code;
        }
    }
}
