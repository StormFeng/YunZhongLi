package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 粉丝公益轮播图
 * Created by xkr on 2017/9/8.
 */

public class ActivityPublicWelfareBannerBean extends NetResult {


    /**
     * data : [{"id":1,"pic":"http://www.yzl.com/static/uploads/20170906/aee8ee85e8039ff2c96ce67ee31e97ff.jpg","url":"http://www.baidu.com","title":"这是标题","displayorder":12,"status":1},{"id":3,"pic":"http://www.yzl.com/static/uploads/20170906/25932d724cb54d4254e52f0434b523bd.jpg","url":"http://www.qq.com","title":"这是标题2","displayorder":1,"status":1}]
     * code : 1
     */

    private List<DataBean> data;

    public static ActivityPublicWelfareBannerBean parse(String json) throws AppException {
        ActivityPublicWelfareBannerBean res = new ActivityPublicWelfareBannerBean();
        try {
            res = gson.fromJson(json, ActivityPublicWelfareBannerBean.class);
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
         * id : 1
         * pic : http://www.yzl.com/static/uploads/20170906/aee8ee85e8039ff2c96ce67ee31e97ff.jpg
         * url : http://www.baidu.com
         * title : 这是标题
         * displayorder : 12
         * status : 1
         */

        private int id;
        private String pic;
        private String url;
        private String title;
        private int displayorder;
        private int status;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getDisplayorder() {
            return displayorder;
        }

        public void setDisplayorder(int displayorder) {
            this.displayorder = displayorder;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
