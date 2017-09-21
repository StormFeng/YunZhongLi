package com.lida.cloud.tpl;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.lida.cloud.R;
import com.lida.cloud.bean.BalanceListBean;
import com.midian.base.bean.NetResult;
import com.midian.base.view.BaseTpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 余额明细
 * Created by Administrator on 2017/8/8.
 */

public class ActivityBalanceDetailTpl extends BaseTpl<BalanceListBean.DataBean.ListBean> {

    @BindView(R.id.tvType)
    TextView tvType;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tvPrice)
    TextView tvPrice;
    @BindView(R.id.tvCount)
    TextView tvCount;

    public ActivityBalanceDetailTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivityBalanceDetailTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_activityconsumptiondetail;
    }

    @Override
    public void setBean(BalanceListBean.DataBean.ListBean bean, int position) {
        if(bean!=null){
            if("cz".equals(bean.getType())){
                tvType.setText("充值");
                tvCount.setText("+"+bean.getAmount());
            }else if("zz".equals(bean.getType())){
                tvType.setText("转账");
                tvCount.setText("-"+bean.getAmount());
            }else if("pay".equals(bean.getType())){
                tvType.setText("提现");
                tvCount.setText("-"+bean.getAmount());
            }else if("ba".equals(bean.getType())){
                tvType.setText("积分返现");
                tvCount.setText("+"+bean.getAmount());
            }else if("bzz".equals(bean.getType())){
                tvType.setText("收到转账");
                tvCount.setText("+"+bean.getAmount());
            }
            tvTime.setText(bean.getCreate_time());
        }
    }
}
