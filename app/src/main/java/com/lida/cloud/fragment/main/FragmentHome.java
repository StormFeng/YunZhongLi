package com.lida.cloud.fragment.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lida.cloud.R;
import com.lida.cloud.activity.ActivityAboutUs;
import com.lida.cloud.activity.ActivityNewsList;
import com.lida.cloud.activity.ActivityProvince;
import com.lida.cloud.activity.ActivitySearch;
import com.lida.cloud.activity.ActivitySign;
import com.lida.cloud.adapter.AdapterGvHotGoods;
import com.lida.cloud.adapter.AdapterGvNewsGoods;
import com.lida.cloud.adapter.AdapterGvShopRec;
import com.lida.cloud.adapter.AdapterHomeIconTab;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.IndexBean;
import com.lida.cloud.widght.InnerGridView;
import com.lida.cloud.widght.MarqueeTextView;
import com.midian.base.api.ApiCallback;
import com.midian.base.base.BaseFragment;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 首页
 * Created by Administrator on 2017/8/8.
 */

public class FragmentHome extends BaseFragment implements ApiCallback {
    @BindView(R.id.banner)
    Banner banner;
    Unbinder unbinder;
    @BindView(R.id.gv_IconTab)
    InnerGridView gvIconTab;
    @BindView(R.id.marqueeTextView)
    MarqueeTextView marqueeTextView;
    @BindView(R.id.gvNewGoods)
    InnerGridView gvNewGoods;
    @BindView(R.id.gvShopRec)
    InnerGridView gvShopRec;
    @BindView(R.id.tvPosition)
    TextView tvPosition;
    @BindView(R.id.gvHotGoods)
    InnerGridView gvHotGoods;

    private List<IndexBean.DataBean.BannerBean> bannerData = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = LayoutInflater.from(_activity).inflate(R.layout.fragment_home, null);
        unbinder = ButterKnife.bind(this, v);
        initBanner();
        getData();
        tvPosition.setText(ac.city);
        return v;
    }

    private void initBanner() {
        banner.setBannerStyle(Banner.CIRCLE_INDICATOR);
        banner.setIndicatorGravity(Banner.CENTER);
        banner.isAutoPlay(true);
        banner.setDelayTime(5000);
        banner.setIndicatorGravity(Banner.RIGHT);
    }

    private void initMarqueeView(final List<IndexBean.DataBean.NewsBean> data) {
        List<View> views = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            View v = LayoutInflater.from(_activity).inflate(R.layout.item_marquee, null);
            TextView tvType = (TextView) v.findViewById(R.id.tvType);
            TextView tvContent = (TextView) v.findViewById(R.id.tvContent);
            tvType.setText("Hot");
            tvContent.setText(data.get(i).getNew_title());
            views.add(v);
        }
        marqueeTextView.setViews(views);
        marqueeTextView.setOnItemClickListener(new MarqueeTextView.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("url", data.get(position).getUrl());
                bundle.putString("title", "新闻资讯详情");
                UIHelper.jump(_activity, ActivityAboutUs.class, bundle);
            }
        });
    }

    private void getData() {
        AppUtil.getApiClient(ac).index(ac.city, this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tvNews, R.id.tvSign, R.id.tvPosition, R.id.tvSearch})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvNews:
                UIHelper.jump(_activity, ActivityNewsList.class);
                break;
            case R.id.tvSign:
                UIHelper.jump(_activity, ActivitySign.class);
                break;
            case R.id.tvPosition:
                Intent intent = new Intent(_activity, ActivityProvince.class);
                intent.putExtra("flag", "FragmentHome");
                this.startActivityForResult(intent, 5001);
                break;
            case R.id.tvSearch:
                UIHelper.jump(_activity, ActivitySearch.class);
                break;
        }
    }

    @Override
    public void onApiStart(String tag) {
        _activity.showLoadingDlg();
    }

    @Override
    public void onApiLoading(long count, long current, String tag) {
        _activity.showLoadingDlg();
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        _activity.hideLoadingDlg();
        if (res.isOK()) {
            IndexBean bean = (IndexBean) res;
            ArrayList imgs = new ArrayList();
            if (bean.getData().get(0).getBanner().size() > 0) {
                bannerData.addAll(bean.getData().get(0).getBanner());
                for (int i = 0; i < bean.getData().get(0).getBanner().size(); i++) {
                    imgs.add(bean.getData().get(0).getBanner().get(i).getBanner_image());
                }
            }
            banner.setImages(imgs.toArray());
            initMarqueeView(bean.getData().get(0).getNews());
            gvIconTab.setAdapter(new AdapterHomeIconTab(_activity, bean.getData().get(0).getCategory()));
            gvNewGoods.setAdapter(new AdapterGvNewsGoods(_activity, bean.getData().get(0).getGoods()));
            gvHotGoods.setAdapter(new AdapterGvHotGoods(_activity, bean.getData().get(0).getHot()));
            gvShopRec.setAdapter(new AdapterGvShopRec(_activity, bean.getData().get(0).getStore()));
        }
    }

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
        _activity.hideLoadingDlg();
        UIHelper.t(_activity, "网络异常！");
    }

    @Override
    public void onParseError(String tag) {
        UIHelper.t(_activity, "数据解析异常！");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Activity.RESULT_OK == resultCode) {
            String city = data.getStringExtra("city");
            tvPosition.setText(city);
            AppUtil.getApiClient(ac).index(city, this);
        }
    }
}
