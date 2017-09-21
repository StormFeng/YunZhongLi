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
import com.vondear.rxtools.RxActivityUtils;
import com.vondear.rxtools.RxDataUtils;
import com.vondear.rxtools.RxUtils;
import com.vondear.rxtools.view.RxToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 绑定手机号
 * Created by Administrator on 2017/8/9.
 */

public class ActivityBindNewPhone extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.btnCode)
    Button btnCode;
    @BindView(R.id.btnConfirm)
    Button btnConfirm;
    @BindView(R.id.etNewPhone)
    EditText etNewPhone;
    @BindView(R.id.etCode)
    EditText etCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bindnewphone);
        ButterKnife.bind(this);
        topbar.setTitle("绑定手机号");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
    }

    @OnClick({R.id.btnCode, R.id.btnConfirm})
    public void onViewClicked(View view) {
        String phone = etNewPhone.getText().toString();
        if("".equals(phone)){
            RxToast.error(_activity, "请输入手机号码").show();
            AnimatorUtils.onVibrationView(etNewPhone);
            return;
        }
        switch (view.getId()) {
            case R.id.btnCode:
                AppUtil.getApiClient(ac).getMobilecode(phone, "bind", callback);
                break;
            case R.id.btnConfirm:
                String code = etCode.getText().toString();
                if("".equals(code)){
                    RxToast.error(_activity, "请输入验证码").show();
                    AnimatorUtils.onVibrationView(etCode);
                    return;
                }
                AppUtil.getApiClient(ac).bindPhone(code, phone, callback);
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
                if("bindPhone".equals(tag)){
                    RxToast.success(_activity, "绑定成功！").show();
                    setResult(RESULT_OK);
                    finish();
                }
            }else{
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
