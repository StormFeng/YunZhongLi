package com.lida.cloud.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lida.cloud.R;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.GetPhoneBean;
import com.midian.base.api.ApiCallback;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.AnimatorUtils;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.vondear.rxtools.RxRegUtils;
import com.vondear.rxtools.RxUtils;
import com.vondear.rxtools.view.RxToast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 忘记密码
 * Created by Administrator on 2017/8/9.
 */

public class ActivityForgetPass extends BaseActivity {

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.etPhone)
    EditText etPhone;
    @BindView(R.id.etCode)
    EditText etCode;
    @BindView(R.id.btnCode)
    Button btnCode;
    @BindView(R.id.etPass)
    EditText etPass;
    @BindView(R.id.btnConfirm)
    Button btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpass);
        ButterKnife.bind(this);
        topbar.setTitle("忘记密码");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
    }

    @OnClick({R.id.btnCode, R.id.btnConfirm})
    public void onViewClicked(View view) {
        String phone = etPhone.getText().toString();
        String code = etCode.getText().toString();
        String pass = etPass.getText().toString();
        if ("".equals(phone)) {
            RxToast.error("请输入手机号码");
            AnimatorUtils.onVibrationView(etPhone);
            return;
        }
        if (!RxRegUtils.isMobileExact(phone)) {
            RxToast.error("手机号码格式不正确");
            AnimatorUtils.onVibrationView(etPhone);
            return;
        }
        switch (view.getId()) {
            case R.id.btnCode:
                AppUtil.getApiClient(ac).getMobilecode(phone, "forget", callback);
                break;
            case R.id.btnConfirm:
                if ("".equals(code)) {
                    RxToast.error(_activity, "请输入验证码").show();
                    AnimatorUtils.onVibrationView(etCode);
                    return;
                }
                if ("".equals(pass)) {
                    RxToast.error(_activity, "请输入新密码").show();
                    AnimatorUtils.onVibrationView(etPass);
                    return;
                }
                AppUtil.getApiClient(ac).forget(phone, pass,code, callback);
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
                if ("getMobilecode".equals(tag)) {
                    RxUtils.countDown(btnCode, 120000, 1000, "获取验证码");
                }
                if("forget".equals(tag)){
                    RxToast.success("密码找回成功");
                    finish();
                }
            } else {
                RxToast.error(_activity, res.getMessage()).show();
                AnimatorUtils.onVibrationView(btnConfirm);
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
