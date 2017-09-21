package com.lida.cloud.data;

import android.content.Context;

import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.ActivityPublicWelfareBean;
import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;

/**
 * 粉丝公益
 * Created by xkr on 2017/9/8.
 */

public class ActivityPublicWelfareData extends BaseListDataSource {

    public ActivityPublicWelfareData(Context context) {
        super(context);
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        this.page = page;
        ArrayList<NetResult> models = new ArrayList<>();
        ActivityPublicWelfareBean bean = AppUtil.getApiClient(ac).welfare(page+"","10");
        if(bean.getData()!=null&&bean.getData().size()>0){
            models.addAll(bean.getData().get(0).getList());
            if(bean.getData().get(0).getList()!=null&&bean.getData().get(0).getList().size()>0){
                if(bean.getData().get(0).getList().size()<10){
                    hasMore = false;
                }else {
                    hasMore = true;
                }
            }
        }
        return models;
    }
}
