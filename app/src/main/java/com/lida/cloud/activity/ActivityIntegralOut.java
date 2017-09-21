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
 * 转出积分
 * Created by WeiQingFeng on 2017/8/31.
 */

public class ActivityIntegralOut extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.etAccount)
    EditText etAccount;
    @BindView(R.id.etIntegral)
    EditText etIntegral;
    @BindView(R.id.btnConfirm)
    Button btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integralout);
        ButterKnife.bind(this);
        topbar.setTitle("转出积分");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
    }

    @OnClick(R.id.btnConfirm)
    public void onViewClicked() {
        String name = etAccount.getText().toString();
        String integral = etIntegral.getText().toString();
        if("".equals(name)){
            RxToast.error("请输入会员账号");
            AnimatorUtils.onVibrationView(etAccount);
            return;
        }
        if("".equals(integral)){
            RxToast.error("请输入转出积分");
            AnimatorUtils.onVibrationView(etIntegral);
            return;
        }

        AppUtil.getApiClient(ac).businessTransfer(_activity,name,integral,callback);
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
                RxToast.success("转出成功！");
                finish();
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
            RxToast.error("网络异常");
        }

        @Override
        public void onParseError(String tag) {
            hideLoadingDlg();
            RxToast.error("数据解析异常");
        }
    };
}
