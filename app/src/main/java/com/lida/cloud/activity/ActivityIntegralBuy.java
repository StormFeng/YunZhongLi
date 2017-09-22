package com.lida.cloud.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.lida.cloud.R;
import com.lida.cloud.alipay.AlipayUtils;
import com.lida.cloud.app.AesEncryptionUtil;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.CreditRatioBean;
import com.lida.cloud.bean.PayBean;
import com.lida.cloud.bean.SignBean;
import com.midian.base.api.ApiCallback;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.vondear.rxtools.RxDataUtils;
import com.vondear.rxtools.view.RxToast;
import com.vondear.rxtools.view.dialog.RxDialogSureCancel;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 购买积分
 * Created by WeiQingFeng on 2017/8/31.
 */

public class ActivityIntegralBuy extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.tagIntegral)
    TagFlowLayout tagIntegral;
    @BindView(R.id.btnConfirm)
    Button btnConfirm;
    @BindView(R.id.etCount)
    EditText etCount;
    @BindView(R.id.llCount)
    LinearLayout llCount;
    @BindView(R.id.tvNotice)
    TextView tvNotice;
    @BindView(R.id.tvPrice)
    TextView tvPrice;

    private PriceBean bean;
    private double money;
    private double per;
    private List<PriceBean> items = new ArrayList<>();
    private RxDialogSureCancel dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integralbuy);
        ButterKnife.bind(this);
        topbar.setTitle("购买积分");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        AppUtil.getApiClient(ac).creditRatio(callback);
    }

    @OnClick(R.id.btnConfirm)
    public void onViewClicked() {
        Set<Integer> selectedList = tagIntegral.getSelectedList();
        Iterator<Integer> iterator = selectedList.iterator();
        int position = 0;
        if (selectedList.size() == 0) {
            RxToast.error("请选择积分数量");
            return;
        }
        while (iterator.hasNext()) {
            position = iterator.next();
        }
        if (position == items.size() - 1) {
            money = RxDataUtils.stringToInt(etCount.getText().toString()) * 100;
        } else {
            money = items.get(position).getPrice();
        }
        if (dialog == null) {
            dialog = new RxDialogSureCancel(_activity);
            dialog.getTvTitle().setVisibility(View.GONE);
            dialog.getTvContent().setText("确认支付？");
            dialog.setSureListener(onClickListener);
            dialog.setCancelListener(onClickListener);
        }
        dialog.show();
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_cancel:
                    dialog.dismiss();
                    break;
                case R.id.tv_sure:
                    AppUtil.getApiClient(ac).businessCredit(String.valueOf(money), callback);
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
            if (res.isOK()) {
                if ("creditRatio".equals(tag)) {
                    CreditRatioBean data = (CreditRatioBean) res;
                    per = RxDataUtils.stringToDouble(data.getData().get(0).getRatio())/100;
                    initData(per);
                }
                if ("businessCredit".equals(tag)) {
                    SignBean bean = (SignBean) res;
                    String sign = AesEncryptionUtil.decrypt(bean.getData().get(0).getAesData());
                    JSONObject jsonObject;
                    String pay_sn = null;
                    try {
                        jsonObject = new JSONObject(sign);
                        pay_sn = jsonObject.getString("pay_sn");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    AppUtil.getApiClient(ac).payAlipay(pay_sn, callback);
                }
                if ("payAlipay".equals(tag)) {
                    PayBean bean = (PayBean) res;
                    String orderInfo = bean.getData().get(0).getOrderString();
                    LogUtils.e(orderInfo);
                    new AlipayUtils().builder(_activity, orderInfo).pay();
                }
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

    public void initData(double v) {
        items.clear();
        for (int i = 0; i < 5; i++) {
            switch (i) {
                case 0:
                    bean = new PriceBean(100 * v, 100);
                    break;
                case 1:
                    bean = new PriceBean(500 * v, 500);
                    break;
                case 2:
                    bean = new PriceBean(1000 * v, 1000);
                    break;
                case 3:
                    bean = new PriceBean(5000 * v, 5000);
                    break;
                case 4:
                    bean = new PriceBean();
                    break;
            }
            items.add(bean);
        }
        tagIntegral.setAdapter(new TagAdapter<PriceBean>(items) {
            @Override
            public View getView(FlowLayout parent, int position, PriceBean bean) {
                View view = LayoutInflater.from(_activity).inflate(R.layout.item_integralbuy, null);
                TextView tvCount = (TextView) view.findViewById(R.id.tvCount);
                TextView tvPrice = (TextView) view.findViewById(R.id.tvPrice);
                if (bean.getCount() == 0) {
                    tvPrice.setText("其他金额(元)");
                    tvCount.setText("*请填写100的倍数");
                } else {
                    tvPrice.setText(RxDataUtils.getAmountValue(bean.getPrice()) + "元");
                    tvCount.setText("可获得积分：" + bean.getCount());
                }
                return view;
            }
        });
        tagIntegral.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                if (selectPosSet.contains(items.size() - 1)) {
                    llCount.setVisibility(View.VISIBLE);
                } else {
                    llCount.setVisibility(View.GONE);
                }
            }
        });
        etCount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (RxDataUtils.isNullString(s.toString())) {
                    tvNotice.setVisibility(View.GONE);
                    tvPrice.setVisibility(View.GONE);
                    return;
                }
                int i = RxDataUtils.stringToInt(s.toString());
                double v1 = i * 100 * per;
                tvNotice.setVisibility(View.VISIBLE);
                tvPrice.setVisibility(View.VISIBLE);
                tvPrice.setText("已输入金额：" + i * 100);
                tvNotice.setText("可获得积分：" + v1);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    class PriceBean {
        private double count;
        private double price;

        public PriceBean() {
        }

        public PriceBean(double count, double price) {
            this.count = count;
            this.price = price;
        }

        public double getCount() {
            return count;
        }

        public void setCount(double count) {
            this.count = count;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }
}
