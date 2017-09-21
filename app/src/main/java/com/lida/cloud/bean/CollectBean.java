package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 收藏
 * Created by WeiQingFeng on 2017/8/23.
 */

public class CollectBean extends NetResult {

    /**
     * data : {"state":1}
     */

    private List<DataBean> data;

    public static CollectBean parse(String json) throws AppException {
        CollectBean res = new CollectBean();
        try {
            res = gson.fromJson(json, CollectBean.class);
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
         * state : 1
         */

        private int state;

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }
    }
}
