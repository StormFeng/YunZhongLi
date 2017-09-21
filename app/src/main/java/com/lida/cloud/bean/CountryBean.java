package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 区
 * Created by WeiQingFeng on 2017/8/23.
 */

public class CountryBean extends NetResult {

    private List<DataBean> data;

    public static CountryBean parse(String json) throws AppException {
        CountryBean res = new CountryBean();
        try {
            res = gson.fromJson(json, CountryBean.class);
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
         * d3_id : 15
         * d3_fid_d1 : 8
         * d3_fid_d2 : 10
         * d3_name : 新城区
         */

        private int d3_id;
        private int d3_fid_d1;
        private int d3_fid_d2;
        private String d3_name;

        public int getD3_id() {
            return d3_id;
        }

        public void setD3_id(int d3_id) {
            this.d3_id = d3_id;
        }

        public int getD3_fid_d1() {
            return d3_fid_d1;
        }

        public void setD3_fid_d1(int d3_fid_d1) {
            this.d3_fid_d1 = d3_fid_d1;
        }

        public int getD3_fid_d2() {
            return d3_fid_d2;
        }

        public void setD3_fid_d2(int d3_fid_d2) {
            this.d3_fid_d2 = d3_fid_d2;
        }

        public String getD3_name() {
            return d3_name;
        }

        public void setD3_name(String d3_name) {
            this.d3_name = d3_name;
        }
    }
}
