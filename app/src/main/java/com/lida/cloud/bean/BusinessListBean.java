package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 店铺列表
 * Created by WeiQingFeng on 2016/10/28 0028.
 */

public class BusinessListBean extends NetResult{


    /**
     * data : {"store":[{"selid":29,"selshoptype":"15","logo":"http://www.yzl.compublic\\static\\license\\2017-07-06/595e05d8b8939.jpg","selshopname":"泉州台商投资区月亮湾听海休闲吧","provincialcity":null,"lat":"24.882847773086","lng":"118.840807826462","typename":"食品","distant":"472.19km"},{"selid":43,"selshoptype":"15","logo":"http://www.yzl.compublic\\static\\license\\2017-07-22/5973437dcceb9.jpg","selshopname":"嘉佳","provincialcity":null,"lat":"25.04474734127379","lng":"118.4896946976587","typename":"食品","distant":"511.93km"}],"page":0}
     */

    private List<DataBean> data;

    public static BusinessListBean parse(String json) throws AppException {
        BusinessListBean res = new BusinessListBean();
        try {
            res = gson.fromJson(json, BusinessListBean.class);
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
         * store : [{"selid":29,"selshoptype":"15","logo":"http://www.yzl.compublic\\static\\license\\2017-07-06/595e05d8b8939.jpg","selshopname":"泉州台商投资区月亮湾听海休闲吧","provincialcity":null,"lat":"24.882847773086","lng":"118.840807826462","typename":"食品","distant":"472.19km"},{"selid":43,"selshoptype":"15","logo":"http://www.yzl.compublic\\static\\license\\2017-07-22/5973437dcceb9.jpg","selshopname":"嘉佳","provincialcity":null,"lat":"25.04474734127379","lng":"118.4896946976587","typename":"食品","distant":"511.93km"}]
         * page : 0
         */

        private int page;
        private List<StoreBean> store;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public List<StoreBean> getStore() {
            return store;
        }

        public void setStore(List<StoreBean> store) {
            this.store = store;
        }

        public static class StoreBean extends NetResult{
            /**
             * selid : 29
             * selshoptype : 15
             * logo : http://www.yzl.compublic\static\license\2017-07-06/595e05d8b8939.jpg
             * selshopname : 泉州台商投资区月亮湾听海休闲吧
             * provincialcity : null
             * lat : 24.882847773086
             * lng : 118.840807826462
             * typename : 食品
             * distant : 472.19km
             */

            private int selid;
            private String selshoptype;
            private String logo;
            private String selshopname;
            private String provincialcity;
            private String lat;
            private String lng;
            private String typename;
            private String distant;

            public int getSelid() {
                return selid;
            }

            public void setSelid(int selid) {
                this.selid = selid;
            }

            public String getSelshoptype() {
                return selshoptype;
            }

            public void setSelshoptype(String selshoptype) {
                this.selshoptype = selshoptype;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getSelshopname() {
                return selshopname;
            }

            public void setSelshopname(String selshopname) {
                this.selshopname = selshopname;
            }

            public String getProvincialcity() {
                return provincialcity;
            }

            public void setProvincialcity(String provincialcity) {
                this.provincialcity = provincialcity;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getLng() {
                return lng;
            }

            public void setLng(String lng) {
                this.lng = lng;
            }

            public String getTypename() {
                return typename;
            }

            public void setTypename(String typename) {
                this.typename = typename;
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
