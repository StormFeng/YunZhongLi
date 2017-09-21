package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 实名认证状态
 * Created by WeiQingFeng on 2017/8/23.
 */

public class MemberIsrealBean extends NetResult {

    private List<DataBean> data;

    public static MemberIsrealBean parse(String json) throws AppException {
        MemberIsrealBean res = new MemberIsrealBean();
        try {
            res = gson.fromJson(json, MemberIsrealBean.class);
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
         * status : 1
         */

        private int status;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
