package com.lida.cloud.tpl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.lida.cloud.R;
import com.lida.cloud.bean.CountryBean;
import com.lida.cloud.bean.ProvinceBean;
import com.midian.base.view.BaseTpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 省
 * Created by WeiQingFeng on 2017/9/4.
 */

public class ActivityCountryTpl extends BaseTpl<CountryBean.DataBean> {

    @BindView(R.id.tv)
    TextView tv;

    public ActivityCountryTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivityCountryTpl(Context context) {
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
    public void setBean(final CountryBean.DataBean bean, int position) {
        if(bean!=null){
            tv.setText(bean.getD3_name());
            tv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("country",bean.getD3_name());
                    intent.putExtra("city",_activity.getIntent().getExtras().getString("city"));
                    intent.putExtra("province",_activity.getIntent().getExtras().getString("province"));
                    _activity.setResult(Activity.RESULT_OK,intent);
                    _activity.finish();
                }
            });
        }
    }
}
