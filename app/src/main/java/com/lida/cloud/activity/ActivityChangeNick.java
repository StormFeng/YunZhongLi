package com.lida.cloud.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.lida.cloud.R;
import com.lida.cloud.app.AppUtil;
import com.midian.base.api.ApiCallback;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.AnimatorUtils;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.vondear.rxtools.view.RxToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 修改昵称
 * Created by Administrator on 2017/8/9.
 */

public class ActivityChangeNick extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.etNick)
    EditText etNick;
    @BindView(R.id.btnOk)
    Button btnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changenick);
        ButterKnife.bind(this);
        topbar.setTitle("昵称");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
    }

    @OnClick(R.id.btnOk)
    public void onViewClicked() {
        String var = etNick.getText().toString();
        if("".equals(var)){
            RxToast.error("请输入昵称");
            AnimatorUtils.onVibrationView(btnOk);
            return;
        }
        if(var.length()<2 || var.length()>20){
            RxToast.error("请注意字符长度限制");
            AnimatorUtils.onVibrationView(btnOk);
            return;
        }
        AppUtil.getApiClient(ac).memberNickname(ac.memid, var, callback);
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
                RxToast.success("修改成功！");
                setResult(RESULT_OK);
                finish();
            }else{
                RxToast.error(res.getMessage());
                AnimatorUtils.onVibrationView(btnOk);
            }
        }

        @Override
        public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
            RxToast.error("网络异常");
            AnimatorUtils.onVibrationView(btnOk);
        }

        @Override
        public void onParseError(String tag) {
            RxToast.error("数据解析异常");
            AnimatorUtils.onVibrationView(btnOk);
        }
    };
}
