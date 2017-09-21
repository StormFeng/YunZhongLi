package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 新闻资讯
 * Created by WeiQingFeng on 2017/8/23.
 */

public class NewsListBean extends NetResult {

    /**
     * data : {"list":[{"url":"http://yzl.com/api/news?id=8","title":"云众利消费全返商业系统","description":"云众利消费全返商业系统","time":"2017-09-02 11:13:43","viewcount":0},{"url":"http://yzl.com/api/news?id=4","title":"经营理念","description":"经营理念","time":"2017-09-02 11:13:57","viewcount":0},{"url":"http://yzl.com/api/news?id=2","title":"云众利搭上\u201c互联网+\u201d的顺风车","description":"云众利搭上\u201c互联网+\u201d的顺风车","time":"2017-09-02 11:14:07","viewcount":0},{"url":"http://yzl.com/api/news?id=1","title":"关于我们","description":"关于我们","time":"2017-09-02 11:14:17","viewcount":0}],"page_num":1,"page_limit":10,"count":4}
     */

    private List<DataBean> data;

    public static NewsListBean parse(String json) throws AppException {
        NewsListBean res = new NewsListBean();
        try {
            res = gson.fromJson(json, NewsListBean.class);
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
         * list : [{"url":"http://yzl.com/api/news?id=8","title":"云众利消费全返商业系统","description":"云众利消费全返商业系统","time":"2017-09-02 11:13:43","viewcount":0},{"url":"http://yzl.com/api/news?id=4","title":"经营理念","description":"经营理念","time":"2017-09-02 11:13:57","viewcount":0},{"url":"http://yzl.com/api/news?id=2","title":"云众利搭上\u201c互联网+\u201d的顺风车","description":"云众利搭上\u201c互联网+\u201d的顺风车","time":"2017-09-02 11:14:07","viewcount":0},{"url":"http://yzl.com/api/news?id=1","title":"关于我们","description":"关于我们","time":"2017-09-02 11:14:17","viewcount":0}]
         * page_num : 1
         * page_limit : 10
         * count : 4
         */

        private String page_num;
        private String page_limit;
        private String count;
        private List<ListBean> list;

        public String getPage_num() {
            return page_num;
        }

        public void setPage_num(String page_num) {
            this.page_num = page_num;
        }

        public String getPage_limit() {
            return page_limit;
        }

        public void setPage_limit(String page_limit) {
            this.page_limit = page_limit;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
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
             * url : http://yzl.com/api/news?id=8
             * title : 云众利消费全返商业系统
             * description : 云众利消费全返商业系统
             * time : 2017-09-02 11:13:43
             * viewcount : 0
             */

            private String url;
            private String title;
            private String description;
            private String time;
            private String viewcount;
            private String pic;

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

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getViewcount() {
                return viewcount;
            }

            public void setViewcount(String viewcount) {
                this.viewcount = viewcount;
            }
        }
    }
}
