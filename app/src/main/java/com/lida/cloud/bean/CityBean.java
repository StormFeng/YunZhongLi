package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 市
 * Created by WeiQingFeng on 2017/8/23.
 */

public class CityBean extends NetResult {

    private List<DataBean> data;

    public static CityBean parse(String json) throws AppException {
        CityBean res = new CityBean();
        try {
            res = gson.fromJson(json, CityBean.class);
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
         * d2_id : 10
         * d2_fid : 8
         * d2_name : 呼和浩特市
         */

        private int d2_id;
        private int d2_fid;
        private String d2_name;

        public int getD2_id() {
            return d2_id;
        }

        public void setD2_id(int d2_id) {
            this.d2_id = d2_id;
        }

        public int getD2_fid() {
            return d2_fid;
        }

        public void setD2_fid(int d2_fid) {
            this.d2_fid = d2_fid;
        }

        public String getD2_name() {
            return d2_name;
        }

        public void setD2_name(String d2_name) {
            this.d2_name = d2_name;
        }
    }
}
