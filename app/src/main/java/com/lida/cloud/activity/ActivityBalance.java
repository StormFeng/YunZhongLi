package com.lida.cloud.activity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lida.cloud.R;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.PersonalInfoBean;
import com.lida.cloud.broadcast.PersonalInfoRefreshBroadCast;
import com.midian.base.api.ApiCallback;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.vondear.rxtools.view.RxToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 余额
 * Created by Administrator on 2017/8/8.
 */

public class ActivityBalance extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.tvBalance)
    TextView tvBalance;
    @BindView(R.id.llRecharge)
    LinearLayout llRecharge;
    @BindView(R.id.llGetMoney)
    LinearLayout llGetMoney;
    @BindView(R.id.llTransferMoney)
    LinearLayout llTransferMoney;
    @BindView(R.id.llDetailList)
    LinearLayout llDetailList;

    private PersonalInfoRefreshBroadCast personalInfoRefreshBroadCast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);
        ButterKnife.bind(this);
        topbar.setTitle("余额");
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
        _activity.registerReceiver(personalInfoRefreshBroadCast,filter);
    }

    private void getData() {
        AppUtil.getApiClient(ac).getPersonalInfo(_activity, ac.memid, callback);
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
                tvBalance.setText(bean.getData().get(0).getMem_yue());
            } else {
                RxToast.error(res.getMessage());
            }
        }

        @Override
        public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
            hideLoadingDlg();
            RxToast.error("网络异常");
        }

        @Override
        public void onParseError(String tag) {
            hideLoadingDlg();
            RxToast.error("数据解析异常");
        }
    };

    @OnClick({R.id.llRecharge, R.id.llGetMoney, R.id.llTransferMoney, R.id.llDetailList})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llRecharge:
                UIHelper.jump(_activity, ActivityRecharge.class);
                break;
            case R.id.llGetMoney:
                String money = tvBalance.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("money",money);
                UIHelper.jump(_activity, ActivityGetMoney.class,bundle);
                break;
            case R.id.llTransferMoney:
                UIHelper.jump(_activity, ActivityTransfer.class);
                break;
            case R.id.llDetailList:
                UIHelper.jump(_activity, ActivityBalanceDetail.class);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(personalInfoRefreshBroadCast);
    }
}