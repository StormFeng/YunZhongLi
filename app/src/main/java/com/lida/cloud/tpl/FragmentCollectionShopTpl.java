package com.lida.cloud.tpl;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.lida.cloud.R;
import com.midian.base.bean.NetResult;
import com.midian.base.view.BaseTpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 收藏的店铺
 * Created by Administrator on 2017/8/8.
 */

public class FragmentCollectionShopTpl extends BaseTpl<NetResult> {

    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvPosition)
    TextView tvPosition;
    @BindView(R.id.tvDistance)
    TextView tvDistance;

    public FragmentCollectionShopTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FragmentCollectionShopTpl(Context context) {
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
    public void setBean(NetResult bean, int position) {
        if(bean!=null){

        }
    }
}
