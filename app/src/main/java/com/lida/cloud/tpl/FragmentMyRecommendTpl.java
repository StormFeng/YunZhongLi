package com.lida.cloud.tpl;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.lida.cloud.R;
import com.lida.cloud.bean.MyRecommendBean;
import com.midian.base.view.BaseTpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xkr on 2017/9/7.
 */

public class FragmentMyRecommendTpl extends BaseTpl<MyRecommendBean.DataBean.ListBean> {


    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvDate)
    TextView tvDate;
    @BindView(R.id.tvMoney)
    TextView tvMoney;

    public FragmentMyRecommendTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FragmentMyRecommendTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_activitymyrecommend;
    }

    @Override
    public void setBean(MyRecommendBean.DataBean.ListBean bean, int position) {
        if(bean!=null){
            tvName.setText(bean.getMem_name()==null?"":bean.getMem_name());
            tvDate.setText(bean.getMem_registertime()==null?"":bean.getMem_registertime());
            tvMoney.setText(bean.getCredit()==null?"":bean.getCredit());
        }
    }
}
