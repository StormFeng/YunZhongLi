package com.lida.cloud.activity;

import android.os.Bundle;

import com.lida.cloud.R;
import com.lida.cloud.data.ActivityNewsListDataSource;
import com.lida.cloud.tpl.ActivityNewsListTpl;
import com.midian.base.base.BaseListActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 新闻资讯
 * Created by WeiQingFeng on 2017/9/4.
 */

public class ActivityNewsList extends BaseListActivity {

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        topbar.setTitle("新闻资讯");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new ActivityNewsListDataSource(_activity);
    }

    @Override
    protected Class getTemplateClass() {
        return ActivityNewsListTpl.class;
    }
}
