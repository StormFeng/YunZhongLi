package com.lida.cloud.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.lida.cloud.R;
import com.lida.cloud.app.AppUtil;
import com.midian.base.api.ApiCallback;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.vondear.rxtools.RxDataUtils;
import com.vondear.rxtools.view.RxToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by WeiQingFeng on 2017/9/13.
 */

public class ActivityReport extends BaseActivity {
    @BindView(R.id.etCon)
    EditText etCon;
    @BindView(R.id.btnCommit)
    Button btnCommit;
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;

    private String selid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        ButterKnife.bind(this);
        selid = mBundle.getString("selid");
        topbar.setTitle("举报商家");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
    }

    @OnClick(R.id.btnCommit)
    public void onViewClicked() {
        String con = etCon.getText().toString();
        if (RxDataUtils.isNullString(con)) {
            RxToast.error("请输入要举报的内容");
            return;
        }
        AppUtil.getApiClient(ac).businessReport(selid, con, callback);
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
                RxToast.success("感谢举报，我们会在第一时间审核");
                finish();
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
}
