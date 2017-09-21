package com.lida.cloud.tpl;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lida.cloud.R;
import com.lida.cloud.activity.ActivityPublicWelfareDetail;
import com.lida.cloud.bean.ActivityPublicWelfareBean;
import com.midian.base.util.UIHelper;
import com.midian.base.view.BaseTpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 粉丝公益
 * Created by xkr on 2017/9/8.
 */

public class ActivityPublicWelfareTpl extends BaseTpl<ActivityPublicWelfareBean.DataBean.ListBean> {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.count)
    TextView count;
    @BindView(R.id.image)
    ImageView image;

    public ActivityPublicWelfareTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivityPublicWelfareTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_public_welfare;
    }

    @Override
    public void setBean(final ActivityPublicWelfareBean.DataBean.ListBean bean, int position) {
        if (bean != null) {
            title.setText(bean.getTitle() == null ? "" : bean.getTitle());
            content.setText(bean.getContent() == null ? "" : bean.getContent());
            count.setText(bean.getCount() == null ? "" : bean.getCount());
            ac.setImage(image,bean.getPic());
            root.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("url", bean.getUrl());
                    UIHelper.jump(_activity, ActivityPublicWelfareDetail.class, bundle);
                }
            });
        }
    }
}
