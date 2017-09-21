package com.lida.cloud.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lida.cloud.R;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.GetPhoneBean;
import com.midian.base.api.ApiCallback;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.AnimatorUtils;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.vondear.rxtools.RxDataUtils;
import com.vondear.rxtools.RxUtils;
import com.vondear.rxtools.view.RxToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 绑定手机号第一步
 * Created by Administrator on 2017/8/9.
 */

public class ActivityBindNewPhoneFirstStep extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.btnCode)
    Button btnCode;
    @BindView(R.id.btnConfirm)
    Button btnConfirm;
    @BindView(R.id.tvPhone)
    TextView tvPhone;
    @BindView(R.id.etCode)
    EditText etCode;

    private String mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bindnewphonefirststep);
        ButterKnife.bind(this);
        topbar.setTitle("绑定手机号");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        AppUtil.getApiClient(ac).getPhone(_activity,ac.memid, callback);
    }

    @OnClick({R.id.btnCode, R.id.btnConfirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnCode:
                AppUtil.getApiClient(ac).getMobilecode(mobile, "relieve", callback);
                break;
            case R.id.btnConfirm:
                String code = etCode.getText().toString();
                if("".equals(code)){
                    RxToast.error(_activity, "请输入验证码").show();
                    AnimatorUtils.onVibrationView(etCode);
                    return;
                }
                AppUtil.getApiClient(ac).commitCode(_activity,code,callback);
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
                if ("getPhone".equals(tag)) {
                    GetPhoneBean bean = (GetPhoneBean) res;
                    mobile = bean.getData().get(0).getMobile();
                    tvPhone.setText("当前绑定手机" + RxDataUtils.hideMobilePhone4(mobile));
                }
                if ("getMobilecode".equals(tag)) {
                    RxUtils.countDown(btnCode, 120000, 1000, "获取验证码");
                }
                if("commitCode".equals(tag)){
                    UIHelper.jumpForResult(_activity,ActivityBindNewPhone.class,1001);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(RESULT_OK == resultCode){
            finish();
        }
    }
}
