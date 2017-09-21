package com.lida.cloud.data;

import android.content.Context;

import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;

/**
 * 收藏的店铺
 * Created by WeiQingFeng on 2017/8/25.
 */

public class FragmentCollectionDataSource extends BaseListDataSource {
    public FragmentCollectionDataSource(Context context) {
        super(context);
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList models = new ArrayList();
        for (int i = 0; i < 10; i++) {
            models.add(new NetResult());
        }
        hasMore = false;
        return models;
    }
}
