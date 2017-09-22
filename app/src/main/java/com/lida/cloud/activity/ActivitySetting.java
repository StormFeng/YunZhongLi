package com.lida.cloud.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lida.cloud.R;
import com.midian.base.base.BaseActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.tencent.bugly.beta.Beta;
import com.vondear.rxtools.RxActivityUtils;
import com.vondear.rxtools.RxDeviceUtils;
import com.vondear.rxtools.RxFileUtils;
import com.vondear.rxtools.view.dialog.RxDialogSureCancel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 设置
 * Created by Administrator on 2017/8/9.
 */

public class ActivitySetting extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.tvVersion)
    TextView tvVersion;
    @BindView(R.id.tvCache)
    TextView tvCache;
    @BindView(R.id.llCheckUpdate)
    LinearLayout llCheckUpdate;

    private RxDialogSureCancel dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        topbar.setTitle("设置");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        tvCache.setText(RxFileUtils.getFileSize(RxFileUtils.getDiskCacheDir(_activity)));
        tvVersion.setText(RxDeviceUtils.getAppVersionName(_activity));
    }

    @OnClick({R.id.llClearCache, R.id.llAboutUs, R.id.btnOut, R.id.llCheckUpdate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llClearCache:
                if (RxFileUtils.cleanCustomCache(RxFileUtils.getDiskCacheDir(_activity))) {
                    UIHelper.t(_activity, "删除缓存成功!");
                    tvCache.setText("0KB");
                }
                break;
            case R.id.llAboutUs:
                UIHelper.jump(_activity, ActivityAboutUs.class);
                break;
            case R.id.btnOut:
                if (dialog == null) {
                    dialog = new RxDialogSureCancel(_activity);
                    dialog.getTvTitle().setVisibility(View.GONE);
                    dialog.setContent("退出登录？");
                    dialog.setSureListener(onClickListener);
                    dialog.setCancelListener(onClickListener);
                }
                dialog.show();
                break;
            case R.id.llCheckUpdate:
                Beta.checkUpgrade();
                break;
        }
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_sure:
                    ac.clearUserInfo();
                    RxActivityUtils.skipActivityAndFinishAll(_activity, ActivityLoginAct.class);
                    break;
                case R.id.tv_cancel:
                    dialog.dismiss();
                    break;
            }
        }
    };
}
