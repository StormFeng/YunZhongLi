package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 省
 * Created by WeiQingFeng on 2017/8/23.
 */

public class ProvinceBean extends NetResult {

    private List<DataBean> data;

    public static ProvinceBean parse(String json) throws AppException {
        ProvinceBean res = new ProvinceBean();
        try {
            res = gson.fromJson(json, ProvinceBean.class);
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
         * d1_id : 8
         * d1_name : 内蒙古自治区
         */

        private int d1_id;
        private String d1_name;

        public int getD1_id() {
            return d1_id;
        }

        public void setD1_id(int d1_id) {
            this.d1_id = d1_id;
        }

        public String getD1_name() {
            return d1_name;
        }

        public void setD1_name(String d1_name) {
            this.d1_name = d1_name;
        }
    }
}
