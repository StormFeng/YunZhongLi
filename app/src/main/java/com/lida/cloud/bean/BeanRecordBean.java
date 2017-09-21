package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

public class BeanRecordBean extends NetResult {

    private List<DataBean> data;

    public static BeanRecordBean parse(String json) throws AppException {
        BeanRecordBean res = new BeanRecordBean();
        try {
            res = gson.fromJson(json, BeanRecordBean.class);
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
         * member_name : root
         * number : 100
         * member_id : 1
         * create_time : 1503546766
         * descipt : 会员消费豆提现申请，冻结100个消费豆
         */

        private String member_name;
        private String number;
        private String member_id;
        private String create_time;
        private String descipt;
        private String type;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getMember_name() {
            return member_name;
        }

        public void setMember_name(String member_name) {
            this.member_name = member_name;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getMember_id() {
            return member_id;
        }

        public void setMember_id(String member_id) {
            this.member_id = member_id;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getDescipt() {
            return descipt;
        }

        public void setDescipt(String descipt) {
            this.descipt = descipt;
        }
    }
}
