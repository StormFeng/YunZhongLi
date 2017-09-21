package com.lida.cloud.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.apkfuns.logutils.LogUtils;
import com.lida.cloud.R;
import com.lida.cloud.adapter.AdapterShopViewPager;
import com.lida.cloud.fragment.FragmentCollectionShop;
import com.lida.cloud.fragment.FragmentOrder;
import com.midian.base.base.BaseFragmentActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**我的订单
 * Created by WeiQingFeng on 2017/8/25.
 */

public class ActivityMyOrder extends BaseFragmentActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.magic_indicator)
    MagicIndicator magicIndicator;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private List<Fragment> fragments = new ArrayList<>();
    private static final String[] CHANNELS = new String[]{"全部","待付款",
            "待发货", "待收货", "待评价","售后"};
    private int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        ButterKnife.bind(this);
        if(mBundle!=null){
            position = mBundle.getInt("position");
        }
        topbar.setTitle("我的订单");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        Bundle bundle = new Bundle();
        bundle.putString("status","");
        FragmentOrder fragmentOrder = new FragmentOrder();
        fragmentOrder.setArguments(bundle);
        LogUtils.e(bundle);
        fragments.add(fragmentOrder);
        for (int i = 0; i < CHANNELS.length-1; i++) {
            Bundle bTemp = new Bundle();
            if(i==4){
                bTemp.putString("status",i+1+"");
            }else{
                bTemp.putString("status",i+"");
            }
            FragmentOrder fTemp = new FragmentOrder();
            fTemp.setArguments(bTemp);
            fragments.add(fTemp);
        }
        viewPager.setOffscreenPageLimit(1);
        viewPager.setAdapter(new AdapterShopViewPager(fm,fragments));
        initMagicIndicator();
        viewPager.setCurrentItem(position);
    }

    private void initMagicIndicator() {
        magicIndicator.setBackgroundColor(Color.WHITE);
        CommonNavigator commonNavigator = new CommonNavigator(_activity);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setSkimOver(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return CHANNELS.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new SimplePagerTitleView(context);
                simplePagerTitleView.setText(CHANNELS[index]);
                simplePagerTitleView.setTextSize(12);
                simplePagerTitleView.setNormalColor(Color.BLACK);
                simplePagerTitleView.setSelectedColor(Color.parseColor("#fe6559"));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewPager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator linePagerIndicator = new LinePagerIndicator(context);
                linePagerIndicator.setMode(LinePagerIndicator.MODE_MATCH_EDGE);
                linePagerIndicator.setColors(Color.parseColor("#fe6559"));
                return linePagerIndicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, viewPager);
    }
}
