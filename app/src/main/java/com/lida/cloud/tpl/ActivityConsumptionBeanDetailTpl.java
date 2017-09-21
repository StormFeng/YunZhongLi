package com.lida.cloud.tpl;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.lida.cloud.R;
import com.lida.cloud.bean.BeanRecordBean;
import com.midian.base.view.BaseTpl;
import com.vondear.rxtools.RxTimeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 消费豆明细
 * Created by Administrator on 2017/8/8.
 */

public class ActivityConsumptionBeanDetailTpl extends BaseTpl<BeanRecordBean.DataBean> {

    @BindView(R.id.tvType)
    TextView tvType;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tvCount)
    TextView tvCount;
    @BindView(R.id.tvPrice)
    TextView tvPrice;

    public ActivityConsumptionBeanDetailTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivityConsumptionBeanDetailTpl(Context context) {
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
    public void setBean(BeanRecordBean.DataBean bean, int position) {
        if(bean!=null){
            tvTime.setText(RxTimeUtils.milliseconds2String(Long.parseLong(bean.getCreate_time()+"000")));
            if("1".equals(bean.getType())){
                tvType.setText("充值");
                tvCount.setText("+"+bean.getNumber());
            }
            if("2".equals(bean.getType())){
                tvType.setText("提现申请");
                tvCount.setText("-"+bean.getNumber());
            }
            if("3".equals(bean.getType())){
                tvType.setText("提现拒绝");
                tvCount.setText("+"+bean.getNumber());
            }
            if("4".equals(bean.getType())){
                tvType.setText("提现成功");
                tvCount.setText("-"+bean.getNumber());
            }
            if("5".equals(bean.getType())){
                tvType.setText("消费");
                tvCount.setText("-"+bean.getNumber());
            }
            if("6".equals(bean.getType())){
                tvType.setText("返还");
                tvCount.setText("+"+bean.getNumber());
            }
            if("7".equals(bean.getType())){
                tvType.setText("用户消费商家获得");
                tvCount.setText("+"+bean.getNumber());
            }
        }
    }
}
