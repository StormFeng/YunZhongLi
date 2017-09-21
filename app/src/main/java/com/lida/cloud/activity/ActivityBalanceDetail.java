package com.lida.cloud.activity;

import android.os.Bundle;
import com.lida.cloud.R;
import com.lida.cloud.data.ActivityBalanceDetailDataSource;
import com.lida.cloud.tpl.ActivityBalanceDetailTpl;
import com.midian.base.base.BaseListActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 余额明细
 * Created by Administrator on 2017/8/10.
 */

public class ActivityBalanceDetail extends BaseListActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        topbar.setTitle("明细");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new ActivityBalanceDetailDataSource(_activity);
    }

    @Override
    protected Class getTemplateClass() {
        return ActivityBalanceDetailTpl.class;
    }
}








