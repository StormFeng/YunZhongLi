package com.lida.cloud.bean;

import java.util.List;

/**
 * Created by WeiQingFeng on 2016/10/28 0028.
 */

public class GoodBean {

    private List<ContentBean> content;
    private int allCount;
    private double AllMoney;
    private boolean allSelect;

    public boolean isAllSelect() {
        return allSelect;
    }

    public void setAllSelect(boolean allSelect) {
        this.allSelect = allSelect;
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

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public static class ContentBean {
        /**
         * id : 0
         * adress : 广州市天河城
         * selected : false
         * gooddetail : [{"id":"00","pic":"11","count":"1","limitcount":"10","name":"BB霜","price":"99","edit":"false","selected":"false","color":"红色"},{"id":"01","pic":"11","count":"1","limitcount":"5","name":"男士洁面乳","price":"66","edit":"false","selected":"false","color":"红色"},{"id":"02","pic":"11","count":"1","limitcount":"2","name":"眼线笔","price":"16","edit":"false","selected":"false","color":"红色"}]
         */

        private String id;
        private String adress;
        private boolean selected;
        private List<GooddetailBean> gooddetail;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAdress() {
            return adress;
        }

        public void setAdress(String adress) {
            this.adress = adress;
        }

        public boolean isSelected() {
            return selected;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }

        public List<GooddetailBean> getGooddetail() {
            return gooddetail;
        }

        public void setGooddetail(List<GooddetailBean> gooddetail) {
            this.gooddetail = gooddetail;
        }

        public static class GooddetailBean {
            /**
             * id : 00
             * pic : 11
             * count : 1
             * limitcount : 10
             * name : BB霜
             * price : 99
             * edit : false
             * selected : false
             * color : 红色
             */

            private String id;
            private String pic;
            private String count;
            private String limitcount;
            private String name;
            private String price;
            private boolean edit;
            private boolean selected;
            private String color;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }

            public String getLimitcount() {
                return limitcount;
            }

            public void setLimitcount(String limitcount) {
                this.limitcount = limitcount;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
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

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }
        }
    }
}
