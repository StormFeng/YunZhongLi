package com.lida.cloud.data;

import android.content.Context;

import com.lida.cloud.activity.ActivityCreditDetail;
import com.lida.cloud.activity.ActivitySettlement;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.BalanceYesterdayBean;
import com.lida.cloud.bean.MemberCreditLogBean;
import com.midian.base.base.BaseListDataSource;

import java.util.ArrayList;

/**
 * 积分明细
 * Created by WeiQingFeng on 2017/8/25.
 */

public class ActivityCreditDetailDataSource extends BaseListDataSource {
    ActivityCreditDetail _activity;
    public ActivityCreditDetailDataSource(Context context) {
        super(context);
        _activity = (ActivityCreditDetail) context;
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList models = new ArrayList();
        final MemberCreditLogBean bean = AppUtil.getApiClient(ac).memberCredit_log();
        _activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                _activity.tvAllMoney.setText(bean.getData().get(0).getCredit()+"");
            }
        });
        models.addAll(bean.getData().get(0).getList());
        hasMore = false;
        return models;
    }
}
