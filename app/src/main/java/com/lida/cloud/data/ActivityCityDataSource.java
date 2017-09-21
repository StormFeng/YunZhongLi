package com.lida.cloud.data;

import android.content.Context;

import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.CityBean;
import com.lida.cloud.bean.ProvinceBean;
import com.midian.base.base.BaseListDataSource;

import java.util.ArrayList;

/**
 * 省
 * Created by WeiQingFeng on 2017/9/4.
 */

public class ActivityCityDataSource extends BaseListDataSource {

    private String id;

    public ActivityCityDataSource(Context context, String id) {
        super(context);
        this.id = id;
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList models = new ArrayList();
        CityBean bean = AppUtil.getApiClient(ac).city(id);
        models.addAll(bean.getData());
        hasMore = false;
        return models;
    }
}
