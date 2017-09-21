package com.lida.cloud.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import com.lida.cloud.R;
import com.lida.cloud.adapter.AdapterShopCenterIconTab;
import com.lida.cloud.widght.InnerGridView;
import com.makeramen.roundedimageview.RoundedImageView;
import com.midian.base.base.BaseActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.umeng.socialize.UMShareAPI;
import com.vondear.rxtools.RxPermissionTool;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 商户中心
 * Created by WeiQingFeng on 2017/8/29.
 */

public class ActivityShopCenter extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.ivLogo)
    RoundedImageView ivLogo;
    @BindView(R.id.tvShopName)
    TextView tvShopName;
    @BindView(R.id.gvIconTabs)
    InnerGridView gvIconTabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopcenter);
        ButterKnife.bind(this);
        topbar.setTitle("商家中心");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        gvIconTabs.setAdapter(new AdapterShopCenterIconTab(_activity));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                RxPermissionTool.with(_activity)
                        .addPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .addPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                        .addPermission(Manifest.permission.CALL_PHONE)
                        .addPermission(Manifest.permission.READ_LOGS)
                        .addPermission(Manifest.permission.READ_PHONE_STATE)
                        .addPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .addPermission(Manifest.permission.SET_DEBUG_APP)
                        .addPermission(Manifest.permission.SYSTEM_ALERT_WINDOW)
                        .addPermission(Manifest.permission.GET_ACCOUNTS)
                        .addPermission(Manifest.permission.WRITE_APN_SETTINGS)
                        .initPermission();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
