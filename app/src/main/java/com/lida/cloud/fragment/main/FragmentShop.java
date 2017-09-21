package com.lida.cloud.fragment.main;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.lida.cloud.R;
import com.lida.cloud.activity.ActivitySearch;
import com.lida.cloud.adapter.AdapterShopViewPager;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.BusinessCategoryBean;
import com.lida.cloud.fragment.FragmentShopList;
import com.midian.base.api.ApiCallback;
import com.midian.base.base.BaseFragment;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.vondear.rxtools.view.RxToast;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 实体店
 * Created by Administrator on 2017/8/8.
 */

public class FragmentShop extends BaseFragment implements ApiCallback {

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    Unbinder unbinder;
    @BindView(R.id.magic_indicator1)
    MagicIndicator magicIndicator;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

//    private static final String[] CHANNELS = new String[]{"生活服务", "餐饮美食", "服装鞋帽",
//            "美容保健", "办公家居", "数码家电", "户外运动", "汽车用品","家装建材","房产地产",
//            "旅游酒店","更多服务"};
//    private static final int[] CHANNELSIMG = new int[]{R.drawable.icon_shop_class1,R.drawable.icon_shop_class2,
//            R.drawable.icon_shop_class3,R.drawable.icon_shop_class4,R.drawable.icon_shop_class5,R.drawable.icon_shop_class6,
//            R.drawable.icon_shop_class7,R.drawable.icon_shop_class8,R.drawable.icon_shop_class9,R.drawable.icon_shop_class10,
//            R.drawable.icon_shop_class11,R.drawable.icon_shop_class12};
    private List<BusinessCategoryBean.DataBean> typeData = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = LayoutInflater.from(_activity).inflate(R.layout.fragment_shop, null);
        unbinder = ButterKnife.bind(this, v);
        topbar.setTitle("实体店");
        topbar.setRightImageButton(R.drawable.icon_search, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.jump(_activity, ActivitySearch.class);
            }
        });
        AppUtil.getApiClient(ac).businessCategory(this);
//        for (int i = 0; i < CHANNELS.length; i++) {
//            fragments.add(new FragmentShopList());
//        }
//        viewPager.setOffscreenPageLimit(2);
//        viewPager.setAdapter(new AdapterShopViewPager(getFragmentManager(),fragments));
//        initMagicIndicator();
        return v;
    }

    private void initMagicIndicator() {
        magicIndicator.setBackgroundColor(Color.WHITE);
        CommonNavigator commonNavigator = new CommonNavigator(_activity);
        commonNavigator.setSkimOver(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return typeData.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                CommonPagerTitleView commonPagerTitleView = new CommonPagerTitleView(context);
                View customLayout = LayoutInflater.from(context).inflate(R.layout.simple_pager_title_layout, null);
                final ImageView titleImg = (ImageView) customLayout.findViewById(R.id.title_img);
                final TextView titleText = (TextView) customLayout.findViewById(R.id.title_text);
                if(typeData.get(index).getLogo()!=null){
                    ac.setImage(titleImg,typeData.get(index).getLogo());
                }
                titleText.setText(typeData.get(index).getName());
                commonPagerTitleView.setContentView(customLayout);
                commonPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewPager.setCurrentItem(index);
                    }
                });
                return commonPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator linePagerIndicator = new LinePagerIndicator(context);
                linePagerIndicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                linePagerIndicator.setColors(Color.parseColor("#fe6559"));
                return linePagerIndicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, viewPager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
        if(res.isOK()){
            if("businessCategory".equals(tag)){
                BusinessCategoryBean bean = (BusinessCategoryBean) res;
                typeData.clear();
                typeData.addAll(bean.getData());
                for (int i = 0; i < typeData.size(); i++) {
                    FragmentShopList fragmentShopList = new FragmentShopList();
                    Bundle bundle = new Bundle();
                    bundle.putString("cid",typeData.get(i).getId()+"");
                    fragmentShopList.setArguments(bundle);
                    fragments.add(fragmentShopList);
                }
                viewPager.setAdapter(new AdapterShopViewPager(getFragmentManager(),fragments));
                initMagicIndicator();
            }
        }
    }

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
        _activity.hideLoadingDlg();
        RxToast.error(_activity, "网络异常").show();
    }

    @Override
    public void onParseError(String tag) {
        _activity.hideLoadingDlg();
        RxToast.error(_activity, "数据解析异常").show();
    }
}
