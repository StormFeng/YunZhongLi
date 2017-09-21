package com.lida.cloud.data;

import android.content.Context;

import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.CityBean;
import com.lida.cloud.bean.CountryBean;
import com.midian.base.base.BaseListDataSource;

import java.util.ArrayList;

/**
 * 区
 * Created by WeiQingFeng on 2017/9/4.
 */

public class ActivityCountryDataSource extends BaseListDataSource {

    private String id;

    public ActivityCountryDataSource(Context context, String id) {
        super(context);
        this.id = id;
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList models = new ArrayList();
        CountryBean bean = AppUtil.getApiClient(ac).county(id);
        models.addAll(bean.getData());
        hasMore = false;
        return models;
    }
}
