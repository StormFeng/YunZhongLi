package com.lida.cloud;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lida.cloud.activity.ActivityLoginAct;
import com.midian.base.app.AppManager;
import com.midian.base.base.BaseActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.vondear.rxtools.RxActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 支付成功
 * Created by WeiQingFeng on 2017/9/8.
 */

public class ActivityPaySuccess extends BaseActivity {
    @BindView(R.id.btnBack)
    Button btnBack;
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paysuccess);
        ButterKnife.bind(this);
        topbar.setTitle("付款成功");
        topbar.setLeftImageButton(R.drawable.icon_back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                finish();
            }
        });
    }

    @OnClick(R.id.btnBack)
    public void onViewClicked() {
        RxActivityUtils.skipActivityAndFinishAll(AppManager.getAppManager().currentActivity(), ActivityLoginAct.class);
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK);
        super.onBackPressed();
    }
}
