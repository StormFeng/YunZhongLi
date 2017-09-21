package com.lida.cloud.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lida.cloud.R;
import com.lida.cloud.adapter.AdapterActivityMyRecommend;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.fragment.FragmentRecommendedMembers;
import com.lida.cloud.fragment.FragmentRecommendedMerchants;
import com.midian.base.base.BaseActivity;
import com.midian.base.base.BaseFragmentActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.ListViewForScrollView;

import net.lucode.hackware.magicindicator.MagicIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的推荐
 * Created by WeiQingFeng on 2017/8/25.
 */

public class ActivityMyRecommend extends BaseFragmentActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
   /* @BindView(R.id.tvAllMoney)
    TextView tvAllMoney;*/
   /* @BindView(R.id.listView)
    ListViewForScrollView listView;*/
    @BindView(R.id.viewPager)
    ViewPager viewPager;
   /* @BindView(R.id.tvRecommendedMerchants)
    TextView tvRecommendedMerchants;*/
    @BindView(R.id.llRecommendedMerchants)
    LinearLayout llRecommendedMerchants;
  /*  @BindView(R.id.tvRecommendedMembers)
    TextView tvRecommendedMembers;*/
    @BindView(R.id.llRecommendedMembers)
    LinearLayout llRecommendedMembers;

    public static TextView tvRecommendedMerchants,tvRecommendedMembers,tvAllMoney;

    private List<Fragment> fragments = new ArrayList<>();

    public static String[] counts = new String[2];
    public static String[] moneys = new String[]{"0.0","0.0"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myrecommend);
        ButterKnife.bind(this);
        topbar.setTitle("我的推荐");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        tvRecommendedMembers = (TextView)findViewById(R.id.tvRecommendedMembers);
        tvRecommendedMerchants = (TextView)findViewById(R.id.tvRecommendedMerchants);
        tvAllMoney = (TextView)findViewById(R.id.tvAllMoney);
      //  listView.setAdapter(new AdapterActivityMyRecommend(_activity));
        fragments.add(new FragmentRecommendedMerchants());
        fragments.add(new FragmentRecommendedMembers());
        viewPager.setAdapter(new FragmentPagerAdapter(fm) {
            @Override
            public Fragment getItem(int position)
            {
                return fragments.get(position);
            }

            @Override
            public int getCount()
            {
                return fragments.size();
            }
        });
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    setButton(R.id.llRecommendedMerchants);
                    tvAllMoney.setText(moneys[0]);
                } else if (position == 1) {
                    setButton(R.id.llRecommendedMembers);
                    tvAllMoney.setText(moneys[1]);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @OnClick({R.id.llRecommendedMembers, R.id.llRecommendedMerchants})
    public void onViewClicked(View view)
    {
        setButton(view.getId());
        switch (view.getId())
        {
            case R.id.llRecommendedMerchants:
                viewPager.setCurrentItem(0);
                break;
            case R.id.llRecommendedMembers:
                viewPager.setCurrentItem(1);
                break;
        }
    }

    private void setButton(int id) {
        llRecommendedMembers.setBackgroundColor(Color.WHITE);
        llRecommendedMerchants.setBackgroundColor(Color.WHITE);
        LinearLayout view = (LinearLayout) findViewById(id);
        view.setBackgroundColor(Color.parseColor("#FFEEEE"));
    }

}
