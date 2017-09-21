package com.lida.cloud.tpl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.lida.cloud.R;
import com.lida.cloud.activity.ActivityCity;
import com.lida.cloud.activity.ActivityCountry;
import com.lida.cloud.bean.CityBean;
import com.lida.cloud.bean.CountryBean;
import com.midian.base.util.UIHelper;
import com.midian.base.view.BaseTpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 市
 * Created by WeiQingFeng on 2017/9/4.
 */

public class ActivityCityTpl extends BaseTpl<CityBean.DataBean> {

    @BindView(R.id.tv)
    TextView tv;

    public ActivityCityTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivityCityTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_area;
    }

    @Override
    public void setBean(final CityBean.DataBean bean, int position) {
        if(bean!=null){
            tv.setText(bean.getD2_name());
            tv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    if("FragmentHome".equals(((ActivityCity)_activity).flag)){
                        Intent intent = new Intent();
                        intent.putExtra("city",bean.getD2_name());
                        intent.putExtra("province",_activity.getIntent().getExtras().getString("province"));
                        _activity.setResult(Activity.RESULT_OK,intent);
                        _activity.finish();
                        return;
                    }
                    bundle.putInt("id",bean.getD2_id());
                    bundle.putString("province",_activity.getIntent().getExtras().getString("province"));
                    bundle.putString("city",bean.getD2_name());
                    UIHelper.jumpForResult(_activity, ActivityCountry.class,bundle,1001);
                }
            });
        }
    }
}
