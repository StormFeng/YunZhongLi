package com.lida.cloud.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alipay.sdk.app.PayTask;
import com.apkfuns.logutils.LogUtils;
import com.lida.cloud.R;
import com.lida.cloud.alipay.AlipayUtils;
import com.lida.cloud.app.AesEncryptionUtil;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.PayBean;
import com.lida.cloud.alipay.PayResult;
import com.lida.cloud.bean.SignBean;
import com.midian.base.api.ApiCallback;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.AnimatorUtils;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.vondear.rxtools.view.RxToast;
import com.vondear.rxtools.view.dialog.RxDialogSureCancel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 充值
 * Created by Administrator on 2017/8/8.
 */

public class ActivityRecharge extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.etMoney)
    EditText etMoney;
    @BindView(R.id.btnConfirm)
    Button btnConfirm;

    private RxDialogSureCancel dialog;
    private String money;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);
        ButterKnife.bind(this);
        topbar.setTitle("充值");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
    }

    @OnClick(R.id.btnConfirm)
    public void onViewClicked() {
        money = etMoney.getText().toString();
        if("".equals(money)){
            RxToast.error("请输入金额");
            AnimatorUtils.onVibrationView(btnConfirm);
            return;
        }
        if(dialog==null){
            dialog = new RxDialogSureCancel(_activity);
            dialog.getTvTitle().setVisibility(View.GONE);
            dialog.getTvContent().setText("确认支付？");
            dialog.setSureListener(onClickListener);
            dialog.setCancelListener(onClickListener);
        }
        dialog.show();
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.tv_cancel:
                    dialog.dismiss();
                    break;
                case R.id.tv_sure:
                    AppUtil.getApiClient(ac).balanceRecharge(_activity,money,callback);
                    break;
            }
        }
    };

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
            if(res.isOK()){
                if("balanceRecharge".equals(tag)){
                    SignBean bean = (SignBean) res;
                    String sign = AesEncryptionUtil.decrypt(bean.getData().get(0).getAesData());
                    JSONObject jsonObject;
                    String pay_sn = null;
                    try {
                        jsonObject = new JSONObject(sign);
                        pay_sn = jsonObject.getString("pay_sn");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    AppUtil.getApiClient(ac).payAlipay(pay_sn,callback);
                }
                if("payAlipay".equals(tag)){
                    PayBean bean = (PayBean) res;
                    String orderInfo = bean.getData().get(0).getOrderString();
                    LogUtils.e(orderInfo);
                    new AlipayUtils().builder(_activity,orderInfo).pay();
                }
            }else{
                RxToast.error(res.getMessage());
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
