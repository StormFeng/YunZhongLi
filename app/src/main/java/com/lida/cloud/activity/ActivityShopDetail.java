package com.lida.cloud.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.lida.cloud.R;
import com.lida.cloud.adapter.AdapterActivityShopDetail;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.ActivityShopDetailBean;
import com.lida.cloud.bean.CollectBean;
import com.lida.cloud.bean.ShopGoodBean;
import com.lida.cloud.widght.InnerGridView;
import com.midian.base.api.ApiCallback;
import com.midian.base.app.AppManager;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.Banner;
import com.midian.base.widget.BaseLibTopbarView;
import com.vondear.rxtools.RxActivityUtils;
import com.vondear.rxtools.view.RxToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 店铺详情
 * Created by WeiQingFeng on 2017/8/25.
 */

public class ActivityShopDetail extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.rbShopDetail)
    RadioButton rbShopDetail;
    @BindView(R.id.rbShopGoods)
    RadioButton rbShopGoods;
    @BindView(R.id.rGroup)
    RadioGroup rGroup;
    @BindView(R.id.tvDes)
    TextView tvDes;
    @BindView(R.id.tvPosition)
    TextView tvPosition;
    @BindView(R.id.tvTel)
    TextView tvTel;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tvCommend)
    TextView tvCommend;
    @BindView(R.id.tvCommendCount)
    TextView tvCommendCount;
    @BindView(R.id.tvReport)
    TextView tvReport;
    @BindView(R.id.llShopDetail)
    LinearLayout llShopDetail;
    @BindView(R.id.gvGood)
    InnerGridView gvGood;
    @BindView(R.id.cbCollection)
    CheckBox cbCollection;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvType)
    TextView tvType;
    @BindView(R.id.tvSelBack)
    TextView tvSelBack;
    @BindView(R.id.btnBuyOnLine)
    Button btnBuyOnLine;

    private String selid;
    private ShopGoodBean shopGoodBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopdetail);
        ButterKnife.bind(this);
        initView();
        initBanner();
        selid = mBundle.getString("selid");
        AppUtil.getApiClient(ac).businessDetail(selid, ac.memid, callback);
    }

    private void initBanner() {
        banner.setBannerStyle(Banner.CIRCLE_INDICATOR);
        banner.setIndicatorGravity(Banner.CENTER);
        banner.isAutoPlay(true);
        banner.setDelayTime(5000);
        banner.setIndicatorGravity(Banner.RIGHT);
    }

    private void initView() {
        topbar.setTitle("商铺详情");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        rGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rbShopDetail:
                        llShopDetail.setVisibility(View.VISIBLE);
                        gvGood.setVisibility(View.GONE);
                        break;
                    case R.id.rbShopGoods:
                        if (shopGoodBean == null) {
                            AppUtil.getApiClient(ac).businessStoregoods(selid, callback);
                        }
                        llShopDetail.setVisibility(View.GONE);
                        gvGood.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });
        cbCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppUtil.getApiClient(ac).collectShop(_activity, selid, callback);
            }
        });
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
                if ("businessDetail".equals(tag)) {
                    ActivityShopDetailBean bean = (ActivityShopDetailBean) res;
                    ActivityShopDetailBean.DataBean data = bean.getData().get(0);
                    if (data.getStore().getSelimage() != null) {
                        banner.setImages(data.getStore().getSelimage().toArray());
                    }
                    tvName.setText(data.getStore().getSelshopname());
                    tvType.setText(data.getStore().getTypename());
                    tvSelBack.setText(data.getStore().getSelback() + "%");
                    tvDes.setText(data.getStore().getSeldetail());
                    tvPosition.setText(data.getStore().getSelshopadd());
                    tvTime.setText(data.getStore().getSelshoptime());
                    if ("0".equals(data.getCollect())) {
                        cbCollection.setChecked(false);
                    } else {
                        cbCollection.setChecked(true);
                    }
                }
                if ("businessStoregoods".equals(tag)) {
                    shopGoodBean = (ShopGoodBean) res;
                    if (shopGoodBean.getData().size() > 0) {
                        gvGood.setAdapter(new AdapterActivityShopDetail(_activity, shopGoodBean.getData().get(0).getGoods()));
                    }
                }
                if ("collectShop".equals(tag)) {
                    RxToast.success("操作成功");
                    CollectBean bean = (CollectBean) res;
                    if (bean.getData().get(0).getState() == 0) {
                        cbCollection.setChecked(false);
                    } else {
                        cbCollection.setChecked(true);
                    }
                    Intent intent = new Intent("android.intent.action.RefreshCollectShop");
                    sendBroadcast(intent);
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

    @OnClick({R.id.btnBuyOnLine,R.id.tvReport})
    public void onViewClicked(View v) {
        Bundle bundle = new Bundle();
        bundle.putString("selid",selid);
        switch (v.getId()){
            case R.id.btnBuyOnLine:
                UIHelper.jump(_activity,ActivityPayNow.class,bundle);
                break;
            case R.id.tvReport:
                UIHelper.jump(_activity,ActivityReport.class,bundle);
                break;
        }
    }
}
