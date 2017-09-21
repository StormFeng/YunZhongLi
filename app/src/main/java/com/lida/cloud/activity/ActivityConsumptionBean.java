package com.lida.cloud.activity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.lida.cloud.R;
import com.lida.cloud.alipay.AlipayUtils;
import com.lida.cloud.app.AesEncryptionUtil;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.PayBean;
import com.lida.cloud.bean.PersonalInfoBean;
import com.lida.cloud.bean.SignBean;
import com.lida.cloud.broadcast.PersonalInfoRefreshBroadCast;
import com.midian.base.api.ApiCallback;
import com.midian.base.app.AppManager;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.vondear.rxtools.RxActivityUtils;
import com.vondear.rxtools.view.RxToast;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 消费豆
 * Created by Administrator on 2017/8/8.
 */

public class ActivityConsumptionBean extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.tvBalance)
    TextView tvBalance;
    @BindView(R.id.llDetailList)
    LinearLayout llDetailList;

    private PersonalInfoRefreshBroadCast personalInfoRefreshBroadCast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumptionbean);
        ButterKnife.bind(this);
        topbar.setTitle("消费豆");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        getData();
        personalInfoRefreshBroadCast = new PersonalInfoRefreshBroadCast(){
            @Override
            public void onReceive(Context context, Intent intent) {
                super.onReceive(context, intent);
                getData();
            }
        };
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.PersonalInfoRefreshBroadCast");
        registerReceiver(personalInfoRefreshBroadCast,filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(personalInfoRefreshBroadCast);
    }

    private void getData() {
        AppUtil.getApiClient(ac).getPersonalInfo(_activity,ac.memid, callback);
    }

    @OnClick({R.id.llRecharge, R.id.llDetailList})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llRecharge:
                UIHelper.jump(_activity,ActivityRechargeBean.class);
                break;
            case R.id.llDetailList:
                UIHelper.jump(_activity,ActivityConsumptionBeanDetail.class);
                break;
        }
    }

    ApiCallback callback = new ApiCallback() {
        @Override
        public void onApiStart(String tag) {
            showLoadingDlg();
        }

        @Override
        public void onApiLoading(long count, long current, String tag) {
            showLoadingDlg();
        }

        @Override
        public void onApiSuccess(NetResult res, String tag) {
            hideLoadingDlg();
            if (res.isOK()) {
                PersonalInfoBean bean = (PersonalInfoBean) res;
                PersonalInfoBean.DataBean dataBean = bean.getData().get(0);
                tvBalance.setText(dataBean.getMem_pay_beans());
            }else{
                RxToast.error(_activity,res.getMessage()).show();
                if("10001".equals(res.getErrorCode())||"10002".equals(res.getErrorCode())
                        ||"10003".equals(res.getErrorCode())){
                    ac.clearUserInfo();
                    RxActivityUtils.skipActivityAndFinishAll(AppManager.getAppManager().currentActivity(), ActivityLoginAct.class);
                }
            }
        }

        @Override
        public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
            hideLoadingDlg();
            RxToast.error(_activity, "网络异常").show();
        }

        @Override
        public void onParseError(String tag) {
            hideLoadingDlg();
            RxToast.error(_activity, "数据解析异常").show();
        }
    };
}
