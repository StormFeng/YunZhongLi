package com.lida.cloud.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lida.cloud.MainActivity;
import com.lida.cloud.R;
import com.lida.cloud.app.AppUtil;
import com.midian.base.api.ApiCallback;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.AnimatorUtils;
import com.vondear.rxtools.RxActivityUtils;
import com.vondear.rxtools.RxRegUtils;
import com.vondear.rxtools.RxUtils;
import com.vondear.rxtools.view.RxToast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 注册
 * Created by WeiQingFeng on 2017/8/24.
 */

public class ActivityRegister extends BaseActivity {

    @BindView(R.id.etAccount)
    EditText etAccount;
    @BindView(R.id.etPhone)
    EditText etPhone;
    @BindView(R.id.etCode)
    EditText etCode;
    @BindView(R.id.btnCode)
    Button btnCode;
    @BindView(R.id.etPass)
    EditText etPass;
    @BindView(R.id.etPassAgain)
    EditText etPassAgain;
//    @BindView(R.id.etName)
//    EditText etName;
//    @BindView(R.id.etCardID)
//    EditText etCardID;
    @BindView(R.id.btnRegister)
    Button btnRegister;
    @BindView(R.id.etRecName)
    EditText etRecName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnCode, R.id.btnRegister})
    public void onViewClicked(View view) {
        String account=etAccount.getText().toString();
        String phone=etPhone.getText().toString();
        String code=etCode.getText().toString();
        String pass=etPass.getText().toString();
        String passAgain=etPassAgain.getText().toString();
//        String realName=etName.getText().toString();
//        String cardId=etCardID.getText().toString();
        String recName=etRecName.getText().toString();
        switch (view.getId()) {
            case R.id.btnCode:
                if(!RxRegUtils.isMobileExact(phone)){
                    AnimatorUtils.onVibrationView(etPhone);
                    RxToast.error(_activity,"请输入正确的手机号码").show();
                    return;
                }
                AppUtil.getApiClient(ac).getMobilecode(phone,"register",apiCallback);
                break;
            case R.id.btnRegister:
                if("".equals(account)){
                    AnimatorUtils.onVibrationView(etAccount);
                    RxToast.warning(_activity,"请输入用户名").show();
                    return;
                }
                if("".equals(phone)){
                    AnimatorUtils.onVibrationView(etPhone);
                    RxToast.warning(_activity,"请输入手机号码").show();
                    return;
                }
                if(!RxRegUtils.isMobileExact(phone)){
                    AnimatorUtils.onVibrationView(etPhone);
                    RxToast.error(_activity,"请输入正确的手机号码").show();
                    return;
                }
                if("".equals(pass)){
                    AnimatorUtils.onVibrationView(etPass);
                    RxToast.warning(_activity,"请输入密码").show();
                    return;
                }
                if("".equals(passAgain)){
                    AnimatorUtils.onVibrationView(etPassAgain);
                    RxToast.warning(_activity,"请再次确认密码").show();
                    return;
                }
                if(!pass.equals(passAgain)){
                    AnimatorUtils.onVibrationView(etPassAgain);
                    RxToast.error(_activity,"两次输入的密码不一致").show();
                    return;
                }
//                if("".equals(realName)){
//                    AnimatorUtils.onVibrationView(etName);
//                    RxToast.warning(_activity,"请输入真实姓名").show();
//                    return;
//                }
//                if(!RxRegUtils.isUsername(realName)){
//                    AnimatorUtils.onVibrationView(etName);
//                    RxToast.warning(_activity,"姓名格式不正确").show();
//                    return;
//                }
//                if("".equals(cardId)){
//                    AnimatorUtils.onVibrationView(etCardID);
//                    RxToast.warning(_activity,"请输入身份证号码").show();
//                    return;
//                }
//                if(!RxRegUtils.validateIdCard(cardId)){
//                    AnimatorUtils.onVibrationView(etCardID);
//                    RxToast.error(_activity,"身份证号码格式不正确").show();
//                    return;
//                }
                AppUtil.getApiClient(ac).register(account,pass,phone,recName,code,apiCallback);
                break;
        }
    }
    ApiCallback apiCallback = new ApiCallback() {
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
                if("getMobilecode".equals(tag)){
                    RxToast.success(_activity, "验证码发送成功").show();
                    RxUtils.countDown(btnCode,120000,1000,"获取验证码");
                }
                if("register".equals(tag)){
                    RxToast.success(_activity, "注册成功!").show();
                    finish();
                }
            }else{
                RxToast.error(_activity, res.getMessage()).show();
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
















