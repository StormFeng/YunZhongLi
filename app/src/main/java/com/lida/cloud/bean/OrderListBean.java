package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 订单列表
 * Created by WeiQingFeng on 2017/8/23.
 */

public class OrderListBean extends NetResult {

    /**
     * data : {"order":[{"order_sn":"SH201708251045479855","status":"1","iscomment":0,"order_id":111,"shopid":6,"statestr":"待发货","shopname":"游乐城","remind":"1","goods":[{"goods_name":"华为","goods_num":11,"market_price":"11.00","cost_price":"0.00","spec_name":"无规格","goods_image":"http://www.yzl.com/static/goods/20170825/acd0a8b73d6b279eb21ceeb72a7cf3b0.jpg"}]},{"order_sn":"SH201708251045479854","status":"3","iscomment":0,"order_id":110,"shopid":0,"statestr":"待评价","shopname":"平台自营","goods":[{"goods_name":"小米6","goods_num":11,"market_price":"300.00","cost_price":"0.00","spec_name":"无规格","goods_image":"http://www.yzl.com/static/goods/20170825/0035473193839c5729b5ef80f25dabbe.jpg"}]},{"order_sn":"SH201708251006475548","status":"3","iscomment":0,"order_id":106,"shopid":6,"statestr":"待评价","shopname":"游乐城","goods":[{"goods_name":"华为","goods_num":10,"market_price":"11.00","cost_price":"0.00","spec_name":"无规格","goods_image":"http://www.yzl.com/static/goods/20170825/acd0a8b73d6b279eb21ceeb72a7cf3b0.jpg"}]},{"order_sn":"SH201708251006349752","status":"3","iscomment":0,"order_id":105,"shopid":6,"statestr":"待评价","shopname":"游乐城","goods":[{"goods_name":"小米6","goods_num":20,"market_price":"300.00","cost_price":"0.00","spec_name":"无规格","goods_image":"http://www.yzl.com/static/goods/20170825/0035473193839c5729b5ef80f25dabbe.jpg"}]}],"page_num":1,"page_limit":8,"count":4}
     */

    private List<DataBean> data;

