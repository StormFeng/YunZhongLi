package com.lida.cloud.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.apkfuns.logutils.LogUtils;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.model.TResult;
import com.lida.cloud.R;
import com.lida.cloud.adapter.AdapterShopPic;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.widght.BaseTakePhotoActivity;
import com.lida.cloud.widght.InnerGridView;
import com.lida.cloud.widght.dialog.DialogAuthComplete;
import com.midian.base.api.ApiCallback;
import com.midian.base.app.AppContext;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.vondear.rxtools.RxDataUtils;
import com.vondear.rxtools.RxRegUtils;
import com.vondear.rxtools.view.RxToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 商家认证
 * Created by WeiQingFeng on 2017/9/4.
 */

public class ActivityShopAuth extends BaseTakePhotoActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etPhone)
    EditText etPhone;
    @BindView(R.id.etNumber)
    EditText etNumber;
    @BindView(R.id.etShopName)
    EditText etShopName;
    @BindView(R.id.gvPic)
    InnerGridView gvPic;
    @BindView(R.id.btnCommit)
    Button btnCommit;

    private List<String> pics = new ArrayList<>();
    private AdapterShopPic adapter;
    private AppContext ac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopauth);
        ButterKnife.bind(this);
        ac = (AppContext) getApplicationContext();
        topbar.setTitle("商户认证");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(this));
        pics.add("");
        adapter = new AdapterShopPic(this, pics);
        gvPic.setAdapter(adapter);
    }

    @OnClick(R.id.btnCommit)
    public void onViewClicked() {
        String name = etName.getText().toString();
        String phone = etPhone.getText().toString();
        String shopNumber = etNumber.getText().toString();
        String shopName = etShopName.getText().toString();
        if(RxDataUtils.isNullString(name)){
            RxToast.error("请输入商家负责人姓名");
            return;
        }
        if(RxDataUtils.isNullString(phone)){
            RxToast.error("请输入商家负责人手机号码");
            return;
        }
        if(!RxRegUtils.isMobileExact(phone)){
            RxToast.error("手机号码格式不正确");
            return;
        }
        if(RxDataUtils.isNullString(shopNumber)){
            RxToast.error("请输入营业执照注册号");
            return;
        }
        if(RxDataUtils.isNullString(shopName)){
            RxToast.error("请输入营业执照名称");
            return;
        }
        if(pics.size()==0||pics==null){
            RxToast.error("请上传营业执照");
            return;
        }
        AppUtil.getApiClient(ac).businessApply(this,name,phone,shopNumber,shopName,
                pics,callback);
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
                DialogAuthComplete dialog = new DialogAuthComplete(ActivityShopAuth.this);
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        finish();
                    }
                });
                dialog.show();
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
        if (requestCode == 1003 || requestCode == 1006) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        if (!"".equals(result.getImage().getCompressPath())) {
            pics.add(pics.size() - 1, result.getImage().getCompressPath());
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.notifyDataSetChanged();
                }
            });
        }
    }
}
