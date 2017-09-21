package com.lida.cloud.data;

import android.content.Context;

import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.ProvinceBean;
import com.midian.base.base.BaseListDataSource;

import java.util.ArrayList;

/**
 * 省
 * Created by WeiQingFeng on 2017/9/4.
 */

public class ActivityProvinceDataSource extends BaseListDataSource {

    public ActivityProvinceDataSource(Context context) {
        super(context);
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList models = new ArrayList();
        ProvinceBean bean = AppUtil.getApiClient(ac).province();
        models.addAll(bean.getData());
        hasMore = false;
        return models;
    }
}
