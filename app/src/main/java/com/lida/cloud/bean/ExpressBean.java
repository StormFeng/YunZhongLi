package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 加密字符串
 * Created by WeiQingFeng on 2017/8/23.
 */

public class ExpressBean extends NetResult {
    private List<DataBean> data;

    public static ExpressBean parse(String json) throws AppException {
        ExpressBean res = new ExpressBean();
        try {
            res = gson.fromJson(json, ExpressBean.class);
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
         * code : shunfeng
         * name : 顺丰
         */

        private String code;
        private String name;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
