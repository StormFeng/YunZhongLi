package com.lida.cloud.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.lida.cloud.R;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.AddressListBean;
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
 * 新增收货地址
 * Created by Administrator on 2017/8/15.
 */

public class ActivityAddAddress extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etPhone)
    EditText etPhone;
    @BindView(R.id.tvChooseArea)
    TextView tvChooseArea;
    @BindView(R.id.etAddress)
    EditText etAddress;
    @BindView(R.id.btnSave)
    Button btnSave;

    private String province;
    private String city;
    private String country;

    private AddressListBean.DataBean.ListBean data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addaddress);
        ButterKnife.bind(this);
        if (mBundle != null) {
            topbar.setTitle("修改收货地址");
            data = (AddressListBean.DataBean.ListBean) mBundle.getSerializable("data");
            etName.setText(data.getName());
            etPhone.setText(data.getMobile());
            province = data.getProvince();
            city = data.getCity();
            country = data.getCountry();
            tvChooseArea.setText(province + "  " + city + "  " + country);
            etAddress.setText(data.getDetail());
        } else {
            topbar.setTitle("新增收货地址");
        }
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_OK == resultCode) {
            LogUtils.e(data);
            province = data.getStringExtra("province");
            city = data.getStringExtra("city");
            country = data.getStringExtra("country");
            tvChooseArea.setText(province + "  " + city + "  " + country);
        }
    }

    @OnClick({R.id.tvChooseArea, R.id.btnSave})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvChooseArea:
                UIHelper.jumpForResult(_activity, ActivityProvince.class, 1001);
                break;
            case R.id.btnSave:
                String name = etName.getText().toString();
                String mobile = etPhone.getText().toString();
                String area = tvChooseArea.getText().toString();
                String address = etAddress.getText().toString();
                LogUtils.e("address:"+address);
                if ("".equals(name)) {
                    RxToast.error("请输入收货人姓名");
                    AnimatorUtils.onVibrationView(etName);
                    return;
                }
                if ("".equals(mobile)) {
                    RxToast.error("请输入收货人手机号码");
                    AnimatorUtils.onVibrationView(etPhone);
                    return;
                }
                if ("".equals(area)) {
                    RxToast.error("请选择收货地区");
                    AnimatorUtils.onVibrationView(etPhone);
                    return;
                }
                if ("".equals(address)) {
                    RxToast.error("请填写详细的收货地址");
                    AnimatorUtils.onVibrationView(etPhone);
                    return;
                }
                if (mBundle != null) {
                    AppUtil.getApiClient(ac).editAddress(name, mobile, province, city, country, address, data.getId(), callback);
                } else {
                    AppUtil.getApiClient(ac).addAddress(name, mobile, province, city, country, address, callback);
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
                RxToast.success("操作成功");
                setResult(RESULT_OK);
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
