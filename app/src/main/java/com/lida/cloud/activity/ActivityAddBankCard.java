package com.lida.cloud.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lida.cloud.R;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.SupportBankCardListBean;
import com.midian.base.api.ApiCallback;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.AnimatorUtils;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.vondear.rxtools.RxDataUtils;
import com.vondear.rxtools.RxRegUtils;
import com.vondear.rxtools.view.RxToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 添加银行卡
 * Created by WeiQingFeng on 2017/8/28.
 */

public class ActivityAddBankCard extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etCardNum)
    EditText etCardNum;
    @BindView(R.id.btnConfim)
    Button btnConfim;
    @BindView(R.id.tvChooseBank)
    TextView tvChooseBank;

    private SupportBankCardListBean.DataBean cardInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addbankcard);
        ButterKnife.bind(this);
        topbar.setTitle("添加银行卡");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
    }

    @OnClick({R.id.btnConfim,R.id.tvChooseBank})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.btnConfim:
                String name = etName.getText().toString();
                String cardNum = etCardNum.getText().toString();
                if("".equals(name)){
                    RxToast.error("请填写姓名");
                    AnimatorUtils.onVibrationView(etName);
                    return;
                }
                if("".equals(cardNum)){
                    RxToast.error("请填写银行卡号码");
                    AnimatorUtils.onVibrationView(etCardNum);
                    return;
                }
                if(!RxRegUtils.isBankCard(cardNum)){
                    RxToast.error("银行卡号码格式不正确");
                    AnimatorUtils.onVibrationView(etCardNum);
                    return;
                }
                if(cardInfo==null){
                    RxToast.error("请选择发卡银行");
                    AnimatorUtils.onVibrationView(tvChooseBank);
                    return;
                }
                AppUtil.getApiClient(ac).bankBind(_activity,cardNum,name,cardInfo.getBank_id(),callback);
                break;
            case R.id.tvChooseBank:
                UIHelper.jumpForResult(_activity,ActivitySupportBankList.class,1001);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(RESULT_OK == resultCode){
            cardInfo = (SupportBankCardListBean.DataBean) data.getSerializableExtra("data");
            tvChooseBank.setText("选择发卡银行    "+cardInfo.getBank_name());
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
            if(res.isOK()){
                RxToast.success("添加成功！");
                setResult(RESULT_OK);
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
