package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 收藏的商品
 * Created by WeiQingFeng on 2017/8/23.
 */

public class CollectGoodsBean extends NetResult {

    /**
     * data : {"count":2,"list":[{"col_id":3,"col_memid":83,"col_goodsid":391,"sp_id":391,"sp_selid":0,"store_name":"","sp_name":"小米6","sp_image":"http://www.yzl.com/static/goods/20170825/0035473193839c5729b5ef80f25dabbe.jpg","sp_images":"[\"20170825\\\\28813d8cb12bb3baf88f1109fedaca4f.jpg\",\"20170825\\\\5061605be63f2ed11feee882b54f821c.jpg\"]","typeid":14,"market_price":"300.00","cost":"200.00","status":"1","stock":990,"create_time":1503626561,"attribute":"a:8:{i:1;a:2:{i:0;s:0:\"\";i:1;s:0:\"\";}i:2;a:2:{i:0;s:0:\"\";i:1;s:0:\"\";}i:3;a:2:{i:0;s:0:\"\";i:1;s:0:\"\";}i:4;a:2:{i:0;s:0:\"\";i:1;s:0:\"\";}i:5;a:2:{i:0;s:0:\"\";i:1;s:0:\"\";}i:6;a:2:{i:0;s:0:\"\";i:1;s:0:\"\";}i:7;a:2:{i:0;s:0:\"\";i:1;s:0:\"\";}i:8;a:2:{i:0;s:0:\"\";i:1;s:0:\"\";}}","content":"&lt;p&gt;324324234 水电费&lt;/p&gt;","is_spec":"0","is_free_shipping":"1","home_sort":0,"credit":1,"sales_volume":0}],"page_num":1,"page_limit":8}
     */

    private List<DataBean> data;

    public static CollectGoodsBean parse(String json) throws AppException {
        CollectGoodsBean res = new CollectGoodsBean();
        try {
            res = gson.fromJson(json, CollectGoodsBean.class);
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
         * count : 2
         * list : [{"col_id":3,"col_memid":83,"col_goodsid":391,"sp_id":391,"sp_selid":0,"store_name":"","sp_name":"小米6","sp_image":"http://www.yzl.com/static/goods/20170825/0035473193839c5729b5ef80f25dabbe.jpg","sp_images":"[\"20170825\\\\28813d8cb12bb3baf88f1109fedaca4f.jpg\",\"20170825\\\\5061605be63f2ed11feee882b54f821c.jpg\"]","typeid":14,"market_price":"300.00","cost":"200.00","status":"1","stock":990,"create_time":1503626561,"attribute":"a:8:{i:1;a:2:{i:0;s:0:\"\";i:1;s:0:\"\";}i:2;a:2:{i:0;s:0:\"\";i:1;s:0:\"\";}i:3;a:2:{i:0;s:0:\"\";i:1;s:0:\"\";}i:4;a:2:{i:0;s:0:\"\";i:1;s:0:\"\";}i:5;a:2:{i:0;s:0:\"\";i:1;s:0:\"\";}i:6;a:2:{i:0;s:0:\"\";i:1;s:0:\"\";}i:7;a:2:{i:0;s:0:\"\";i:1;s:0:\"\";}i:8;a:2:{i:0;s:0:\"\";i:1;s:0:\"\";}}","content":"&lt;p&gt;324324234 水电费&lt;/p&gt;","is_spec":"0","is_free_shipping":"1","home_sort":0,"credit":1,"sales_volume":0}]
         * page_num : 1
         * page_limit : 8
         */

        private String count;
        private String page_num;
        private String page_limit;
        private List<ListBean> list;

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

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

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * col_id : 3
             * col_memid : 83
             * col_goodsid : 391
             * sp_id : 391
             * sp_selid : 0
             * store_name :
             * sp_name : 小米6
             * sp_image : http://www.yzl.com/static/goods/20170825/0035473193839c5729b5ef80f25dabbe.jpg
             * sp_images : ["20170825\\28813d8cb12bb3baf88f1109fedaca4f.jpg","20170825\\5061605be63f2ed11feee882b54f821c.jpg"]
             * typeid : 14
             * market_price : 300.00
             * cost : 200.00
             * status : 1
             * stock : 990
             * create_time : 1503626561
             * attribute : a:8:{i:1;a:2:{i:0;s:0:"";i:1;s:0:"";}i:2;a:2:{i:0;s:0:"";i:1;s:0:"";}i:3;a:2:{i:0;s:0:"";i:1;s:0:"";}i:4;a:2:{i:0;s:0:"";i:1;s:0:"";}i:5;a:2:{i:0;s:0:"";i:1;s:0:"";}i:6;a:2:{i:0;s:0:"";i:1;s:0:"";}i:7;a:2:{i:0;s:0:"";i:1;s:0:"";}i:8;a:2:{i:0;s:0:"";i:1;s:0:"";}}
             * content : &lt;p&gt;324324234 水电费&lt;/p&gt;
             * is_spec : 0
             * is_free_shipping : 1
             * home_sort : 0
             * credit : 1
             * sales_volume : 0
             */

            private String col_id;
            private String sp_name;
            private String sp_image;
            private String sp_id;
            private String market_price;
            private String cost;
            private boolean edit;
            private boolean select;

            public String getSp_id() {
                return sp_id;
            }

            public void setSp_id(String sp_id) {
                this.sp_id = sp_id;
            }

            public boolean isEdit() {
                return edit;
            }

            public void setEdit(boolean edit) {
                this.edit = edit;
            }

            public boolean isSelect() {
                return select;
            }

            public void setSelect(boolean select) {
                this.select = select;
            }

            public String getCol_id() {
                return col_id;
            }

            public void setCol_id(String col_id) {
                this.col_id = col_id;
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

            public String getMarket_price() {
                return market_price;
            }

            public void setMarket_price(String market_price) {
                this.market_price = market_price;
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
