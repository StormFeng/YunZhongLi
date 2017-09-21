package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 物流信息
 * Created by WeiQingFeng on 2017/8/23.
 */

public class LogisticsBean extends NetResult {

    private List<DataBean> data;

    public static LogisticsBean parse(String json) throws AppException {
        LogisticsBean res = new LogisticsBean();
        try {
            res = gson.fromJson(json, LogisticsBean.class);
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
         * time : 2017-08-20 07:48:17
         * ftime : 2017-08-20 07:48:17
         * context : 快件已到达【广东广州车陂】 扫描员是【广州车陂】上一站是【】
         * location :
         */

        private String time;
        private String ftime;
        private String context;
        private String location;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getFtime() {
            return ftime;
        }

        public void setFtime(String ftime) {
            this.ftime = ftime;
        }

        public String getContext() {
            return context;
        }

        public void setContext(String context) {
            this.context = context;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }
    }
}
