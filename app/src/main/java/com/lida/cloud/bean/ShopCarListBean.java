package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 购物车
 * Created by WeiQingFeng on 2017/8/23.
 */

public class ShopCarListBean extends NetResult {

    private int allCount;
    private double AllMoney;
    private boolean allSelect;
    private List<DataBean> data;

    public static ShopCarListBean parse(String json) throws AppException {
        ShopCarListBean res = new ShopCarListBean();
        try {
            res = gson.fromJson(json, ShopCarListBean.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            throw AppException.json(e);
        }
        return res;
    }

    public int getAllCount() {
        return allCount;
    }

    public void setAllCount(int allCount) {
        this.allCount = allCount;
    }

    public double getAllMoney() {
        return AllMoney;
    }

    public void setAllMoney(double allMoney) {
        AllMoney = allMoney;
    }

    public boolean isAllSelect() {
        return allSelect;
    }

    public void setAllSelect(boolean allSelect) {
        this.allSelect = allSelect;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }


    public static class DataBean {
        /**
         * goodsid : 392
         * selshopname : 游乐城
         * selid : 6
         * specid : 0
         * list : [{"sp_id":392,"sp_selid":6,"store_name":"游乐城","sp_name":"华为","sp_image":"http://www.yzl.com/static/goods/20170825/acd0a8b73d6b279eb21ceeb72a7cf3b0.jpg","sp_images":"[\"20170825\\\\9366f08952441d02ac742a9b5c27b46b.jpg\"]","typeid":14,"market_price":"11.00","cost":"11.00","status":"1","stock":106,"create_time":1503626765,"attribute":"a:8:{i:1;a:2:{i:0;s:0:\"\";i:1;s:0:\"\";}i:2;a:2:{i:0;s:0:\"\";i:1;s:0:\"\";}i:3;a:2:{i:0;s:0:\"\";i:1;s:0:\"\";}i:4;a:2:{i:0;s:0:\"\";i:1;s:0:\"\";}i:5;a:2:{i:0;s:0:\"\";i:1;s:0:\"\";}i:6;a:2:{i:0;s:0:\"\";i:1;s:0:\"\";}i:7;a:2:{i:0;s:0:\"\";i:1;s:0:\"\";}i:8;a:2:{i:0;s:0:\"\";i:1;s:0:\"\";}}","content":"","is_spec":"0","is_free_shipping":"1","home_sort":0,"credit":1,"sales_volume":0}]
         */

        private String selshopname;
        private String shopid;
        private boolean selected;
        private List<ListBean> list;

        public boolean isSelected() {
            return selected;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }

        public String getSelshopname() {
            return selshopname;
        }

        public void setSelshopname(String selshopname) {
            this.selshopname = selshopname;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public String getShopid() {
            return shopid;
        }

        public void setShopid(String shopid) {
            this.shopid = shopid;
        }

        public static class ListBean {
            /**
             * sp_id : 392
             * sp_selid : 6
             * store_name : 游乐城
             * sp_name : 华为
             * sp_image : http://www.yzl.com/static/goods/20170825/acd0a8b73d6b279eb21ceeb72a7cf3b0.jpg
             * sp_images : ["20170825\\9366f08952441d02ac742a9b5c27b46b.jpg"]
             * typeid : 14
             * market_price : 11.00
             * cost : 11.00
             * status : 1
             * stock : 106
             * create_time : 1503626765
             * attribute : a:8:{i:1;a:2:{i:0;s:0:"";i:1;s:0:"";}i:2;a:2:{i:0;s:0:"";i:1;s:0:"";}i:3;a:2:{i:0;s:0:"";i:1;s:0:"";}i:4;a:2:{i:0;s:0:"";i:1;s:0:"";}i:5;a:2:{i:0;s:0:"";i:1;s:0:"";}i:6;a:2:{i:0;s:0:"";i:1;s:0:"";}i:7;a:2:{i:0;s:0:"";i:1;s:0:"";}i:8;a:2:{i:0;s:0:"";i:1;s:0:"";}}
             * content :
             * is_spec : 0
             * is_free_shipping : 1
             * home_sort : 0
             * credit : 1
             * sales_volume : 0
             */

            private String id;
            private String shopid;
            private String uid;
            private String goodsid;
            private String sp_name;
            private String specid;
            private String total;
            private String createtime;
            private String sp_image;
            private String cost;
            private String state;
            private String status;
            private String spec_name;
            private String stock;
            private boolean edit;
            private boolean selected;
            private int isselect;

            public int getIsselect() {
                return isselect;
            }

            public void setIsselect(int isselect) {
                this.isselect = isselect;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getStock() {
                return stock;
            }

            public void setStock(String stock) {
                this.stock = stock;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public boolean isEdit() {
                return edit;
            }

            public void setEdit(boolean edit) {
                this.edit = edit;
            }

            public boolean isSelected() {
                return selected;
            }

            public void setSelected(boolean selected) {
                this.selected = selected;
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

            public String getCost() {
                return cost;
            }

            public void setCost(String cost) {
                this.cost = cost;
            }

            public String getShopid() {
                return shopid;
            }

            public void setShopid(String shopid) {
                this.shopid = shopid;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getGoodsid() {
                return goodsid;
            }

            public void setGoodsid(String goodsid) {
                this.goodsid = goodsid;
            }

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

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
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
