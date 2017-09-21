package com.lida.cloud.tpl;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.lida.cloud.R;
import com.lida.cloud.bean.BusinessHoldBean;
import com.midian.base.view.BaseTpl;
import com.vondear.rxtools.RxTextUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 库存积分
 * Created by WeiQingFeng on 2017/8/25.
 */

public class ActivityIntegralTpl extends BaseTpl<BusinessHoldBean.DataBean.RecordBean> {

    @BindView(R.id.tvType)
    TextView tvType;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tvMoney)
    TextView tvMoney;

    public ActivityIntegralTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivityIntegralTpl(Context context) {
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
    public void setBean(BusinessHoldBean.DataBean.RecordBean bean, int position) {
        if(bean!=null){
            tvTime.setText(bean.getOut_time());
            if("0".equals(bean.getStatus())){
                tvType.setText("转出积分-转给"+bean.getOut_name());
                tvMoney.setText("-"+bean.getOut_credit());
            }else{
                tvType.setText(bean.getOut_name());
                tvMoney.setText("+"+bean.getOut_credit());
            }
        }
    }
}
