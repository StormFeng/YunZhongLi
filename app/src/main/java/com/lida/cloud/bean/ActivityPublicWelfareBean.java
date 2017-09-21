package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 粉丝公益列表
 * Created by xkr on 2017/9/8.
 */

public class ActivityPublicWelfareBean extends NetResult {

    /**
     * data : [{"list":[{"pubid":2,"pic":"http://www.yzl.com/static/uploads/20170906/f098e9575fd8cf225c4887347fbbead6.jpg","content":"<p>阿斯达少废话焚膏继晷看<\/p>","title":"小明子","des":"12312 12412412213","createtime":1504686680,"count":0,"status":1,"url":"http://www.yzl.com/api/welfare?id=2"},{"pubid":7,"pic":null,"content":"<p>3123<\/p>","title":"123","des":"123","createtime":1504682378,"count":123,"status":1,"url":"http://www.yzl.com/api/welfare?id=7"}],"page_num":1,"page_limit":10,"count":2}]
     * code : 1
     */

    private List<DataBean> data;

    public static ActivityPublicWelfareBean parse(String json) throws AppException {
        ActivityPublicWelfareBean res = new ActivityPublicWelfareBean();
        try {
            res = gson.fromJson(json, ActivityPublicWelfareBean.class);
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
         * list : [{"pubid":2,"pic":"http://www.yzl.com/static/uploads/20170906/f098e9575fd8cf225c4887347fbbead6.jpg","content":"<p>阿斯达少废话焚膏继晷看<\/p>","title":"小明子","des":"12312 12412412213","createtime":1504686680,"count":0,"status":1,"url":"http://www.yzl.com/api/welfare?id=2"},{"pubid":7,"pic":null,"content":"<p>3123<\/p>","title":"123","des":"123","createtime":1504682378,"count":123,"status":1,"url":"http://www.yzl.com/api/welfare?id=7"}]
         * page_num : 1
         * page_limit : 10
         * count : 2
         */

        private int page_num;
        private int page_limit;
        private int count;
        private List<ListBean> list;

        public int getPage_num() {
            return page_num;
        }

        public void setPage_num(int page_num) {
            this.page_num = page_num;
        }

        public int getPage_limit() {
            return page_limit;
        }

        public void setPage_limit(int page_limit) {
            this.page_limit = page_limit;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean extends NetResult{
            /**
             * pubid : 2
             * pic : http://www.yzl.com/static/uploads/20170906/f098e9575fd8cf225c4887347fbbead6.jpg
             * content : <p>阿斯达少废话焚膏继晷看</p>
             * title : 小明子
             * des : 12312 12412412213
             * createtime : 1504686680
             * count : 0
             * status : 1
             * url : http://www.yzl.com/api/welfare?id=2
             */

            private int pubid;
            private String pic;
            private String content;
            private String title;
            private String des;
            private int createtime;
            private String count;
            private int status;
            private String url;

            public int getPubid() {
                return pubid;
            }

            public void setPubid(int pubid) {
                this.pubid = pubid;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDes() {
                return des;
            }

            public void setDes(String des) {
                this.des = des;
            }

            public int getCreatetime() {
                return createtime;
            }

            public void setCreatetime(int createtime) {
                this.createtime = createtime;
            }

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
