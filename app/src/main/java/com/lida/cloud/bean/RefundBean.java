package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 退货地址
 * Created by WeiQingFeng on 2017/8/23.
 */

public class RefundBean extends NetResult {

    private List<DataBean> data;

    public static RefundBean parse(String json) throws AppException {
        RefundBean res = new RefundBean();
        try {
            res = gson.fromJson(json, RefundBean.class);
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
         * address : 广东省广州市
         * name : 李老板
         * mobile : 18012345678
         */

        private String address;
        private String name;
        private String mobile;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
    }
}
