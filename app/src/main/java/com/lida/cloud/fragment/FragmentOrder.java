package com.lida.cloud.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.apkfuns.logutils.LogUtils;
import com.lida.cloud.R;
import com.lida.cloud.activity.ActivityLoginAct;
import com.lida.cloud.adapter.AdapterFragmentOrder;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.OrderListBean;
import com.lida.cloud.broadcast.RefreshOrderList;
import com.midian.base.api.ApiCallback;
import com.midian.base.app.AppManager;
import com.midian.base.base.BaseFragment;
import com.midian.base.bean.NetResult;
import com.vondear.rxtools.RxActivityUtils;
import com.vondear.rxtools.view.RxToast;

/**
 * 订单
 * Created by WeiQingFeng on 2017/8/25.
 */

public class FragmentOrder extends BaseFragment {

    private ListView listView;
    private RefreshOrderList refreshOrderList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        listView = new ListView(_activity);
        listView.setSelector(R.drawable.transparent_bg);
        getData();
        refreshOrderList = new RefreshOrderList(){
            @Override
            public void onReceive(Context context, Intent intent) {
                super.onReceive(context, intent);
                getData();
            }
        };
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.RefreshOrderList");
        _activity.registerReceiver(refreshOrderList,filter);
        return listView;
    }

    public void getData() {
        AppUtil.getApiClient(ac).orderList(_activity,getArguments().getString("status"),callback);
    }

    ApiCallback callback = new ApiCallback() {
        @Override
        public void onApiStart(String tag) {
            _activity.showLoadingDlg();
        }

        @Override
        public void onApiLoading(long count, long current, String tag) {
            _activity.showLoadingDlg();
        }

        @Override
        public void onApiSuccess(NetResult res, String tag) {
            _activity.hideLoadingDlg();
            if(res.isOK()){
                OrderListBean bean = (OrderListBean) res;
                listView.setAdapter(new AdapterFragmentOrder(_activity,FragmentOrder.this,bean.getData().get(0).getOrder()));
            }else{
                RxToast.error(_activity,res.getMessage()).show();
                if("10001".equals(res.getErrorCode())||"10002".equals(res.getErrorCode())
                        ||"10003".equals(res.getErrorCode())){
                    ac.clearUserInfo();
                    RxActivityUtils.skipActivityAndFinishAll(AppManager.getAppManager().currentActivity(), ActivityLoginAct.class);
                }
            }
        }

        @Override
        public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
            _activity.hideLoadingDlg();
            RxToast.error("网络异常");
        }

        @Override
        public void onParseError(String tag) {
            _activity.hideLoadingDlg();
            RxToast.error("数据解析异常");
        }
    };
}
