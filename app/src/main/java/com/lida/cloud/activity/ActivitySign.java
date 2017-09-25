package com.lida.cloud.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lida.cloud.R;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.SignMonthBean;
import com.lida.cloud.widght.signdate.SignDate;
import com.midian.base.api.ApiCallback;
import com.midian.base.app.AppManager;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.vondear.rxtools.RxActivityUtils;
import com.vondear.rxtools.view.RxToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 签到
 * Created by Administrator on 2017/8/15.
 */

public class ActivitySign extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.tvSignRule)
    TextView tvSignRule;
    @BindView(R.id.llSign)
    LinearLayout llSign;
    @BindView(R.id.signDate)
    SignDate signDate;
    @BindView(R.id.tvCredit)
    TextView tvCredit;
    @BindView(R.id.tvDays)
    TextView tvDays;
    @BindView(R.id.tvGetCredit)
    TextView tvGetCredit;
    @BindView(R.id.tvSign)
    TextView tvSign;

    private List temp = new ArrayList();
    private SignMonthBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        ButterKnife.bind(this);
        topbar.setTitle("签到");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        getData();
    }

    private void getData() {
        AppUtil.getApiClient(ac).signDetail(_activity, callback);
    }

    @OnClick({R.id.tvSignRule, R.id.llSign})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.tvSignRule:
                UIHelper.jump(_activity, ActivitySignRule.class);
                break;
            case R.id.llSign:
                if(bean.getData().size()>0){
                    if(bean.getData().get(0).is_sign()){
                        AppUtil.getApiClient(ac).sign(_activity, callback);
                    }else{
                        RxToast.warning("今天已签到");
                    }
                }

                break;
        }
    }

    ApiCallback callback = new ApiCallback() {
        @Override
        public void onApiStart(String tag) {
            showLoadingDlg();
            llSign.setClickable(false);
        }

        @Override
        public void onApiLoading(long count, long current, String tag) {
            showLoadingDlg();
        }

        @Override
        public void onApiSuccess(NetResult res, String tag) {
            hideLoadingDlg();
            llSign.setClickable(true);
            if (res.isOK()) {
                if ("sign".equals(tag)) {
                    RxToast.success("签到成功！");
                    getData();
                    Intent intent = new Intent("android.intent.action.PersonalInfoRefreshBroadCast");
                    sendBroadcast(intent);
                }
                if ("signDetail".equals(tag)) {
                    bean = (SignMonthBean) res;
                    if (bean.getData().size() > 0) {
                        SignMonthBean.DataBean data = bean.getData().get(0);
                        signDate.setInitData(data.getDays());
                        tvCredit.setText("我的信用积分：" + data.getMem_credit());
                        tvGetCredit.setText("今日签到可获" + data.getToday_get_credit() + "积分");
                        tvDays.setText("连续签到" + data.getContinuity_days() + "天");
                        if(data.is_sign()){
                            tvSign.setText("签到");
                        }else{
                            tvSign.setText("今天已签到");
                        }
                    } else {
                        signDate.setInitData(temp);
                    }
                }
            } else {
                RxToast.error(res.getMessage());
                if("10001".equals(res.getErrorCode())){
                    ac.clearUserInfo();
                    RxActivityUtils.skipActivityAndFinishAll(AppManager.getAppManager().currentActivity(), ActivityLoginAct.class);
                }else if("10002".equals(res.getErrorCode())||"10003".equals(res.getErrorCode())){
                    AppUtil.getApiClient(ac).token(ac.memid,ac.refresh_token,callback);
                }
            }
        }

        @Override
        public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
            hideLoadingDlg();
            llSign.setClickable(true);
            RxToast.error("网络异常");
        }

        @Override
        public void onParseError(String tag) {
            hideLoadingDlg();
            llSign.setClickable(true);
            RxToast.showToast("数据解析异常");
        }
    };
}
