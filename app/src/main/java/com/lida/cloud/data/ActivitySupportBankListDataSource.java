package com.lida.cloud.data;

import android.content.Context;

import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.SupportBankCardListBean;
import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;

/**
 * 选择发卡银行
 * Created by WeiQingFeng on 2017/8/31.
 */

public class ActivitySupportBankListDataSource extends BaseListDataSource {
    public ActivitySupportBankListDataSource(Context context) {
        super(context);
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<NetResult> models = new ArrayList<>();
        SupportBankCardListBean bean = AppUtil.getApiClient(ac).getSupportBankCardList();
        models.addAll(bean.getData());
        hasMore = false;
        return models;
    }
}
