package com.lida.cloud.data;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

import com.lida.cloud.activity.ActivitySettlement;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.BalanceYesterdayBean;
import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;

/**
 * 昨日结算
 * Created by WeiQingFeng on 2017/8/25.
 */

public class ActivitySettlementDataSource extends BaseListDataSource {
    ActivitySettlement _activity;
    public ActivitySettlementDataSource(Context context) {
        super(context);
        _activity = (ActivitySettlement) context;
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList models = new ArrayList();
        final BalanceYesterdayBean bean = AppUtil.getApiClient(ac).balanceYesterday();
        _activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                _activity.tvAllMoney.setText(bean.getData().get(0).getTotal_balance()+"");
            }
        });
        models.addAll(bean.getData().get(0).getList());
        hasMore = false;
        return models;
    }
}
