package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 加密字符串
 * Created by WeiQingFeng on 2017/8/23.
 */

public class SignBean extends NetResult {
    /**
     * data : {"aesData":"******"}
     */

    private List<DataBean> data;

    public static SignBean parse(String json) throws AppException {
        SignBean res = new SignBean();
        try {
            res = gson.fromJson(json, SignBean.class);
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
         * aesData : ******
         */

        private String aesData;
        private String tokenInfo;

        public String getTokenInfo() {
            return tokenInfo;
        }

        public void setTokenInfo(String tokenInfo) {
            this.tokenInfo = tokenInfo;
        }

        public String getAesData() {
            return aesData;
        }

        public void setAesData(String aesData) {
            this.aesData = aesData;
        }
    }
}
