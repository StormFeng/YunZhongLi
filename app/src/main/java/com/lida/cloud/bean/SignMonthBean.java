package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 签到月
 * Created by WeiQingFeng on 2017/8/23.
 */

public class SignMonthBean extends NetResult {

    private List<DataBean> data;

    public static SignMonthBean parse(String json) throws AppException {
        SignMonthBean res = new SignMonthBean();
        try {
            res = gson.fromJson(json, SignMonthBean.class);
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
        private List<String> days;
        private String continuity_days;
        private String today_get_credit;
        private String mem_credit;
        private boolean is_sign;

        public String getContinuity_days() {
            return continuity_days;
        }

        public void setContinuity_days(String continuity_days) {
            this.continuity_days = continuity_days;
        }

        public String getToday_get_credit() {
            return today_get_credit;
        }

        public void setToday_get_credit(String today_get_credit) {
            this.today_get_credit = today_get_credit;
        }

        public String getMem_credit() {
            return mem_credit;
        }

        public void setMem_credit(String mem_credit) {
            this.mem_credit = mem_credit;
        }

        public boolean is_sign() {
            return is_sign;
        }

        public void setIs_sign(boolean is_sign) {
            this.is_sign = is_sign;
        }

        public List<String> getDays() {
            return days;
        }

        public void setDays(List<String> days) {
            this.days = days;
        }
    }
}
