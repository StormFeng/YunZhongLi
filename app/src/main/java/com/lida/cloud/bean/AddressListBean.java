package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 加密字符串
 * Created by WeiQingFeng on 2017/8/23.
 */

public class AddressListBean extends NetResult {

    /**
     * data : {"list":[{"id":1,"name":"小陈","mobile":"13523533545","province":"广东省","city":"广州市","country":"天河区","detail":"车陂","isdefault":0,"user_id":83,"update_time":1503111605},{"id":7,"name":"周小","mobile":"13523533545","province":"广东省","city":"广州市","country":"天河区","detail":"上社","isdefault":0,"user_id":83,"update_time":1503112010}]}
     */

    private List<DataBean> data;

    public static AddressListBean parse(String json) throws AppException {
        AddressListBean res = new AddressListBean();
        try {
            res = gson.fromJson(json, AddressListBean.class);
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
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean extends NetResult{
            /**
             * id : 1
             * name : 小陈
             * mobile : 13523533545
             * province : 广东省
             * city : 广州市
             * country : 天河区
             * detail : 车陂
             * isdefault : 0
             * user_id : 83
             * update_time : 1503111605
             */

            private String id;
            private String name;
            private String mobile;
            private String province;
            private String city;
            private String country;
            private String detail;
            private String isdefault;
            private String user_id;
            private String update_time;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
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

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public String getDetail() {
                return detail;
            }

            public void setDetail(String detail) {
                this.detail = detail;
            }

            public String getIsdefault() {
                return isdefault;
            }

            public void setIsdefault(String isdefault) {
                this.isdefault = isdefault;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(String update_time) {
                this.update_time = update_time;
            }
        }
    }
}
