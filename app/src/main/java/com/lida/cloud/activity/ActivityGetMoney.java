package com.lida.cloud.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.lida.cloud.R;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.MyBankCardListBean;
import com.midian.base.api.ApiCallback;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.AnimatorUtils;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.vondear.rxtools.RxDataUtils;
import com.vondear.rxtools.view.RxToast;
import com.vondear.rxtools.view.dialog.RxDialogSureCancel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 提现
 * Created by WeiQingFeng on 2017/8/28.
 */

public class ActivityGetMoney extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.tvBalance)
    TextView tvBalance;
    @BindView(R.id.btnGetAll)
    TextView btnGetAll;
    @BindView(R.id.btnConfirm)
    Button btnConfirm;
    @BindView(R.id.tvBankName)
    TextView tvBankName;
    @BindView(R.id.tvCardNum)
    TextView tvCardNum;
    @BindView(R.id.etMoney)
    EditText etMoney;
    @BindView(R.id.ivBank)
    ImageView ivBank;
    @BindView(R.id.llChooseCard)
    LinearLayout llChooseCard;

    private MyBankCardListBean.DataBean cardInfo;
    private RxDialogSureCancel dialog;
    private String money;
    private String allMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getmoney);
        ButterKnife.bind(this);
        allMoney = mBundle.getString("money");
        tvBalance.setText("可用余额"+ allMoney +"元");
        topbar.setTitle("提现");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
    }

    @OnClick({R.id.btnGetAll, R.id.btnConfirm, R.id.llChooseCard})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llChooseCard:
                Bundle bundle = new Bundle();
                bundle.putString("flag", "ActivityGetMoney");
                UIHelper.jumpForResult(_activity, ActivityBankCard.class,bundle,1001);
                break;
            case R.id.btnGetAll:
                etMoney.setText(allMoney);
                break;
            case R.id.btnConfirm:
                money = etMoney.getText().toString();
                if (cardInfo == null) {
                    RxToast.error("请选择提现银行卡");
                    AnimatorUtils.onVibrationView(llChooseCard);
                    return;
                }
                if ("".equals(money)) {
                    RxToast.error("请输入提现金额");
                    AnimatorUtils.onVibrationView(etMoney);
                    return;
                }
                if(RxDataUtils.stringToInt(money)>RxDataUtils.stringToInt(allMoney)){
                    RxToast.error("可用余额不足");
                    AnimatorUtils.onVibrationView(etMoney);
                    return;
                }
                if(dialog==null){
                    dialog = new RxDialogSureCancel(_activity);
                    dialog.getTvTitle().setVisibility(View.GONE);
                    dialog.setContent("确认提现？");
                    dialog.setCancelListener(onClickListener);
                    dialog.setSureListener(onClickListener);
                }
                dialog.show();
                break;
        }
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.tv_sure:
                    AppUtil.getApiClient(ac).balanceWithdrawals(_activity,cardInfo.getB_id(),money,callback);
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
                RxToast.success("申请提现成功！");
                Intent intent = new Intent("android.intent.action.PersonalInfoRefreshBroadCast");
                sendBroadcast(intent);
                finish();
            }else{
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogUtils.e("data:"+data);
        if (RESULT_OK == resultCode) {
            cardInfo = (MyBankCardListBean.DataBean) data.getSerializableExtra("data");
            tvBankName.setText(cardInfo.getB_bank_name());
            String b_number = cardInfo.getB_number();
            tvCardNum.setText("尾号"+ b_number.substring(b_number.length()-4,b_number.length())+"储蓄卡");
        }
    }
}
