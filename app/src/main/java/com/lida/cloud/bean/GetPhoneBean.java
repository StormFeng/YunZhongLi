package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 获取手机号码
 * Created by WeiQingFeng on 2017/8/23.
 */

public class GetPhoneBean extends NetResult {

    /**
     * data : {"mobile":"13513513513"}
     */

    private List<DataBean> data;

    public static GetPhoneBean parse(String json) throws AppException {
        GetPhoneBean res = new GetPhoneBean();
        try {
            res = gson.fromJson(json, GetPhoneBean.class);
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
         * mobile : 13513513513
         */

        private String mobile;

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
    }
}
