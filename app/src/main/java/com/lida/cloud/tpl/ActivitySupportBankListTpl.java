package com.lida.cloud.tpl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.lida.cloud.R;
import com.lida.cloud.bean.SupportBankCardListBean;
import com.midian.base.view.BaseTpl;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 选择发卡银行
 * Created by WeiQingFeng on 2017/8/31.
 */

public class ActivitySupportBankListTpl extends BaseTpl<SupportBankCardListBean.DataBean> {

    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.tv)
    TextView tv;

    public ActivitySupportBankListTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivitySupportBankListTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_activitysupportbanklist;
    }

    @Override
    public void setBean(final SupportBankCardListBean.DataBean bean, int position) {
        if (bean != null) {
            ac.setImage(iv, bean.getBank_image());
            tv.setText(bean.getBank_name());
            root.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("data",bean);
                    _activity.setResult(Activity.RESULT_OK,intent);
                    _activity.finish();
                }
            });
        }
    }
}
