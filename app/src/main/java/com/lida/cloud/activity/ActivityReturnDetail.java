package com.lida.cloud.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lida.cloud.R;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.ExpressBean;
import com.lida.cloud.bean.RefundBean;
import com.lida.cloud.bean.RefundingBean;
import com.lida.cloud.widght.dialog.DialogSelectExpress;
import com.midian.base.api.ApiCallback;
import com.midian.base.app.AppManager;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.vondear.rxtools.RxActivityUtils;
import com.vondear.rxtools.RxDataUtils;
import com.vondear.rxtools.view.RxToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.R.attr.data;

/**
 * 退货详情
 * Created by WeiQingFeng on 2017/9/19.
 */

public class ActivityReturnDetail extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.tvType)
    TextView tvType;
    @BindView(R.id.tvDes)
    TextView tvDes;
    @BindView(R.id.tvStatus)
    TextView tvStatus;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvPhone)
    TextView tvPhone;
    @BindView(R.id.tvAddress)
    TextView tvAddress;
    @BindView(R.id.tvLog)
    TextView tvLog;
    @BindView(R.id.etLogNum)
    EditText etLogNum;
    @BindView(R.id.btnCommit)
    Button btnCommit;
    @BindView(R.id.llTemp)
    LinearLayout llTemp;

    private DialogSelectExpress dialogSelectExpress;
    private String order_id;
    private String refundid;
    private String express_sn;
    private String express_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_returndetail);
        ButterKnife.bind(this);
        order_id = mBundle.getString("id");
        refundid = mBundle.getString("refundid");
        topbar.setTitle("退货详情");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        AppUtil.getApiClient(ac).orderRefunding(refundid, callback);
        AppUtil.getApiClient(ac).orderRefundExpress(callback);
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
                if ("orderRefunding".equals(tag)) {
                    RefundingBean bean = (RefundingBean) res;
                    RefundingBean.DataBean data = bean.getData().get(0);
                    if("1".equals(data.getRefund().getType())){
                        llTemp.setVisibility(View.VISIBLE);
                        tvAddress.setText(data.getAddress().getAddress());
                        tvName.setText(data.getAddress().getName());
                        tvPhone.setText(data.getAddress().getMobile());
                        tvType.setText("退货退款");
                    }else{
                        llTemp.setVisibility(View.GONE);
                        tvType.setText("退款");
                    }
                    tvDes.setText(data.getRefund().getContent());
                    if("0".equals(data.getRefund().getStatus())){
                        tvStatus.setText("审核中");
                        btnCommit.setEnabled(false);
                        btnCommit.setText("后台审核中");
                    }else if("1".equals(data.getRefund().getStatus())){
                        tvStatus.setText("退货中");
                        btnCommit.setEnabled(true);
                        btnCommit.setText("提交快递信息");
                    }else if("2".equals(data.getRefund().getStatus())){
                        tvStatus.setText("退货/退款完成");
                        btnCommit.setEnabled(false);
                        btnCommit.setText("退货/退款完成");
                    }
                }
                if ("orderRefundExpress".equals(tag)) {
                    ExpressBean bean = (ExpressBean) res;
                    dialogSelectExpress = new DialogSelectExpress(_activity, tvLog, bean.getData());
                }
                if ("orderRefundInfo".equals(tag)) {
                    RxToast.success("提交成功");
                    finish();
                }
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

    @OnClick({R.id.tvLog, R.id.btnCommit})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.tvLog:
                dialogSelectExpress.show();
                break;
            case R.id.btnCommit:
                express_sn = etLogNum.getText().toString();
                if (RxDataUtils.isNullString(express_sn)) {
                    RxToast.error("请选择快递公司");
                    return;
                }
                express_code = tvLog.getTag().toString();
                AppUtil.getApiClient(ac).orderRefundInfo(order_id, express_sn, express_code, callback);
                break;
        }
    }
}


















