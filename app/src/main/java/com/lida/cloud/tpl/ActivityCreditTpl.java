package com.lida.cloud.tpl;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.lida.cloud.R;
import com.lida.cloud.bean.MemberCreditLogBean;
import com.midian.base.view.BaseTpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 积分明细
 * Created by WeiQingFeng on 2017/8/25.
 */

public class ActivityCreditTpl extends BaseTpl<MemberCreditLogBean.DataBean.ListBean> {

    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tvMoney)
    TextView tvMoney;
    @BindView(R.id.tvType)
    TextView tvType;

    public ActivityCreditTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivityCreditTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_activitysettlement;
    }

    @Override
    public void setBean(MemberCreditLogBean.DataBean.ListBean bean, int position) {
        if (bean != null) {
            tvType.setText(bean.getLog());
            tvTime.setText(bean.getCreatetime());
            tvMoney.setText(bean.getCredit() + "");
        }
    }
}
