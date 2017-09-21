package com.lida.cloud.fragment;

import com.lida.cloud.data.FragmentGoodCommentDataSource;
import com.lida.cloud.tpl.FragmentGoodCommentTpl;
import com.midian.base.base.BaseListFragment;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;
import java.util.ArrayList;

/**
 * Created by WeiQingFeng on 2017/8/25.
 */

public class FramengGoodComment extends BaseListFragment {

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        String id = getArguments().getString("id");
        return new FragmentGoodCommentDataSource(_activity,id);
    }

    @Override
    protected Class getTemplateClass() {
        return FragmentGoodCommentTpl.class;
    }
}
