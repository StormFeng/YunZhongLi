package com.lida.cloud.tpl;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import com.lida.cloud.R;
import com.lida.cloud.activity.ActivityCity;
import com.lida.cloud.activity.ActivityProvince;
import com.lida.cloud.bean.ProvinceBean;
import com.midian.base.util.UIHelper;
import com.midian.base.view.BaseTpl;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 省
 * Created by WeiQingFeng on 2017/9/4.
 */

public class ActivityProvinceTpl extends BaseTpl<ProvinceBean.DataBean> {

    @BindView(R.id.tv)
    TextView tv;

    public ActivityProvinceTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivityProvinceTpl(Context context) {
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
    public void setBean(final ProvinceBean.DataBean bean, int position) {
        if(bean!=null){
            tv.setText(bean.getD1_name());
            root.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("id",bean.getD1_id());
                    bundle.putString("province",bean.getD1_name());
                    bundle.putString("flag",((ActivityProvince)_activity).flag);
                    UIHelper.jumpForResult(_activity, ActivityCity.class, bundle,1001);
                }
            });
        }
    }
}
