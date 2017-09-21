package com.lida.cloud.activity;

import android.os.Bundle;

import com.lida.cloud.R;
import com.lida.cloud.data.ActivitySupportBankListDataSource;
import com.lida.cloud.tpl.ActivitySupportBankListTpl;
import com.midian.base.base.BaseActivity;
import com.midian.base.base.BaseListActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 选择发卡银行
 * Created by WeiQingFeng on 2017/8/31.
 */

public class ActivitySupportBankList extends BaseListActivity {

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        topbar.setTitle("选择发卡银行");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new ActivitySupportBankListDataSource(_activity);
    }

    @Override
    protected Class getTemplateClass() {
        return ActivitySupportBankListTpl.class;
    }
}
