package com.lida.cloud.data;

import android.content.Context;

import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.NewsListBean;
import com.midian.base.base.BaseListDataSource;

import java.util.ArrayList;

/**
 * 新闻资讯
 * Created by WeiQingFeng on 2017/9/4.
 */

public class ActivityNewsListDataSource extends BaseListDataSource {

    public ActivityNewsListDataSource(Context context) {
        super(context);
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList models = new ArrayList();
        NewsListBean bean = AppUtil.getApiClient(ac).newsList();
        models.addAll(bean.getData().get(0).getList());
        hasMore = false;
        return models;
    }
}
