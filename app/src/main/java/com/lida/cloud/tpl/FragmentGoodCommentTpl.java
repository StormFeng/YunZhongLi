package com.lida.cloud.tpl;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.lida.cloud.R;
import com.lida.cloud.adapter.AdapterFragmentGoodComment;
import com.lida.cloud.bean.GoodCommentBean;
import com.lida.cloud.widght.InnerGridView;
import com.makeramen.roundedimageview.RoundedImageView;
import com.midian.base.view.BaseTpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 商品评论列表
 * Created by WeiQingFeng on 2017/8/25.
 */

public class FragmentGoodCommentTpl extends BaseTpl<GoodCommentBean.DataBean.ListBean> {

    @BindView(R.id.gv)
    InnerGridView gv;
    @BindView(R.id.ivHead)
    RoundedImageView ivHead;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tvContent)
    TextView tvContent;

    public FragmentGoodCommentTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FragmentGoodCommentTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_fragmentgoodcomment;
    }

    @Override
    public void setBean(GoodCommentBean.DataBean.ListBean bean, int position) {
        if (bean != null) {
            gv.setAdapter(new AdapterFragmentGoodComment(_activity,bean.getImages()));
            tvName.setText(bean.getContent());
            tvContent.setText(bean.getContent());
            tvTime.setText(bean.getCreate_time()+"    颜色分类："+bean.getSpec());
        }
    }
}
