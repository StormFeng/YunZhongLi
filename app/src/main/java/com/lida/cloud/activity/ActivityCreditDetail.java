package com.lida.cloud.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.lida.cloud.R;
import com.lida.cloud.data.ActivityCreditDetailDataSource;
import com.lida.cloud.data.ActivitySettlementDataSource;
import com.lida.cloud.tpl.ActivityCreditTpl;
import com.lida.cloud.tpl.ActivitySettlementTpl;
import com.midian.base.base.BaseListActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 信用积分明细
 * Created by WeiQingFeng on 2017/8/25.
 */

public class ActivityCreditDetail extends BaseListActivity {

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.tvAllMoney)
    public TextView tvAllMoney;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        topbar.setTitle("积分明细");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_credit;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new ActivityCreditDetailDataSource(ActivityCreditDetail.this);
    }

    @Override
    protected Class getTemplateClass() {
        return ActivityCreditTpl.class;
    }
}
