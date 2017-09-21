package com.lida.cloud.widght;

import com.midian.base.api.ApiCallback;
import com.midian.base.bean.NetResult;

/**
 * Created by WeiQingFeng on 2017/9/4.
 */

public class BaseApiCallback implements ApiCallback {

    @Override
    public void onApiStart(String tag) {

    }

    @Override
    public void onApiLoading(long count, long current, String tag) {

    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {

    }

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {

    }

    @Override
    public void onParseError(String tag) {

    }
}
