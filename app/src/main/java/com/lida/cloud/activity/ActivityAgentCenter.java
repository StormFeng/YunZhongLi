package com.lida.cloud.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lida.cloud.R;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.ActivityAgentCenterBean;
import com.lida.cloud.widght.BaseApiCallback;
import com.makeramen.roundedimageview.RoundedImageView;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.vondear.rxtools.RxActivityUtils;
import com.vondear.rxtools.view.dialog.RxDialogSureCancel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 代理中心
 * Created by WeiQingFeng on 2017/9/1.
 */

public class ActivityAgentCenter extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.tvSales)
    TextView tvSales;
    @BindView(R.id.tvIncome)
    TextView tvIncome;
    @BindView(R.id.area)
    TextView area;
    @BindView(R.id.ivHead)
    RoundedImageView ivHead;

    private RxDialogSureCancel dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agentcenter);
        ButterKnife.bind(this);
        topbar.setTitle("代理中心");
        if("1".equals(ac.isagent)){
            topbar.setRightText("退出", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(dialog==null){
                        dialog = new RxDialogSureCancel(_activity);
                        dialog.getTvTitle().setVisibility(View.GONE);
                        dialog.setContent("退出登录？");
                        dialog.setSureListener(onClickListener);
                        dialog.setCancelListener(onClickListener);
                    }
                    dialog.show();
                }
            });
        }else{
            topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        }
        AppUtil.getApiClient(ac).agent(callback);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.tv_sure:
                    ac.clearUserInfo();
                    RxActivityUtils.skipActivityAndFinishAll(_activity,ActivityLoginAct.class);
                    break;
                case R.id.tv_cancel:
                    dialog.dismiss();
                    break;
            }
        }
    };

    @OnClick({R.id.tvSales, R.id.tvIncome})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvSales:
                UIHelper.jump(_activity, ActivitySalesChooseTime.class);
                break;
            case R.id.tvIncome:
                UIHelper.jump(_activity, ActivityAgentCenterRevenue.class);
                break;
        }
    }

    BaseApiCallback callback = new BaseApiCallback() {
        @Override
        public void onApiStart(String tag) {
            super.onApiStart(tag);
            showLoadingDlg();
        }

        @Override
        public void onApiSuccess(NetResult res, String tag) {
            super.onApiSuccess(res, tag);
            hideLoadingDlg();
            if (res.isOK()) {
                ActivityAgentCenterBean bean = (ActivityAgentCenterBean) res;
                if (bean != null && bean.getData() != null && bean.getData().size() > 0) {
                    ActivityAgentCenterBean.DataBean dataBean = bean.getData().get(0);
                    area.setText(dataBean.getArea() + dataBean.getType());
                    ac.setImage(ivHead,dataBean.getAvatar()==null?"":dataBean.getAvatar());
                }
            } else {
                ac.handleErrorCode(_activity, res.getMessage());
                tvIncome.setClickable(false);
                tvSales.setClickable(false);
            }
        }

        @Override
        public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
            super.onApiFailure(t, errorNo, strMsg, tag);
            showLoadingDlg();
        }
    };
}
