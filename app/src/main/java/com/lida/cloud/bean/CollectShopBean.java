package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 收藏的店铺
 * Created by WeiQingFeng on 2017/8/23.
 */

public class CollectShopBean extends NetResult {

    /**
     * data : {"list":[{"selid":29,"sel_memid":160,"selname":null,"selshopname":"泉州台商投资区月亮湾听海休闲吧","selshoptime":null,"selshoptype":"15","selshopadd":"泉州台商投资区滨海大道月亮湾68号","seltel":"18350552288","seldetail":"本店经营：烧烤、下午茶、酒吧、海鲜大排档、娱乐、承接各类中大型party、聚会、酒会等等。","selimage":"[\"20170819\\\\1b62bf26cb39d26d925698321e7f28be.png\",\"20170819\\\\10ead01e0bb287ed46135d7aacfeb152.png\",\"20170819\\\\d510f913a69660364962186683d772b4.png\"]","selemail":"438778291@qq.com","selback":100,"selfname":"吴水宾","selnumber":"350509600252838","selimg1":"2017-07-06/595e05d8b8197.jpg","sort":2017,"logo":"2017-07-06/595e05d8b8939.jpg","seljifen":4579,"seltj":"2","lon":"118.840807826462","dime":"24.882847773086","provincialcity":null,"status":1,"is_open":1,"distant":"472.19km"}],"count":5,"page_num":0,"page_limit":8}
     */

    private List<DataBean> data;

