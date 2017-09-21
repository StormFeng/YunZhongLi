package com.lida.cloud.activity;

import android.os.Bundle;
import android.view.View;

import com.apkfuns.logutils.LogUtils;
import com.lida.cloud.R;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.ActivityAgentCenterRevenueBean;
import com.lida.cloud.bean.ActivityPublicWelfareBannerBean;
import com.lida.cloud.data.ActivityPublicWelfareData;
import com.lida.cloud.tpl.ActivityPublicWelfareTpl;
import com.lida.cloud.widght.BaseApiCallback;
import com.midian.base.base.BaseActivity;
import com.midian.base.base.BaseListActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.Banner;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 粉丝公益
 * Created by Administrator on 2017/8/10.
 */

public class ActivityPublicWelfare extends BaseListActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.banner)
    Banner bannerView;


    private List<ActivityPublicWelfareBannerBean.DataBean> bannerBeanData = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_public_welfare);
        ButterKnife.bind(this);
        topbar.setTitle("粉丝公益");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        AppUtil.getApiClient(ac).welfarebanner(callback);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_public_welfare;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new ActivityPublicWelfareData(_activity);
    }

    @Override
    protected Class getTemplateClass() {
        return ActivityPublicWelfareTpl.class;
    }


    BaseApiCallback callback = new BaseApiCallback(){
        @Override
        public void onApiStart(String tag) {
            super.onApiStart(tag);
        }

        @Override
        public void onApiSuccess(NetResult res, String tag) {
            super.onApiSuccess(res, tag);
            if(tag.equals("welfarebanner")){
                ActivityPublicWelfareBannerBean bean = (ActivityPublicWelfareBannerBean)res;
                if(bean!=null&&bean.getData()!=null&&bean.getData().size()>0){
                    initBannerView(bean);
                }
            }
        }

        @Override
        public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
            super.onApiFailure(t, errorNo, strMsg, tag);
        }
    };

    private void initBannerView(ActivityPublicWelfareBannerBean bean) {
        bannerBeanData = bean.getData();
        List<String> images = new ArrayList<>(bannerBeanData.size());
        for (ActivityPublicWelfareBannerBean.DataBean tmp : bannerBeanData) {
            images.add(tmp.getPic());
        }
        LogUtils.e(images);
        bannerView.setBannerStyle(Banner.CIRCLE_INDICATOR);//设置圆形指示器
        bannerView.setIndicatorGravity(Banner.CENTER);
        bannerView.isAutoPlay(true);
        bannerView.setDelayTime(5000);//设置轮播间隔时间
        bannerView.setImages(images.toArray());
        bannerView.setOnBannerClickListener(new Banner.OnBannerClickListener() {
            @Override
            public void OnBannerClick(View view, int position) {
                ActivityPublicWelfareBannerBean.DataBean date = bannerBeanData.get(position-1);
                Bundle bundle = new Bundle();
                bundle.putString("url",date.getUrl());
                UIHelper.jump(_activity,ActivityPublicWelfareDetail.class,bundle);
            }
        });

    }
}
