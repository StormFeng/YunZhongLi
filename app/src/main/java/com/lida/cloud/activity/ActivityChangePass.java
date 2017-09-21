package com.lida.cloud.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.lida.cloud.R;
import com.lida.cloud.app.AppUtil;
import com.midian.base.api.ApiCallback;
import com.midian.base.app.AppManager;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.AnimatorUtils;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.vondear.rxtools.RxActivityUtils;
import com.vondear.rxtools.view.RxToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 修改密码
 * Created by Administrator on 2017/8/9.
 */

public class ActivityChangePass extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.etOldPass)
    EditText etOldPass;
    @BindView(R.id.etPass)
    EditText etPass;
    @BindView(R.id.etPassAgain)
    EditText etPassAgain;
    @BindView(R.id.btnConfim)
    Button btnConfim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepass);
        ButterKnife.bind(this);
        topbar.setTitle("登录密码");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
    }

    @OnClick(R.id.btnConfim)
    public void onViewClicked() {
        String oldPass = etOldPass.getText().toString();
        String newPass = etPass.getText().toString();
        String newPassAgain = etPassAgain.getText().toString();
        if("".equals(oldPass)){
            AnimatorUtils.onVibrationView(etOldPass);
            RxToast.error(_activity,"请输入原有密码").show();
            return;
        }
        if("".equals(newPass)){
            AnimatorUtils.onVibrationView(etPass);
            RxToast.error(_activity,"请输入新密码密码").show();
            return;
        }
        if(!newPass.equals(newPassAgain)){
            AnimatorUtils.onVibrationView(etPass);
            AnimatorUtils.onVibrationView(etPassAgain);
            RxToast.error(_activity,"两次输入的密码不一致").show();
            return;
        }
        AppUtil.getApiClient(ac).memberModify(_activity,mBundle.getString("phone"),oldPass,newPass,newPassAgain,callback);
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
            if(res.isOK()){
                RxToast.success("修改成功,请重新登录");
                ac.clearUserInfo();
                RxActivityUtils.skipActivityAndFinishAll(AppManager.getAppManager().currentActivity(), ActivityLoginAct.class);
            }else{
                RxToast.error(res.getMessage());
                if("10001".equals(res.getErrorCode())||"10002".equals(res.getErrorCode())){
                    ac.clearUserInfo();
                    RxActivityUtils.skipActivityAndFinishAll(AppManager.getAppManager().currentActivity(), ActivityLoginAct.class);
                }
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
}
