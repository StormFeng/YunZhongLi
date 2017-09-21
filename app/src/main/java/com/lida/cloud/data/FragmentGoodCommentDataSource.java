package com.lida.cloud.data;

import android.content.Context;

import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.GoodCommentBean;
import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;

/**
 * 评论
 * Created by WeiQingFeng on 2017/8/25.
 */

public class FragmentGoodCommentDataSource extends BaseListDataSource {

    private String id;

    public FragmentGoodCommentDataSource(Context context, String id) {
        super(context);
        this.id = id;
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList models = new ArrayList();
        GoodCommentBean bean = AppUtil.getApiClient(ac).goodComment(id);
        models.addAll(bean.getData().get(0).getList());
        hasMore = false;
        return models;
    }
}
