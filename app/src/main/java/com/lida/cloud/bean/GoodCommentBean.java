package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 商品评价
 * Created by WeiQingFeng on 2017/8/23.
 */

public class GoodCommentBean extends NetResult {

    /**
     * data : {"list":[{"images":["http://yzl.gzldrj.com/static/goods_comment/20170831/4cb1ecb5ed69bf1c82a2dc483d4727b1.gif","http://yzl.gzldrj.com/static/goods_comment/20170831/af8965ba94757e97de971be983e9c68c.gif","http://yzl.gzldrj.com/static/goods_comment/20170831/75ada4d45938b7a26e0ca719bcd085b3.gif"],"member_name":"r***","member_thumb":"","spec":"无规格","content":"这是极好的","create_time":"2017-08-31 15:54:23"}],"page_num":1,"page_limit":10,"count":1}
     */

    private List<DataBean> data;

    public static GoodCommentBean parse(String json) throws AppException {
        GoodCommentBean res = new GoodCommentBean();
        try {
            res = gson.fromJson(json, GoodCommentBean.class);
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
         * list : [{"images":["http://yzl.gzldrj.com/static/goods_comment/20170831/4cb1ecb5ed69bf1c82a2dc483d4727b1.gif","http://yzl.gzldrj.com/static/goods_comment/20170831/af8965ba94757e97de971be983e9c68c.gif","http://yzl.gzldrj.com/static/goods_comment/20170831/75ada4d45938b7a26e0ca719bcd085b3.gif"],"member_name":"r***","member_thumb":"","spec":"无规格","content":"这是极好的","create_time":"2017-08-31 15:54:23"}]
         * page_num : 1
         * page_limit : 10
         * count : 1
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
             * images : ["http://yzl.gzldrj.com/static/goods_comment/20170831/4cb1ecb5ed69bf1c82a2dc483d4727b1.gif","http://yzl.gzldrj.com/static/goods_comment/20170831/af8965ba94757e97de971be983e9c68c.gif","http://yzl.gzldrj.com/static/goods_comment/20170831/75ada4d45938b7a26e0ca719bcd085b3.gif"]
             * member_name : r***
             * member_thumb :
             * spec : 无规格
             * content : 这是极好的
             * create_time : 2017-08-31 15:54:23
             */

            private String member_name;
            private String member_thumb;
            private String spec;
            private String content;
            private String create_time;
            private List<String> images;

            public String getMember_name() {
                return member_name;
            }

            public void setMember_name(String member_name) {
                this.member_name = member_name;
            }

            public String getMember_thumb() {
                return member_thumb;
            }

            public void setMember_thumb(String member_thumb) {
                this.member_thumb = member_thumb;
            }

            public String getSpec() {
                return spec;
            }

            public void setSpec(String spec) {
                this.spec = spec;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public List<String> getImages() {
                return images;
            }

            public void setImages(List<String> images) {
                this.images = images;
            }
        }
    }
}
