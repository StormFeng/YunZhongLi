package com.lida.cloud.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lida.cloud.data.FragmentShopListDataSource;
import com.lida.cloud.tpl.FragmentShopListTpl;
import com.midian.base.base.BaseListFragment;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;

import java.util.ArrayList;

/**
 * 商家列表
 * Created by Administrator on 2017/8/8.
 */

public class FragmentShopList extends BaseListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        String cid = getArguments().getString("cid");
        return new FragmentShopListDataSource(_activity,cid,"");
    }

    @Override
    protected Class getTemplateClass() {
        return FragmentShopListTpl.class;
    }
}
