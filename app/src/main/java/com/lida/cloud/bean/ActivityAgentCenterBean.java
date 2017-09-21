package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 代理中心
 * Created by xkr on 2017/9/7.
 */

public class ActivityAgentCenterBean extends NetResult {


    /**
     * data : [{"memid":83,"type":"省代理","area":"广东省","createtime":1504337500,"avatar":"http://www.yzl.com/static/uploads/20170904/b1ca5f2efd0f603bd5b8419263ab88d5.jpg"}]
     * code : 1
     */

    private List<DataBean> data;

    public static ActivityAgentCenterBean parse(String json) throws AppException {
        ActivityAgentCenterBean res = new ActivityAgentCenterBean();
        try {
            res = gson.fromJson(json, ActivityAgentCenterBean.class);
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
         * memid : 83
         * type : 省代理
         * area : 广东省
         * createtime : 1504337500
         * avatar : http://www.yzl.com/static/uploads/20170904/b1ca5f2efd0f603bd5b8419263ab88d5.jpg
         */

        private int memid;
        private String type;
        private String area;
        private int createtime;
        private String avatar;

        public int getMemid() {
            return memid;
        }

        public void setMemid(int memid) {
            this.memid = memid;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public int getCreatetime() {
            return createtime;
        }

        public void setCreatetime(int createtime) {
            this.createtime = createtime;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }
}
