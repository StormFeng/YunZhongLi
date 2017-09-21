package com.lida.cloud.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.lida.cloud.R;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.BalanceYesterdayBean;
import com.lida.cloud.data.ActivitySettlementDataSource;
import com.lida.cloud.tpl.ActivitySettlementTpl;
import com.lida.cloud.widght.BaseApiCallback;
import com.midian.base.base.BaseListActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;
import com.vondear.rxtools.view.RxToast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 昨日结算
 * Created by WeiQingFeng on 2017/8/25.
 */

public class ActivitySettlement extends BaseListActivity {

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.tvAllMoney)
    public TextView tvAllMoney;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        topbar.setTitle("累计结算");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_settlement;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new ActivitySettlementDataSource(ActivitySettlement.this);
    }

    @Override
    protected Class getTemplateClass() {
        return ActivitySettlementTpl.class;
    }
}
