package com.lida.cloud.app;

import android.content.Intent;

import com.lida.cloud.RefreshService;
import com.lida.cloud.util.ShareUtil;
import com.midian.base.app.AppContext;
import com.vondear.rxtools.RxTimeUtils;
import com.vondear.rxtools.RxUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2017/8/8.
 */

public class BaseAppContext extends AppContext {
    @Override
    public void onCreate() {
        super.onCreate();
        Api.init(this);
        RxUtils.init(this);
        ShareUtil.init();
    }
}
