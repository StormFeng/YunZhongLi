package com.lida.cloud.data;

import android.content.Context;

import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.BeanRecordBean;
import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;

/**
 * 消费豆明细
 * Created by Administrator on 2017/8/8.
 */

public class ActivityConsumptionBeanDetailDataSource extends BaseListDataSource {

    public ActivityConsumptionBeanDetailDataSource(Context context) {
        super(context);
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<NetResult> models = new ArrayList<>();
        BeanRecordBean bean = AppUtil.getApiClient(ac).beanRecord(ac.memid, "");
        models.addAll(bean.getData());
        hasMore = false;
        return models;
    }
}
