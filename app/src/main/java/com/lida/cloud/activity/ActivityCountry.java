package com.lida.cloud.activity;

import android.os.Bundle;

import com.lida.cloud.R;
import com.lida.cloud.data.ActivityCityDataSource;
import com.lida.cloud.data.ActivityCountryDataSource;
import com.lida.cloud.tpl.ActivityCityTpl;
import com.lida.cloud.tpl.ActivityCountryTpl;
import com.midian.base.base.BaseListActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 区
 * Created by WeiQingFeng on 2017/9/4.
 */

public class ActivityCountry extends BaseListActivity {

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        topbar.setTitle("区");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        String id = mBundle.getInt("id")+"";
        return new ActivityCountryDataSource(_activity,id);
    }

    @Override
    protected Class getTemplateClass() {
        return ActivityCountryTpl.class;
    }
}
