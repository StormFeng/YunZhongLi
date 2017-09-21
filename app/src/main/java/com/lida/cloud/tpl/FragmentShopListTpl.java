package com.lida.cloud.tpl;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lida.cloud.R;
import com.lida.cloud.activity.ActivityGoodDetail;
import com.lida.cloud.activity.ActivityShopDetail;
import com.lida.cloud.bean.BusinessListBean;
import com.midian.base.util.UIHelper;
import com.midian.base.view.BaseTpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 商家列表
 * Created by Administrator on 2017/8/8.
 */

public class FragmentShopListTpl extends BaseTpl<BusinessListBean.DataBean.StoreBean> {

    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvPosition)
    TextView tvPosition;
    @BindView(R.id.tvDistance)
    TextView tvDistance;

    public FragmentShopListTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FragmentShopListTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_fragmentshoplist;
    }

    @Override
    public void setBean(final BusinessListBean.DataBean.StoreBean bean, int position) {
        if(bean!=null){
            if(bean.getLogo()!=null){
                ac.setImage(iv,bean.getLogo());
            }
            tvName.setText(bean.getSelshopname());
            tvPosition.setText(bean.getProvincialcity());
            tvDistance.setText(bean.getDistant());
            root.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("selid",bean.getSelid()+"");
                    UIHelper.jump(_activity, ActivityShopDetail.class,bundle);
                }
            });
        }
    }
}
