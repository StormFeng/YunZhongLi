package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 个人中心
 * Created by WeiQingFeng on 2017/8/23.
 */

public class PersonalInfoBean extends NetResult {

    private List<DataBean> data;

    public static PersonalInfoBean parse(String json) throws AppException {
        PersonalInfoBean res = new PersonalInfoBean();
        try {
            res = gson.fromJson(json, PersonalInfoBean.class);
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
         * mem_name : lihui123
         * province : 广东省
         * city : 广州市
         * county : 天河区
         * mem_pay_beans : 0
         * pri_back : null
         * memid : 356
         * mem_hname :
         * mem_tname : 666
         * mem_tx : http://www.yzl.com/static/qrcode/356.png
         * mem_state : 0
         * mem_tel : 15899959195
         * mem_nc : 我的昵称
         * mem_sex : 男
         * mem_add : 车陂会的商业大厦
         * mem_email : 1111@qq.com
         * mem_jifen : 1000
         * mem_credit : 1000
         * mem_yue : 0
         * mem_noyue : 0
         * qrcode : http://www.yzl.com/static/qrcode/356.png
         */

        private String mem_name;
        private String province;
        private String city;
        private String county;
        private String mem_pay_beans;
        private String pri_back;
        private String memid;
        private String mem_hname;
        private String mem_tname;
        private String mem_tx;
        private String mem_state;
        private String mem_tel;
        private String mem_nc;
        private String mem_sex;
        private String mem_add;
        private String mem_email;
        private String mem_jifen;
        private String mem_credit;
        private String mem_yue;
        private String mem_noyue;
        private String qrcode;

        public String getMem_name() {
            return mem_name;
        }

        public void setMem_name(String mem_name) {
            this.mem_name = mem_name;
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

        public String getMem_pay_beans() {
            return mem_pay_beans;
        }

        public void setMem_pay_beans(String mem_pay_beans) {
            this.mem_pay_beans = mem_pay_beans;
        }

        public String getPri_back() {
            return pri_back;
        }

        public void setPri_back(String pri_back) {
            this.pri_back = pri_back;
        }

        public String getMemid() {
            return memid;
        }

        public void setMemid(String memid) {
            this.memid = memid;
        }

        public String getMem_hname() {
            return mem_hname;
        }

        public void setMem_hname(String mem_hname) {
            this.mem_hname = mem_hname;
        }

        public String getMem_tname() {
            return mem_tname;
        }

        public void setMem_tname(String mem_tname) {
            this.mem_tname = mem_tname;
        }

        public String getMem_tx() {
            return mem_tx;
        }

        public void setMem_tx(String mem_tx) {
            this.mem_tx = mem_tx;
        }

        public String getMem_state() {
            return mem_state;
        }

        public void setMem_state(String mem_state) {
            this.mem_state = mem_state;
        }

        public String getMem_tel() {
            return mem_tel;
        }

        public void setMem_tel(String mem_tel) {
            this.mem_tel = mem_tel;
        }

        public String getMem_nc() {
            return mem_nc;
        }

        public void setMem_nc(String mem_nc) {
            this.mem_nc = mem_nc;
        }

        public String getMem_sex() {
            return mem_sex;
        }

        public void setMem_sex(String mem_sex) {
            this.mem_sex = mem_sex;
        }

        public String getMem_add() {
            return mem_add;
        }

        public void setMem_add(String mem_add) {
            this.mem_add = mem_add;
        }

        public String getMem_email() {
            return mem_email;
        }

        public void setMem_email(String mem_email) {
            this.mem_email = mem_email;
        }

        public String getMem_jifen() {
            return mem_jifen;
        }

        public void setMem_jifen(String mem_jifen) {
            this.mem_jifen = mem_jifen;
        }

        public String getMem_credit() {
            return mem_credit;
        }

        public void setMem_credit(String mem_credit) {
            this.mem_credit = mem_credit;
        }

        public String getMem_yue() {
            return mem_yue;
        }

        public void setMem_yue(String mem_yue) {
            this.mem_yue = mem_yue;
        }

        public String getMem_noyue() {
            return mem_noyue;
        }

        public void setMem_noyue(String mem_noyue) {
            this.mem_noyue = mem_noyue;
        }

        public String getQrcode() {
            return qrcode;
        }

        public void setQrcode(String qrcode) {
            this.qrcode = qrcode;
        }
    }
}
