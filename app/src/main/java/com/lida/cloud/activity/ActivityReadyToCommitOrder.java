package com.lida.cloud.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.lida.cloud.ActivityPaySuccess;
import com.lida.cloud.R;
import com.lida.cloud.adapter.AdapterActivityReadyTocommitOrder;
import com.lida.cloud.adapter.AdapterActivityReadyTocommitOrderCopy;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.ActivityReadyToCommitOrderBean;
import com.lida.cloud.bean.ActivityReadyToCommitOrderBeanCopy;
import com.lida.cloud.bean.AddressListBean;
import com.lida.cloud.bean.OrderCreateBean;
import com.midian.base.api.ApiCallback;
import com.midian.base.app.AppManager;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.ListViewForScrollView;
import com.vondear.rxtools.RxActivityUtils;
import com.vondear.rxtools.RxDataUtils;
import com.vondear.rxtools.RxTextUtils;
import com.vondear.rxtools.view.RxToast;
import com.vondear.rxtools.view.dialog.RxDialogSureCancel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 结算-提交订单
 * Created by WeiQingFeng on 2017/9/6.
 */

public class ActivityReadyToCommitOrder extends BaseActivity {

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.tvChooseAddress)
    TextView tvChooseAddress;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvPhone)
    TextView tvPhone;
    @BindView(R.id.tvAddress)
    TextView tvAddress;
    @BindView(R.id.llAddress)
    LinearLayout llAddress;
    @BindView(R.id.lvGoods)
    ListViewForScrollView lvGoods;
    @BindView(R.id.tvCount)
    TextView tvCount;
    @BindView(R.id.tvPrice)
    TextView tvPrice;
    @BindView(R.id.btnCommit)
    Button btnCommit;

    private AddressListBean.DataBean.ListBean addressBean;
    private RxDialogSureCancel dialogSureCancel;

    private String goodsid;
    private String total;
    private String specid;

    private List<String> remark = new ArrayList<>();
    private ActivityReadyToCommitOrderBean bean;
    private ActivityReadyToCommitOrderBeanCopy beanCopy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_readytocommitorder);
        ButterKnife.bind(this);
        initView();
        if(mBundle==null){
            AppUtil.getApiClient(ac).orderConfirm(_activity, callback);
        }else{
            goodsid = mBundle.getString("goodsid");
            total = mBundle.getString("total");
            specid = mBundle.getString("specid");
            AppUtil.getApiClient(ac).orderConfirmCopy(_activity,goodsid,total,specid,callback);
        }
    }

    private void initView() {
        topbar.setTitle("订单详情");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));

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
                if("orderConfirm".equals(tag)){
                    bean = (ActivityReadyToCommitOrderBean) res;
                    ActivityReadyToCommitOrderBean.DataBean data = bean.getData().get(0);
                    lvGoods.setAdapter(new AdapterActivityReadyTocommitOrder(_activity, data.getList()));
                    RxTextUtils.getBuilder("共").setForegroundColor(Color.parseColor("#404040"))
                            .append(data.getTotal()).setForegroundColor(Color.parseColor("#FF3600"))
                            .append("件商品").setForegroundColor(Color.parseColor("#404040"))
                            .into(tvCount);
                    RxTextUtils.getBuilder("合计:").setForegroundColor(Color.parseColor("#404040"))
                            .append("￥"+ data.getPrice()).setForegroundColor(Color.parseColor("#FF3600"))
                            .into(tvPrice);
                }
                if("orderConfirmCopy".equals(tag)){
                    beanCopy = (ActivityReadyToCommitOrderBeanCopy) res;
                    ActivityReadyToCommitOrderBeanCopy.DataBean data = beanCopy.getData().get(0);
                    lvGoods.setAdapter(new AdapterActivityReadyTocommitOrderCopy(_activity, data.getList()));
                    RxTextUtils.getBuilder("共").setForegroundColor(Color.parseColor("#404040"))
                            .append(data.getTotal()).setForegroundColor(Color.parseColor("#FF3600"))
                            .append("件商品").setForegroundColor(Color.parseColor("#404040"))
                            .into(tvCount);
                    RxTextUtils.getBuilder("合计:").setForegroundColor(Color.parseColor("#404040"))
                            .append("￥"+ data.getPrice()).setForegroundColor(Color.parseColor("#FF3600"))
                            .into(tvPrice);
                }
                if("orderCreate".equals(tag)){
                    Intent intent = new Intent("android.intent.action.RefreshShopCar");
                    sendBroadcast(intent);
                    final OrderCreateBean bean = (OrderCreateBean) res;
                    dialogSureCancel = new RxDialogSureCancel(_activity);
                    dialogSureCancel.getTvTitle().setVisibility(View.GONE);
                    dialogSureCancel.setContent("订单提交成功，现在付款？");
                    dialogSureCancel.setSureListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AppUtil.getApiClient(ac).orderPay(_activity,bean.getData().get(0).getOrderid(),callback);
                        }
                    });
                    dialogSureCancel.setCancelListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialogSureCancel.dismiss();
                        }
                    });
                    dialogSureCancel.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            UIHelper.jump(_activity,ActivityMyOrder.class);
                            finish();
                        }
                    });
                    dialogSureCancel.setCanceledOnTouchOutside(false);
                    dialogSureCancel.show();
                }
                if("orderPay".equals(tag)){
                    dialogSureCancel.dismiss();
                    UIHelper.jumpForResult(_activity, ActivityPaySuccess.class,1002);
                }
            } else {
                RxToast.error(res.getMessage());
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

    @OnClick({R.id.tvChooseAddress, R.id.btnCommit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvChooseAddress:
                Bundle bundle = new Bundle();
                bundle.putString("flag","ActivityReadyToCommitOrder");
                UIHelper.jumpForResult(_activity,ActivityAddress.class,bundle,1001);
                break;
            case R.id.btnCommit:
                if(addressBean==null){
                    RxToast.error("请选择收货地址");
                    return;
                }
                if(mBundle==null){
                    for (int i = 0; i < bean.getData().get(0).getList().size(); i++) {
                        remark.add(bean.getData().get(0).getList().get(i).getRemark());
                    }
                    AppUtil.getApiClient(ac).orderCreate(_activity,remark,addressBean.getId(),callback);
                }else{
                    for (int i = 0; i < beanCopy.getData().get(0).getList().size(); i++) {
                        remark.add(beanCopy.getData().get(0).getList().get(i).getRemark());
                    }
                    AppUtil.getApiClient(ac).orderCreate(_activity,goodsid,total,specid,addressBean.getId(),remark,callback);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogUtils.e(data);
        if(RESULT_OK==resultCode){
            switch (requestCode){
                case 1001:
                    addressBean = (AddressListBean.DataBean.ListBean) data.getExtras().getSerializable("data");
                    llAddress.setVisibility(View.VISIBLE);
                    tvName.setText(addressBean.getName());
                    tvPhone.setText(RxDataUtils.hideMobilePhone4(addressBean.getMobile()));
                    tvAddress.setText(addressBean.getDetail());
                    break;
                case 1002:
                    finish();
                    break;
            }

        }
    }
}
