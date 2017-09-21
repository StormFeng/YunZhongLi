package com.lida.cloud.data;

import android.app.Activity;
import android.content.Context;

import com.lida.cloud.activity.ActivityMyRecommend;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.MyRecommendBean;
import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;

/**
 * 推荐的商户
 * Created by xkr on 2017/9/7.
 */

public class FragmentRecommendMerchantsData extends BaseListDataSource{

    public FragmentRecommendMerchantsData(Context context) {
        super(context);
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<NetResult> models = new ArrayList<>();
        final MyRecommendBean bean = AppUtil.getApiClient(ac).myrecommend("1");
        if (bean.isOK()){
            if(bean.getData()!=null&&bean.getData().size()>0){
                models.addAll(bean.getData().get(0).getList());
                ActivityMyRecommend.counts[0] = bean.getData().get(0).getCount()+"";
                ActivityMyRecommend.moneys[0] = bean.getData().get(0).getTotal()+"";
                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ActivityMyRecommend.tvRecommendedMerchants.setText(bean.getData().get(0).getCount());
                        ActivityMyRecommend.tvAllMoney.setText(bean.getData().get(0).getTotal()+"");
                    }
                });
            }
        }
        hasMore = false;
        return models;
    }
}
