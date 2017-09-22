package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 确定订单（购物车进来）
 * Created by WeiQingFeng on 2017/8/23.
 */

public class ActivityReadyToCommitOrderBean extends NetResult {

    private List<DataBean> data;

    public static ActivityReadyToCommitOrderBean parse(String json) throws AppException {
        ActivityReadyToCommitOrderBean res = new ActivityReadyToCommitOrderBean();
        try {
            res = gson.fromJson(json, ActivityReadyToCommitOrderBean.class);
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
         * address : []
         * list : [{"shopid":40,"selshopname":"上海女人","goods":[{"specid":397,"total":1,"cost":"55.00","sp_name":"test1","sp_image":"http://www.yzl.com/static/goods/20170823/06ecd425ed0d29d979f081366ab6fae5.png","spec_name":"红色"}]},{"shopid":41,"selshopname":"丫丫呀","goods":[{"specid":397,"total":1,"cost":"55.00","sp_name":"test1","sp_image":"http://www.yzl.com/static/goods/20170823/06ecd425ed0d29d979f081366ab6fae5.png","spec_name":"红色"}]}]
         * total : 2
         * price : 110
         */

        private String total;
        private String price;
        private List<ListBean> list;

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * shopid : 40
             * selshopname : 上海女人
             * goods : [{"specid":397,"total":1,"cost":"55.00","sp_name":"test1","sp_image":"http://www.yzl.com/static/goods/20170823/06ecd425ed0d29d979f081366ab6fae5.png","spec_name":"红色"}]
             */

            private String shopid;
            private String selshopname;
            private List<GoodsBean> goods;
            private String remark;

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getShopid() {
                return shopid;
            }

            public void setShopid(String shopid) {
                this.shopid = shopid;
            }

            public String getSelshopname() {
                return selshopname;
            }

            public void setSelshopname(String selshopname) {
                this.selshopname = selshopname;
            }

            public List<GoodsBean> getGoods() {
                return goods;
            }

            public void setGoods(List<GoodsBean> goods) {
                this.goods = goods;
            }

            public static class GoodsBean {
                /**
                 * specid : 397
                 * total : 1
                 * cost : 55.00
                 * sp_name : test1
                 * sp_image : http://www.yzl.com/static/goods/20170823/06ecd425ed0d29d979f081366ab6fae5.png
                 * spec_name : 红色
                 */

                private String specid;
                private String total;
                private String cost;
                private String sp_name;
                private String sp_image;
                private String spec_name;

                public String getSpecid() {
                    return specid;
                }

                public void setSpecid(String specid) {
                    this.specid = specid;
                }

                public String getTotal() {
                    return total;
                }

                public void setTotal(String total) {
                    this.total = total;
                }

                public String getCost() {
                    return cost;
                }

                public void setCost(String cost) {
                    this.cost = cost;
                }

                public String getSp_name() {
                    return sp_name;
                }

                public void setSp_name(String sp_name) {
                    this.sp_name = sp_name;
                }

                public String getSp_image() {
                    return sp_image;
                }

                public void setSp_image(String sp_image) {
                    this.sp_image = sp_image;
                }

                public String getSpec_name() {
                    return spec_name;
                }

                public void setSpec_name(String spec_name) {
                    this.spec_name = spec_name;
                }
            }
        }
    }
}
