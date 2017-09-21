package com.lida.cloud.fragment;

import com.lida.cloud.R;
import com.lida.cloud.data.FragmentRecommendMerchantsData;
import com.lida.cloud.tpl.FragmentMyRecommendTpl;
import com.midian.base.base.BaseListFragment;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;

import java.util.ArrayList;

/**
 * 我的推荐-推荐的商户
 * Created by xkr on 2017/9/7.
 */

public class FragmentRecommendedMerchants extends BaseListFragment{


    @Override
    protected int getLayoutId() {
        return R.layout.layout_pulltorefresh;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new FragmentRecommendMerchantsData(_activity);
    }

    @Override
    protected Class getTemplateClass() {
        return FragmentMyRecommendTpl.class;
    }
}
