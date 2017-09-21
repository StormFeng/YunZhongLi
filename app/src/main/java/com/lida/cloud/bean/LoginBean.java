package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 登录
 * Created by WeiQingFeng on 2017/8/23.
 */

public class LoginBean extends NetResult {

    /**
     * data : {"memid":360}
     */

    private List<DataBean> data;

    public static LoginBean parse(String json) throws AppException {
        LoginBean res = new LoginBean();
        try {
            res = gson.fromJson(json, LoginBean.class);
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
         * memid : 360
         */

        private String tokenInfo;
        private String isagent;

        public String getIsagent() {
            return isagent;
        }

        public void setIsagent(String isagent) {
            this.isagent = isagent;
        }

        public String getTokenInfo() {
            return tokenInfo;
        }

        public void setTokenInfo(String tokenInfo) {
            this.tokenInfo = tokenInfo;
        }
    }
}
