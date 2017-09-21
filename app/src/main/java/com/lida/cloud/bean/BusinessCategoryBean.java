package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 店铺分类
 * Created by WeiQingFeng on 2016/10/28 0028.
 */

public class BusinessCategoryBean extends NetResult{

    private List<DataBean> data;

    public static BusinessCategoryBean parse(String json) throws AppException {
        BusinessCategoryBean res = new BusinessCategoryBean();
        try {
            res = gson.fromJson(json, BusinessCategoryBean.class);
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
         * id : 2
         * name : 餐饮
         * logo : http://www.yzl.com\public\static\category\
         */

        private int id;
        private String name;
        private String logo;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }
    }
}
