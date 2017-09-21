package com.lida.cloud.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.lida.cloud.R;
import com.lida.cloud.data.FragmentShopListDataSource;
import com.lida.cloud.tpl.FragmentShopListTpl;
import com.midian.base.base.BaseListActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 搜索商铺
 * Created by WeiQingFeng on 2017/9/18.
 */

public class ActivitySearch extends BaseListActivity {

    @BindView(R.id.etSearch)
    EditText etSearch;
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;

    private String keyWord = "";
    private FragmentShopListDataSource fragmentShopListDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        topbar.setTitle("搜索");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                keyWord = s.toString();
                fragmentShopListDataSource.setKeyWord(s.toString());
                listViewHelper.refresh();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        fragmentShopListDataSource = new FragmentShopListDataSource(_activity,"",keyWord);
        return fragmentShopListDataSource;
    }

    @Override
    protected Class getTemplateClass() {
        return FragmentShopListTpl.class;
    }
}
