package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 商铺商品
 * Created by WeiQingFeng on 2017/8/23.
 */

public class ShopGoodBean extends NetResult {

    /**
     * data : {"goods":[{"sp_id":"商品ID","sp_name":"商品名称","sp_image":"商品主图","cost":"消费豆"}]}
     */

    private List<DataBean> data;

    public static ShopGoodBean parse(String json) throws AppException {
        ShopGoodBean res = new ShopGoodBean();
        try {
            res = gson.fromJson(json, ShopGoodBean.class);
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
        private List<GoodsBean> goods;

        public List<GoodsBean> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsBean> goods) {
            this.goods = goods;
        }

        public static class GoodsBean {
            /**
             * sp_id : 商品ID
             * sp_name : 商品名称
             * sp_image : 商品主图
             * cost : 消费豆
             */

            private String sp_id;
            private String sp_name;
            private String goods_image;
            private String cost;

            public String getSp_id() {
                return sp_id;
            }

            public void setSp_id(String sp_id) {
                this.sp_id = sp_id;
            }

            public String getSp_name() {
                return sp_name;
            }

            public void setSp_name(String sp_name) {
                this.sp_name = sp_name;
            }

            public String getGoods_image() {
                return goods_image;
            }

            public void setGoods_image(String goods_image) {
                this.goods_image = goods_image;
            }

            public String getCost() {
                return cost;
            }

            public void setCost(String cost) {
                this.cost = cost;
            }
        }
    }
}
