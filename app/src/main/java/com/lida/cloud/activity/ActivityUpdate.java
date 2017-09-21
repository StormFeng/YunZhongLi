package com.lida.cloud.activity;

import android.os.Bundle;
import android.widget.TextView;
import com.lida.cloud.R;
import com.makeramen.roundedimageview.RoundedImageView;
import com.midian.base.base.BaseActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.vondear.rxtools.RxDataUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 会员升级
 * Created by WeiQingFeng on 2017/9/19.
 */

public class ActivityUpdate extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.ivHead)
    RoundedImageView ivHead;
    @BindView(R.id.tvAccount)
    TextView tvAccount;

    private String head;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memberupdate);
        ButterKnife.bind(this);
        head = mBundle.getString("head");
        name = mBundle.getString("name");
        if(!RxDataUtils.isNullString(head)){
            ac.setImage(ivHead,head);
        }
        tvAccount.setText(name);
        topbar.setTitle("会员升级");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
    }
}
