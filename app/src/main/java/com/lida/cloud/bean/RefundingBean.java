package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 退款详情
 * Created by WeiQingFeng on 2017/8/23.
 */

public class RefundingBean extends NetResult {

    private List<DataBean> data;

    public static RefundingBean parse(String json) throws AppException {
        RefundingBean res = new RefundingBean();
        try {
            res = gson.fromJson(json, RefundingBean.class);
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
         * refund : {"refund_id":1,"memid":83,"create_time":1504009746,"status":"0","order_id":110,"type":"1","content":"撒旦撒","reply":"","express":"申通","express_sn":"123456","express_code":"shentong"}
         * order : {"status":"0","price":"600.00"}
         * address : {"address":"退货地址","name":"退货人","mobile":"135"}
         */

        private RefundBean refund;
        private OrderBean order;
        private AddressBean address;

        public RefundBean getRefund() {
            return refund;
        }

        public void setRefund(RefundBean refund) {
            this.refund = refund;
        }

        public OrderBean getOrder() {
            return order;
        }

        public void setOrder(OrderBean order) {
            this.order = order;
        }

        public AddressBean getAddress() {
            return address;
        }

        public void setAddress(AddressBean address) {
            this.address = address;
        }

        public static class RefundBean {
            /**
             * refund_id : 1
             * memid : 83
             * create_time : 1504009746
             * status : 0
             * order_id : 110
             * type : 1
             * content : 撒旦撒
             * reply : 
             * express : 申通
             * express_sn : 123456
             * express_code : shentong
             */

            private String refund_id;
            private String memid;
            private String create_time;
            private String status;
            private String order_id;
            private String type;
            private String content;
            private String reply;
            private String express;
            private String express_sn;
            private String express_code;

            public String getRefund_id() {
                return refund_id;
            }

            public void setRefund_id(String refund_id) {
                this.refund_id = refund_id;
            }

            public String getMemid() {
                return memid;
            }

            public void setMemid(String memid) {
                this.memid = memid;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getReply() {
                return reply;
            }

            public void setReply(String reply) {
                this.reply = reply;
            }

            public String getExpress() {
                return express;
            }

            public void setExpress(String express) {
                this.express = express;
            }

            public String getExpress_sn() {
                return express_sn;
            }

            public void setExpress_sn(String express_sn) {
                this.express_sn = express_sn;
            }

            public String getExpress_code() {
                return express_code;
            }

            public void setExpress_code(String express_code) {
                this.express_code = express_code;
            }
        }

        public static class OrderBean {
            /**
             * status : 0
             * price : 600.00
             */

            private String status;
            private String price;

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }
        }

        public static class AddressBean {
            /**
             * address : 退货地址
             * name : 退货人
             * mobile : 135
             */

            private String address;
            private String name;
            private String mobile;

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
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
        }
    }
}
