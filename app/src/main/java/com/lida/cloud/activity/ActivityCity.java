package com.lida.cloud.activity;

import android.content.Intent;
import android.os.Bundle;

import com.lida.cloud.R;
import com.lida.cloud.data.ActivityCityDataSource;
import com.lida.cloud.data.ActivityProvinceDataSource;
import com.lida.cloud.tpl.ActivityCityTpl;
import com.lida.cloud.tpl.ActivityProvinceTpl;
import com.midian.base.base.BaseListActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 市
 * Created by WeiQingFeng on 2017/9/4.
 */

public class ActivityCity extends BaseListActivity {

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;

    public String flag = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        if(getIntent()!=null){
            flag = getIntent().getStringExtra("flag");
        }
        topbar.setTitle("市");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        String id = mBundle.getInt("id")+"";
        return new ActivityCityDataSource(_activity,id);
    }

    @Override
    protected Class getTemplateClass() {
        return ActivityCityTpl.class;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(RESULT_OK == resultCode){
            setResult(RESULT_OK,data);
            finish();
        }
    }
}
