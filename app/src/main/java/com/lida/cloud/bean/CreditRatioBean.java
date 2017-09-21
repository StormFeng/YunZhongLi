package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 加密字符串
 * Created by WeiQingFeng on 2017/8/23.
 */

public class CreditRatioBean extends NetResult {

    private List<DataBean> data;

    public static CreditRatioBean parse(String json) throws AppException {
        CreditRatioBean res = new CreditRatioBean();
        try {
            res = gson.fromJson(json, CreditRatioBean.class);
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
         * ratio : 100
         */

        private String ratio;

        public String getRatio() {
            return ratio;
        }

        public void setRatio(String ratio) {
            this.ratio = ratio;
        }
    }
}
