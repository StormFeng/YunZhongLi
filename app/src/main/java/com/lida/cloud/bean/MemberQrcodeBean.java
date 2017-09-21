package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

/**
 * 登录
 * Created by WeiQingFeng on 2017/8/23.
 */

public class MemberQrcodeBean extends NetResult {

    /**
     * data : {"member":{"mem_nc":"我的昵称","mem_name":"我的姓名","memid":"我的会员ID","mem_tel":"我的手机"},"qrcode":"http://www.yzl.com/static/qrcode/1.png"}
     */

    private DataBean data;

    public static MemberQrcodeBean parse(String json) throws AppException {
        MemberQrcodeBean res = new MemberQrcodeBean();
        try {
            res = gson.fromJson(json, MemberQrcodeBean.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            throw AppException.json(e);
        }
        return res;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * member : {"mem_nc":"我的昵称","mem_name":"我的姓名","memid":"我的会员ID","mem_tel":"我的手机"}
         * qrcode : http://www.yzl.com/static/qrcode/1.png
         */

        private MemberBean member;
        private String qrcode;

        public MemberBean getMember() {
            return member;
        }

        public void setMember(MemberBean member) {
            this.member = member;
        }

        public String getQrcode() {
            return qrcode;
        }

        public void setQrcode(String qrcode) {
            this.qrcode = qrcode;
        }

        public static class MemberBean {
            /**
             * mem_nc : 我的昵称
             * mem_name : 我的姓名
             * memid : 我的会员ID
             * mem_tel : 我的手机
             */

            private String mem_nc;
            private String mem_name;
            private String memid;
            private String mem_tel;

            public String getMem_nc() {
                return mem_nc;
            }

            public void setMem_nc(String mem_nc) {
                this.mem_nc = mem_nc;
            }

            public String getMem_name() {
                return mem_name;
            }

            public void setMem_name(String mem_name) {
                this.mem_name = mem_name;
            }

            public String getMemid() {
                return memid;
            }

            public void setMemid(String memid) {
                this.memid = memid;
            }

            public String getMem_tel() {
                return mem_tel;
            }

            public void setMem_tel(String mem_tel) {
                this.mem_tel = mem_tel;
            }
        }
    }
}
