package com.lida.cloud.data;

import android.content.Context;

import com.lida.cloud.activity.ActivityIntegralStock;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.BusinessHoldBean;
import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;

/**
 * 库存积分
 * Created by WeiQingFeng on 2017/8/25.
 */

public class ActivityIntegralDataSource extends BaseListDataSource {
    private ActivityIntegralStock _activity;
    public ActivityIntegralDataSource(Context context) {
        super(context);
        _activity = (ActivityIntegralStock) context;
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList models = new ArrayList();
        final BusinessHoldBean bean = AppUtil.getApiClient(ac).businessCredit(context);
        _activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                _activity.tvAllMoney.setText(bean.getData().get(0).getCredit());
            }
        });
        models.addAll(bean.getData().get(0).getRecord());
        hasMore = false;
        return models;
    }
}