    public static OrderListBean parse(String json) throws AppException {
        OrderListBean res = new OrderListBean();
        try {
            res = gson.fromJson(json, OrderListBean.class);
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
         * order : [{"order_sn":"SH201708251045479855","status":"1","iscomment":0,"order_id":111,"shopid":6,"statestr":"待发货","shopname":"游乐城","remind":"1","goods":[{"goods_name":"华为","goods_num":11,"market_price":"11.00","cost_price":"0.00","spec_name":"无规格","goods_image":"http://www.yzl.com/static/goods/20170825/acd0a8b73d6b279eb21ceeb72a7cf3b0.jpg"}]},{"order_sn":"SH201708251045479854","status":"3","iscomment":0,"order_id":110,"shopid":0,"statestr":"待评价","shopname":"平台自营","goods":[{"goods_name":"小米6","goods_num":11,"market_price":"300.00","cost_price":"0.00","spec_name":"无规格","goods_image":"http://www.yzl.com/static/goods/20170825/0035473193839c5729b5ef80f25dabbe.jpg"}]},{"order_sn":"SH201708251006475548","status":"3","iscomment":0,"order_id":106,"shopid":6,"statestr":"待评价","shopname":"游乐城","goods":[{"goods_name":"华为","goods_num":10,"market_price":"11.00","cost_price":"0.00","spec_name":"无规格","goods_image":"http://www.yzl.com/static/goods/20170825/acd0a8b73d6b279eb21ceeb72a7cf3b0.jpg"}]},{"order_sn":"SH201708251006349752","status":"3","iscomment":0,"order_id":105,"shopid":6,"statestr":"待评价","shopname":"游乐城","goods":[{"goods_name":"小米6","goods_num":20,"market_price":"300.00","cost_price":"0.00","spec_name":"无规格","goods_image":"http://www.yzl.com/static/goods/20170825/0035473193839c5729b5ef80f25dabbe.jpg"}]}]
         * page_num : 1
         * page_limit : 8
         * count : 4
         */

        private String page_num;
        private String page_limit;
        private String count;
        private List<OrderBean> order;

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

        public List<OrderBean> getOrder() {
            return order;
        }

        public void setOrder(List<OrderBean> order) {
            this.order = order;
        }

        public static class OrderBean {
            /**
             * order_sn : SH201708251045479855
             * status : 1
             * iscomment : 0
             * order_id : 111
             * shopid : 6
             * statestr : 待发货
             * shopname : 游乐城
             * remind : 1
             * goods : [{"goods_name":"华为","goods_num":11,"market_price":"11.00","cost_price":"0.00","spec_name":"无规格","goods_image":"http://www.yzl.com/static/goods/20170825/acd0a8b73d6b279eb21ceeb72a7cf3b0.jpg"}]
             */

            private String refund_status;
            private String refundid;
            private String order_sn;
            private String status;
            private String iscomment;
            private String order_id;
            private String shopid;
            private String statestr;
            private String shopname;
            private String remind;
            private List<GoodsBean> goods;
            private String totalprice;

            public String getRefundid() {
                return refundid;
            }

            public void setRefundid(String refundid) {
                this.refundid = refundid;
            }

            public String getRefund_status() {
                return refund_status;
            }

            public void setRefund_status(String refund_status) {
                this.refund_status = refund_status;
            }

            public String getTotalprice() {
                return totalprice;
            }

            public void setTotalprice(String totalprice) {
                this.totalprice = totalprice;
            }

            public String getOrder_sn() {
                return order_sn;
            }

            public void setOrder_sn(String order_sn) {
                this.order_sn = order_sn;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getIscomment() {
                return iscomment;
            }

            public void setIscomment(String iscomment) {
                this.iscomment = iscomment;
            }

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public String getShopid() {
                return shopid;
            }

            public void setShopid(String shopid) {
                this.shopid = shopid;
            }

            public String getStatestr() {
                return statestr;
            }

            public void setStatestr(String statestr) {
                this.statestr = statestr;
            }

            public String getShopname() {
                return shopname;
            }

            public void setShopname(String shopname) {
                this.shopname = shopname;
            }

            public String getRemind() {
                return remind;
            }

            public void setRemind(String remind) {
                this.remind = remind;
            }

            public List<GoodsBean> getGoods() {
                return goods;
            }

            public void setGoods(List<GoodsBean> goods) {
                this.goods = goods;
            }

            public static class GoodsBean {
                /**
                 * goods_name : 华为
                 * goods_num : 11
                 * market_price : 11.00
                 * cost_price : 0.00
                 * spec_name : 无规格
                 * goods_image : http://www.yzl.com/static/goods/20170825/acd0a8b73d6b279eb21ceeb72a7cf3b0.jpg
                 */

                private String goods_name;
                private String goods_num;
                private String market_price;
                private String cost_price;
                private String spec_name;
                private String goods_image;

                public String getGoods_name() {
                    return goods_name;
                }

                public void setGoods_name(String goods_name) {
                    this.goods_name = goods_name;
                }

                public String getGoods_num() {
                    return goods_num;
                }

                public void setGoods_num(String goods_num) {
                    this.goods_num = goods_num;
                }

                public String getMarket_price() {
                    return market_price;
                }

                public void setMarket_price(String market_price) {
                    this.market_price = market_price;
                }

                public String getCost_price() {
                    return cost_price;
                }

                public void setCost_price(String cost_price) {
                    this.cost_price = cost_price;
                }

                public String getSpec_name() {
                    return spec_name;
                }

                public void setSpec_name(String spec_name) {
                    this.spec_name = spec_name;
                }

                public String getGoods_image() {
                    return goods_image;
                }

                public void setGoods_image(String goods_image) {
                    this.goods_image = goods_image;
                }
            }
        }
    }
}
