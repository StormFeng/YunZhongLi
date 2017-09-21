package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 加密字符串
 * Created by WeiQingFeng on 2017/8/23.
 */

public class BusinessDataBean extends NetResult {

    /**
     * data : {"data":{"selid":353,"sel_memid":83,"selname":null,"selshopname":"小小","selshoptime":null,"selshoptype":"15","selshopadd":null,"seltel":"13523533545","seldetail":null,"selimage":null,"selemail":null,"selback":100,"selfname":"晨晨","selnumber":"12312312312","selimg1":null,"sort":0,"logo":null,"seljifen":0,"seltj":"1","lon":null,"dime":null,"provincialcity":null,"status":0,"is_open":0}}
     */

    private List<DataBeanX> data;

    public static BusinessDataBean parse(String json) throws AppException {
        BusinessDataBean res = new BusinessDataBean();
        try {
            res = gson.fromJson(json, BusinessDataBean.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            throw AppException.json(e);
        }
        return res;
    }

    public List<DataBeanX> getData() {
        return data;
    }

    public void setData(List<DataBeanX> data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * data : {"selid":353,"sel_memid":83,"selname":null,"selshopname":"小小","selshoptime":null,"selshoptype":"15","selshopadd":null,"seltel":"13523533545","seldetail":null,"selimage":null,"selemail":null,"selback":100,"selfname":"晨晨","selnumber":"12312312312","selimg1":null,"sort":0,"logo":null,"seljifen":0,"seltj":"1","lon":null,"dime":null,"provincialcity":null,"status":0,"is_open":0}
         */

        private DataBean data;

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * selid : 353
             * sel_memid : 83
             * selname : null
             * selshopname : 小小
             * selshoptime : null
             * selshoptype : 15
             * selshopadd : null
             * seltel : 13523533545
             * seldetail : null
             * selimage : null
             * selemail : null
             * selback : 100
             * selfname : 晨晨
             * selnumber : 12312312312
             * selimg1 : null
             * sort : 0
             * logo : null
             * seljifen : 0
             * seltj : 1
             * lon : null
             * dime : null
             * provincialcity : null
             * status : 0
             * is_open : 0
             */

            private String sel_memid;
            private String selname;
            private String selshopname;
            private String selshoptime;
            private String selshoptype;
            private String selshopadd;
            private String seltel;
            private String seldetail;
            private String[] selimage;
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
            private String province;
            private String city;
            private String county;
            private String returnaddress;
            private String returnname;
            private String returnmobile;
            private String intro;
            private String typename;
            private String is_open;


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

            public String[] getSelimage() {
                return selimage;
            }

            public void setSelimage(String[] selimage) {
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

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getCounty() {
                return county;
            }

            public void setCounty(String county) {
                this.county = county;
            }

            public String getReturnaddress() {
                return returnaddress;
            }

            public void setReturnaddress(String returnaddress) {
                this.returnaddress = returnaddress;
            }

            public String getReturnname() {
                return returnname;
            }

            public void setReturnname(String returnname) {
                this.returnname = returnname;
            }

            public String getReturnmobile() {
                return returnmobile;
            }

            public void setReturnmobile(String returnmobile) {
                this.returnmobile = returnmobile;
            }

            public String getIntro() {
                return intro;
            }

            public void setIntro(String intro) {
                this.intro = intro;
            }

            public String getTypename() {
                return typename;
            }

            public void setTypename(String typename) {
                this.typename = typename;
            }
        }
    }
}