    public static CollectShopBean parse(String json) throws AppException {
        CollectShopBean res = new CollectShopBean();
        try {
            res = gson.fromJson(json, CollectShopBean.class);
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
         * list : [{"selid":29,"sel_memid":160,"selname":null,"selshopname":"泉州台商投资区月亮湾听海休闲吧","selshoptime":null,"selshoptype":"15","selshopadd":"泉州台商投资区滨海大道月亮湾68号","seltel":"18350552288","seldetail":"本店经营：烧烤、下午茶、酒吧、海鲜大排档、娱乐、承接各类中大型party、聚会、酒会等等。","selimage":"[\"20170819\\\\1b62bf26cb39d26d925698321e7f28be.png\",\"20170819\\\\10ead01e0bb287ed46135d7aacfeb152.png\",\"20170819\\\\d510f913a69660364962186683d772b4.png\"]","selemail":"438778291@qq.com","selback":100,"selfname":"吴水宾","selnumber":"350509600252838","selimg1":"2017-07-06/595e05d8b8197.jpg","sort":2017,"logo":"2017-07-06/595e05d8b8939.jpg","seljifen":4579,"seltj":"2","lon":"118.840807826462","dime":"24.882847773086","provincialcity":null,"status":1,"is_open":1,"distant":"472.19km"}]
         * count : 5
         * page_num : 0
         * page_limit : 8
         */

        private String count;
        private String page_num;
        private String page_limit;
        private List<ListBean> list;

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

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

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * selid : 29
             * sel_memid : 160
             * selname : null
             * selshopname : 泉州台商投资区月亮湾听海休闲吧
             * selshoptime : null
             * selshoptype : 15
             * selshopadd : 泉州台商投资区滨海大道月亮湾68号
             * seltel : 18350552288
             * seldetail : 本店经营：烧烤、下午茶、酒吧、海鲜大排档、娱乐、承接各类中大型party、聚会、酒会等等。
             * selimage : ["20170819\\1b62bf26cb39d26d925698321e7f28be.png","20170819\\10ead01e0bb287ed46135d7aacfeb152.png","20170819\\d510f913a69660364962186683d772b4.png"]
             * selemail : 438778291@qq.com
             * selback : 100
             * selfname : 吴水宾
             * selnumber : 350509600252838
             * selimg1 : 2017-07-06/595e05d8b8197.jpg
             * sort : 2017
             * logo : 2017-07-06/595e05d8b8939.jpg
             * seljifen : 4579
             * seltj : 2
             * lon : 118.840807826462
             * dime : 24.882847773086
             * provincialcity : null
             * status : 1
             * is_open : 1
             * distant : 472.19km
             */

            private String col_id;
            private String selid;
            private String sel_memid;
            private String selname;
            private String selshopname;
            private String selshoptime;
            private String selshoptype;
            private String selshopadd;
            private String seltel;
            private String seldetail;
            private String selimage;
            private String selemail;
            private String selback;
            private String selfname;
            private String selnumber;
            private String selimg1;
            private String sort;
            private String logo;
            private String seljifen;
            private String seltj;
            private String lon;
            private String dime;
            private String provincialcity;
            private String status;
            private String is_open;
            private String distant;
            private boolean select;
            private boolean edit;

            public String getCol_id() {
                return col_id;
            }

            public void setCol_id(String col_id) {
                this.col_id = col_id;
            }

            public boolean isSelect() {
                return select;
            }

            public void setSelect(boolean select) {
                this.select = select;
            }

            public boolean isEdit() {
                return edit;
            }

            public void setEdit(boolean edit) {
                this.edit = edit;
            }

            public String getSelid() {
                return selid;
            }

            public void setSelid(String selid) {
                this.selid = selid;
            }

            public String getSel_memid() {
                return sel_memid;
            }

            public void setSel_memid(String sel_memid) {
                this.sel_memid = sel_memid;
            }

            public String getSelname() {
                return selname;
            }

            public void setSelname(String selname) {
                this.selname = selname;
            }

            public String getSelshopname() {
                return selshopname;
            }

            public void setSelshopname(String selshopname) {
                this.selshopname = selshopname;
            }

            public String getSelshoptime() {
                return selshoptime;
            }

            public void setSelshoptime(String selshoptime) {
                this.selshoptime = selshoptime;
            }

            public String getSelshoptype() {
                return selshoptype;
            }

            public void setSelshoptype(String selshoptype) {
                this.selshoptype = selshoptype;
            }

            public String getSelshopadd() {
                return selshopadd;
            }

            public void setSelshopadd(String selshopadd) {
                this.selshopadd = selshopadd;
            }

            public String getSeltel() {
                return seltel;
            }

            public void setSeltel(String seltel) {
                this.seltel = seltel;
            }

            public String getSeldetail() {
                return seldetail;
            }

            public void setSeldetail(String seldetail) {
                this.seldetail = seldetail;
            }

            public String getSelimage() {
                return selimage;
            }

            public void setSelimage(String selimage) {
                this.selimage = selimage;
            }

            public String getSelemail() {
                return selemail;
            }

            public void setSelemail(String selemail) {
                this.selemail = selemail;
            }

            public String getSelback() {
                return selback;
            }

            public void setSelback(String selback) {
                this.selback = selback;
            }

            public String getSelfname() {
                return selfname;
            }

            public void setSelfname(String selfname) {
                this.selfname = selfname;
            }

            public String getSelnumber() {
                return selnumber;
            }

            public void setSelnumber(String selnumber) {
                this.selnumber = selnumber;
            }

            public String getSelimg1() {
                return selimg1;
            }

            public void setSelimg1(String selimg1) {
                this.selimg1 = selimg1;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getSeljifen() {
                return seljifen;
            }

            public void setSeljifen(String seljifen) {
                this.seljifen = seljifen;
            }

            public String getSeltj() {
                return seltj;
            }

            public void setSeltj(String seltj) {
                this.seltj = seltj;
            }

            public String getLon() {
                return lon;
            }

            public void setLon(String lon) {
                this.lon = lon;
            }

            public String getDime() {
                return dime;
            }

            public void setDime(String dime) {
                this.dime = dime;
            }

            public String getProvincialcity() {
                return provincialcity;
            }

            public void setProvincialcity(String provincialcity) {
                this.provincialcity = provincialcity;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getIs_open() {
                return is_open;
            }

            public void setIs_open(String is_open) {
                this.is_open = is_open;
            }

            public String getDistant() {
                return distant;
            }

            public void setDistant(String distant) {
                this.distant = distant;
            }
        }
    }
}
