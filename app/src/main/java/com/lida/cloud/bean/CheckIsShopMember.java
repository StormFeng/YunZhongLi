package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 判断商家认证状态
 * Created by WeiQingFeng on 2017/9/4.
 */

public class CheckIsShopMember extends NetResult {


    /**
     * data : {"status":-1}
     */

    private List<DataBean> data;

    public static CheckIsShopMember parse(String json) throws AppException {
        CheckIsShopMember res = new CheckIsShopMember();
        try {
            res = gson.fromJson(json, CheckIsShopMember.class);
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
         * status : -1
         */

        private int status;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
