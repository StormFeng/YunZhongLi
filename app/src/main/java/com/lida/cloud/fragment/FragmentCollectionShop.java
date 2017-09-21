package com.lida.cloud.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.apkfuns.logutils.LogUtils;
import com.lida.cloud.R;
import com.lida.cloud.activity.ActivityLoginAct;
import com.lida.cloud.adapter.AdapterFragmetCollectionShop;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.CollectShopBean;
import com.lida.cloud.broadcast.EditBroadCast;
import com.lida.cloud.broadcast.RefreshCollectShop;
import com.midian.base.api.ApiCallback;
import com.midian.base.app.AppManager;
import com.midian.base.base.BaseFragment;
import com.midian.base.bean.NetResult;
import com.vondear.rxtools.RxActivityUtils;
import com.vondear.rxtools.view.RxToast;
import com.vondear.rxtools.view.dialog.RxDialogSureCancel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 收藏的店铺
 * Created by Administrator on 2017/8/8.
 */

public class FragmentCollectionShop extends BaseFragment {
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.btnDel)
    Button btnDel;
    Unbinder unbinder;

    private RefreshCollectShop refreshCollectShop;
    private EditBroadCast editBroadCast;
    private AdapterFragmetCollectionShop adapter;
    private RxDialogSureCancel dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = LayoutInflater.from(_activity).inflate(R.layout.fragment_collectionshop, null);
        unbinder = ButterKnife.bind(this, v);
        LogUtils.e("经纬度：" + ac.lon + "   " + ac.lat);
        getData();
        editBroadCast = new EditBroadCast() {
            @Override
            public void onReceive(Context context, Intent intent) {
                super.onReceive(context, intent);
                boolean order = intent.getBooleanExtra("order", false);
                if (order) {
                    btnDel.setVisibility(View.VISIBLE);
                    adapter.toggleEdit(true);
                } else {
                    btnDel.setVisibility(View.GONE);
                    adapter.toggleEdit(false);
                }
            }
        };
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.EDITBROADCAST");
        _activity.registerReceiver(editBroadCast, filter);

        refreshCollectShop = new RefreshCollectShop(){
            @Override
            public void onReceive(Context context, Intent intent) {
                super.onReceive(context, intent);
                getData();
            }
        };
        IntentFilter filter1 = new IntentFilter();
        filter1.addAction("android.intent.action.RefreshCollectShop");
        _activity.registerReceiver(refreshCollectShop, filter1);
        return v;
    }

    private void getData() {
        AppUtil.getApiClient(ac).collectShop(_activity, ac.memid, ac.lon, ac.lat, callback);
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
            if (res.isOK()) {
                if("collectShop".equals(tag)){
                    CollectShopBean bean = (CollectShopBean) res;
                    if (bean.getData().size() > 0) {
                        adapter = new AdapterFragmetCollectionShop(_activity, bean.getData().get(0).getList());
                    }else{
                        adapter = new AdapterFragmetCollectionShop(_activity, null);
                    }
                    if(listView.getAdapter()!=null){
                        adapter.toggleEdit(true);
                    }
                    listView.setAdapter(adapter);
                }
                if("collectRemoveshop".equals(tag)){
                    dialog.dismiss();
                    getData();
                }
            } else {
                RxToast.error(_activity, res.getMessage()).show();
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
            RxToast.error(_activity, "网络异常").show();
        }

        @Override
        public void onParseError(String tag) {
            _activity.hideLoadingDlg();
            RxToast.error(_activity, "数据解析异常").show();
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        _activity.unregisterReceiver(editBroadCast);
        _activity.unregisterReceiver(refreshCollectShop);
    }

    @OnClick(R.id.btnDel)
    public void onViewClicked() {
        dialog = new RxDialogSureCancel(_activity);
        dialog.getTvTitle().setVisibility(View.GONE);
        dialog.setContent("确定删除?");
        dialog.setSureListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<CollectShopBean.DataBean.ListBean> data = adapter.getData();
                StringBuilder temp = new StringBuilder();
                for (int i = 0; i < data.size(); i++) {
                    if(data.get(i).isSelect()){
                        temp.append(data.get(i).getCol_id()+",");
                    }
                }
                String ids = temp.substring(0, temp.length() - 1);
                AppUtil.getApiClient(ac).collectRemoveshop(_activity,ids,callback);
            }
        });
        dialog.setCancelListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
