package com.lida.cloud.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;

import com.lida.cloud.R;
import com.lida.cloud.activity.ActivityLoginAct;
import com.lida.cloud.adapter.AdapterFragmetCollectionGood;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.CollectGoodsBean;
import com.lida.cloud.bean.CollectShopBean;
import com.lida.cloud.broadcast.EditBroadCast;
import com.lida.cloud.broadcast.RefreshCollectGood;
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
 * 收藏的商品
 * Created by Administrator on 2017/8/8.
 */

public class FragmentCollectionGood extends BaseFragment {

    @BindView(R.id.btnDel)
    Button btnDel;
    Unbinder unbinder;
    @BindView(R.id.gridView)
    GridView gridView;

    private RefreshCollectGood refreshCollectGood;
    private EditBroadCast editBroadCast;
    private AdapterFragmetCollectionGood adapter;
    private RxDialogSureCancel dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = LayoutInflater.from(_activity).inflate(R.layout.fragment_collectiongoods, null);
        unbinder = ButterKnife.bind(this, v);
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

        refreshCollectGood = new RefreshCollectGood(){
            @Override
            public void onReceive(Context context, Intent intent) {
                super.onReceive(context, intent);
                getData();
            }
        };
        IntentFilter var = new IntentFilter();
        var.addAction("android.intent.action.RefreshCollectGood");
        _activity.registerReceiver(refreshCollectGood, var);
        return v;
    }

    private void getData() {
        AppUtil.getApiClient(ac).collectGoods(_activity, callback);
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
                if ("collectGoods".equals(tag)) {
                    CollectGoodsBean bean = (CollectGoodsBean) res;
                    if (bean.getData().get(0).getList().size() > 0) {
                        adapter = new AdapterFragmetCollectionGood(_activity, bean.getData().get(0).getList());
                    } else {
                        adapter = new AdapterFragmetCollectionGood(_activity, null);
                    }
                    if (gridView.getAdapter() != null) {
                        adapter.toggleEdit(true);
                    }
                    gridView.setAdapter(adapter);
                }
                if ("collectRemovegoods".equals(tag)) {
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
        _activity.unregisterReceiver(refreshCollectGood);
    }

    @OnClick(R.id.btnDel)
    public void onViewClicked() {
        dialog = new RxDialogSureCancel(_activity);
        dialog.getTvTitle().setVisibility(View.GONE);
        dialog.setContent("确定删除?");
        dialog.setSureListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<CollectGoodsBean.DataBean.ListBean> data = adapter.getData();
                StringBuilder temp = new StringBuilder();
                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).isSelect()) {
                        temp.append(data.get(i).getCol_id() + ",");
                    }
                }
                String ids = temp.substring(0, temp.length() - 1);
                AppUtil.getApiClient(ac).collectRemovegoods(_activity, ids, callback);
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
