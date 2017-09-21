package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 提交下单
 * Created by WeiQingFeng on 2017/8/23.
 */

public class OrderCreateBean extends NetResult {

    private List<DataBean> data;

    public static OrderCreateBean parse(String json) throws AppException {
        OrderCreateBean res = new OrderCreateBean();
        try {
            res = gson.fromJson(json, OrderCreateBean.class);
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
         * orderid : PS201709071528085649
         */

        private String orderid;

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }
    }
}
