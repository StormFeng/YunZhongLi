package com.lida.cloud.data;

import android.content.Context;

import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.BalanceListBean;
import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;

/**
 * 余额明细
 * Created by Administrator on 2017/8/8.
 */

public class ActivityBalanceDetailDataSource extends BaseListDataSource {

    public ActivityBalanceDetailDataSource(Context context) {
        super(context);
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<NetResult> models = new ArrayList<>();
        BalanceListBean bean = AppUtil.getApiClient(ac).balance(ac.memid);
        models.addAll(bean.getData().get(0).getList());
        hasMore = false;
        return models;
    }
}
