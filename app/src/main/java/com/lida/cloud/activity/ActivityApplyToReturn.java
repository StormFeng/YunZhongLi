package com.lida.cloud.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lida.cloud.R;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.widght.dialog.DialogChooseReturnType;
import com.midian.base.api.ApiCallback;
import com.midian.base.app.AppManager;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.vondear.rxtools.RxActivityUtils;
import com.vondear.rxtools.view.RxToast;
import com.vondear.rxtools.view.dialog.RxDialogSureCancel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 退货申请
 * Created by WeiQingFeng on 2017/9/19.
 */

public class ActivityApplyToReturn extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.tvType)
    TextView tvType;
    @BindView(R.id.tvMoney)
    TextView tvMoney;
    @BindView(R.id.etDes)
    EditText etDes;
    @BindView(R.id.btnCommit)
    Button btnCommit;

    private RxDialogSureCancel dialogSureCancel;
    private DialogChooseReturnType dialogChooseReturnType;
    private String type = "2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applytoreturn);
        ButterKnife.bind(this);
        tvMoney.setText("￥" + mBundle.getString("money"));
        topbar.setTitle("退货申请");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
    }

    @OnClick({R.id.btnCommit,R.id.tvType})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.btnCommit:
                final String des = etDes.getText().toString();
                if ("".equals(des)) {
                    RxToast.error("请填写退款说明");
                    return;
                }
                if (dialogSureCancel == null) {
                    dialogSureCancel = new RxDialogSureCancel(_activity);
                    dialogSureCancel.getTvTitle().setVisibility(View.GONE);
                    dialogSureCancel.setContent("确认退款？");
                    dialogSureCancel.setCancelListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialogSureCancel.dismiss();
                        }
                    });
                    dialogSureCancel.setSureListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AppUtil.getApiClient(ac).orderRefund(type, des, mBundle.getString("id"), callback);
                        }
                    });
                }
                dialogSureCancel.show();
                break;
            case R.id.tvType:
                if(mBundle.getBoolean("click")){
                    if(dialogChooseReturnType==null){
                        dialogChooseReturnType = new DialogChooseReturnType(_activity);
                        dialogChooseReturnType.setTypeSelectedListener(new DialogChooseReturnType.onTypeSelectedListener() {
                            @Override
                            public void returnMomey() {
                                type = "2";
                                tvType.setText("仅退款");
                            }

                            @Override
                            public void returnMomeyAndGood() {
                                type = "1";
                                tvType.setText("退货退款");
                            }
                        });
                    }
                    dialogChooseReturnType.show();
                }
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
                RxToast.success("退款申请提交成功，请等待审核");
                Intent intent = new Intent("android.intent.action.RefreshOrderList");
                sendBroadcast(intent);
                finish();
            } else {
                RxToast.error(res.getMessage());
                if ("10001".equals(res.getErrorCode()) || "10002".equals(res.getErrorCode())
                        || "10003".equals(res.getErrorCode())) {
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
