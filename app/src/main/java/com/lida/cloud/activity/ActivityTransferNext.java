package com.lida.cloud.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.lida.cloud.R;
import com.lida.cloud.app.AesEncryptionUtil;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.SignBean;
import com.makeramen.roundedimageview.RoundedImageView;
import com.midian.base.api.ApiCallback;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.AnimatorUtils;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.vondear.rxtools.RxActivityUtils;
import com.vondear.rxtools.view.RxToast;
import com.vondear.rxtools.view.dialog.RxDialogSureCancel;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 转账-下一步
 * Created by Administrator on 2017/8/14.
 */

public class ActivityTransferNext extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.ivHead)
    RoundedImageView ivHead;
    @BindView(R.id.tvAccount)
    TextView tvAccount;
    @BindView(R.id.etMoney)
    EditText etMoney;
    @BindView(R.id.etDes)
    EditText etDes;
    @BindView(R.id.btnConfirm)
    Button btnConfirm;
    @BindView(R.id.tvMoney)
    TextView tvMoney;

    private String account;
    private String targetId;
    private String money;
    private String des;
    private RxDialogSureCancel dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfernext);
        ButterKnife.bind(this);
        account = mBundle.getString("account");
        tvAccount.setText(account);
        topbar.setTitle("转账确认");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        AppUtil.getApiClient(ac).balanceSearch(_activity,ac.memid,account,callback);
    }

    @OnClick(R.id.btnConfirm)
    public void onViewClicked() {
        money = etMoney.getText().toString();
        des = etDes.getText().toString();
        if("".equals(money)){
            RxToast.error("请输入转账金额");
            AnimatorUtils.onVibrationView(etMoney);
            return;
        }
        if(dialog==null){
            dialog = new RxDialogSureCancel(_activity);
            dialog.getTvTitle().setVisibility(View.GONE);
            dialog.setContent("确认转账？");
            dialog.setCancelListener(onClickListener);
            dialog.setSureListener(onClickListener);
        }
        dialog.show();
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.tv_sure:
                    AppUtil.getApiClient(ac).balanceTransfer(_activity,targetId,money,des,callback);
                    break;
                case R.id.tv_cancel:
                    dialog.dismiss();
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
                if("balanceSearch".equals(tag)){
                    SignBean bean = (SignBean) res;
                    String decrypt = AesEncryptionUtil.decrypt(bean.getData().get(0).getAesData());
                    try {
                        JSONObject jsonObject = new JSONObject(decrypt);
                        ac.setImage(ivHead,jsonObject.getString("tar_thumb"));
                        tvMoney.setText("可用余额"+jsonObject.getString("balance")+"元");
                        targetId = jsonObject.getString("tar_id");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                if("balanceTransfer".equals(tag)){
                    RxToast.success("转账申请提交成功！");
                    Intent intent = new Intent("android.intent.action.PersonalInfoRefreshBroadCast");
                    sendBroadcast(intent);
                    setResult(RESULT_OK);
                    finish();
                }
            }else{
                RxToast.error(res.getMessage());
                finish();
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
