package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 商品详情
 * Created by WeiQingFeng on 2016/10/28 0028.
 */

public class ActivityShopDetailBean extends NetResult{

    /**
     * data : {"store":{"typename":"食品","selid":6,"sel_memid":106,"selname":"林星福","selshopname":"游乐城","selshoptime":null,"selshoptype":"15","selshopadd":"温陵南路92号福华商业中心15层A～D","seltel":"13505050508","seldetail":"广州市安航贸易发展有限公司，2009年12月10日成立，经营范围包括预包装食品批发;散装食品批发;乳制品批发。","selimage":["20170819\\1b62bf26cb39d26d925698321e7f28be.png","20170819\\10ead01e0bb287ed46135d7aacfeb152.png","20170819\\d510f913a69660364962186683d772b4.png"],"selemail":null,"selback":100,"selfname":"元乙","selnumber":"4667674999476","selimg1":"","sort":0,"logo":"","seljifen":8600,"seltj":"1","lon":"113.328343133","dime":"23.1283200752","provincialcity":"广东省广州市天河区","status":1,"is_open":1},"collect":0}
     */

    private List<DataBean> data;

    public static ActivityShopDetailBean parse(String json) throws AppException {
        ActivityShopDetailBean res = new ActivityShopDetailBean();
        try {
            res = gson.fromJson(json, ActivityShopDetailBean.class);
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
         * store : {"typename":"食品","selid":6,"sel_memid":106,"selname":"林星福","selshopname":"游乐城","selshoptime":null,"selshoptype":"15","selshopadd":"温陵南路92号福华商业中心15层A～D","seltel":"13505050508","seldetail":"广州市安航贸易发展有限公司，2009年12月10日成立，经营范围包括预包装食品批发;散装食品批发;乳制品批发。","selimage":["20170819\\1b62bf26cb39d26d925698321e7f28be.png","20170819\\10ead01e0bb287ed46135d7aacfeb152.png","20170819\\d510f913a69660364962186683d772b4.png"],"selemail":null,"selback":100,"selfname":"元乙","selnumber":"4667674999476","selimg1":"","sort":0,"logo":"","seljifen":8600,"seltj":"1","lon":"113.328343133","dime":"23.1283200752","provincialcity":"广东省广州市天河区","status":1,"is_open":1}
         * collect : 0
         */

        private StoreBean store;
        private String collect;

        public StoreBean getStore() {
            return store;
        }

        public void setStore(StoreBean store) {
            this.store = store;
        }

        public String getCollect() {
            return collect;
        }

        public void setCollect(String collect) {
            this.collect = collect;
        }

        public static class StoreBean {
            /**
             * typename : 食品
             * selid : 6
             * sel_memid : 106
             * selname : 林星福
             * selshopname : 游乐城
             * selshoptime : null
             * selshoptype : 15
             * selshopadd : 温陵南路92号福华商业中心15层A～D
             * seltel : 13505050508
             * seldetail : 广州市安航贸易发展有限公司，2009年12月10日成立，经营范围包括预包装食品批发;散装食品批发;乳制品批发。
             * selimage : ["20170819\\1b62bf26cb39d26d925698321e7f28be.png","20170819\\10ead01e0bb287ed46135d7aacfeb152.png","20170819\\d510f913a69660364962186683d772b4.png"]
             * selemail : null
             * selback : 100
             * selfname : 元乙
             * selnumber : 4667674999476
             * selimg1 :
             * sort : 0
             * logo :
             * seljifen : 8600
             * seltj : 1
             * lon : 113.328343133
             * dime : 23.1283200752
             * provincialcity : 广东省广州市天河区
             * status : 1
             * is_open : 1
             */

            private String typename;
            private String selid;
            private String sel_memid;
            private String selname;
            private String selshopname;
            private String selshoptime;
            private String selshoptype;
            private String selshopadd;
            private String seltel;
            private String seldetail;
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
            private List<String> selimage;

            public String getTypename() {
                return typename;
            }

            public void setTypename(String typename) {
                this.typename = typename;
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

            public List<String> getSelimage() {
                return selimage;
            }

            public void setSelimage(List<String> selimage) {
                this.selimage = selimage;
            }
        }
    }
}
