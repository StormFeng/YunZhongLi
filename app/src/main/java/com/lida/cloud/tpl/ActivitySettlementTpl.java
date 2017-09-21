package com.lida.cloud.tpl;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.lida.cloud.R;
import com.lida.cloud.bean.BalanceYesterdayBean;
import com.midian.base.bean.NetResult;
import com.midian.base.view.BaseTpl;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 昨日结算
 * Created by WeiQingFeng on 2017/8/25.
 */

public class ActivitySettlementTpl extends BaseTpl<BalanceYesterdayBean.DataBean.ListBean> {

    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tvMoney)
    TextView tvMoney;

    public ActivitySettlementTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivitySettlementTpl(Context context) {
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
    public void setBean(BalanceYesterdayBean.DataBean.ListBean bean, int position) {
        if(bean!=null){
            tvTime.setText(bean.getCreate_time());
            tvMoney.setText(bean.getAmount());
        }
    }
}
