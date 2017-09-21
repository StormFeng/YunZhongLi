package com.lida.cloud.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.lida.cloud.R;
import com.lida.cloud.data.ActivityIntegralDataSource;
import com.lida.cloud.data.ActivitySettlementDataSource;
import com.lida.cloud.tpl.ActivityIntegralTpl;
import com.lida.cloud.tpl.ActivitySettlementTpl;
import com.midian.base.base.BaseListActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 库存积分
 * Created by WeiQingFeng on 2017/8/25.
 */

public class ActivityIntegralStock extends BaseListActivity {

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.tvAllMoney)
    public TextView tvAllMoney;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        topbar.setTitle("库存积分");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_integral;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new ActivityIntegralDataSource(_activity);
    }

    @Override
    protected Class getTemplateClass() {
        return ActivityIntegralTpl.class;
    }
}
