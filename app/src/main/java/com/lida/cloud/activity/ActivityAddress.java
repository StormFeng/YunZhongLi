package com.lida.cloud.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import com.lida.cloud.R;
import com.lida.cloud.adapter.AdapterActivityAddress;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.AddressListBean;
import com.midian.base.api.ApiCallback;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.vondear.rxtools.view.RxToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 收货地址
 * Created by Administrator on 2017/8/10.
 */

public class ActivityAddress extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.btnAddNewAdd)
    Button btnAddNewAdd;

    public String flag = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adress);
        ButterKnife.bind(this);
        if(mBundle!=null){
            flag = mBundle.getString("flag");
        }
        initView();
        getData();
    }

    public void getData() {
        AppUtil.getApiClient(ac).getAddressList(callback);
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
                AddressListBean bean = (AddressListBean) res;
                listView.setAdapter(new AdapterActivityAddress(ActivityAddress.this,bean.getData().get(0).getList()));
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

    private void initView() {
        topbar.setTitle("收货地址");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
    }

    @OnClick(R.id.btnAddNewAdd)
    public void onViewClicked() {
        UIHelper.jumpForResult(_activity,ActivityAddAddress.class,1001);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(RESULT_OK == resultCode){
            getData();
        }
    }
}
