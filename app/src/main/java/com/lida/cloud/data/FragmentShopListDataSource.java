package com.lida.cloud.data;

import android.content.Context;

import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.BusinessListBean;
import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;
import com.vondear.rxtools.RxSPUtils;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/8/8.
 */

public class FragmentShopListDataSource extends BaseListDataSource {

    private String cid;
    private String keyWord;


    public FragmentShopListDataSource(Context context, String cid, String keyWord) {
        super(context);
        this.cid = cid;
        this.keyWord = keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<NetResult> models = new ArrayList<>();
        this.page = page;
        BusinessListBean bean = AppUtil.getApiClient(ac).businessList(ac.lat, ac.lon, cid, keyWord, this.page+"");
        if (bean.isOK()){
            if(bean.getData().size()>0){
                models.addAll(bean.getData().get(0).getStore());
                if(bean.getData().get(0).getStore().size()<10){
                    hasMore = false;
                }else{
                    hasMore = true;
                }
            }else{
                return models;
            }
        }
        return models;
    }
}
