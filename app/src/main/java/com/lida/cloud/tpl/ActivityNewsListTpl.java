package com.lida.cloud.tpl;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lida.cloud.R;
import com.lida.cloud.activity.ActivityAboutUs;
import com.lida.cloud.bean.NewsListBean;
import com.midian.base.util.UIHelper;
import com.midian.base.view.BaseTpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 新闻资讯
 * Created by WeiQingFeng on 2017/9/4.
 */

public class ActivityNewsListTpl extends BaseTpl<NewsListBean.DataBean.ListBean> {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvContent)
    TextView tvContent;
    @BindView(R.id.tvViewCount)
    TextView tvViewCount;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.iv)
    ImageView iv;

    public ActivityNewsListTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivityNewsListTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_activitynews;
    }

    @Override
    public void setBean(final NewsListBean.DataBean.ListBean bean, int position) {
        if (bean != null) {
            tvTitle.setText(bean.getTitle());
            tvContent.setText(bean.getDescription());
            tvViewCount.setText("浏览：" + bean.getViewcount());
            tvTime.setText(bean.getTime());
            ac.setImage(iv,bean.getPic());
            root.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("url", bean.getUrl());
                    bundle.putString("title", "新闻资讯详情");
                    UIHelper.jump(_activity, ActivityAboutUs.class, bundle);
                }
            });

        }
    }
}
