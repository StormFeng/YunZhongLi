package com.lida.cloud.fragment.main;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.google.gson.Gson;
import com.lida.cloud.R;
import com.lida.cloud.activity.ActivityReadyToCommitOrder;
import com.lida.cloud.adapter.ExpandableListAdapter;
import com.lida.cloud.adapter.UpdateView;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.GoodBean;
import com.lida.cloud.bean.ShopCarListBean;
import com.lida.cloud.broadcast.RefreshShopCar;
import com.lida.cloud.widght.BaseApiCallback;
import com.lida.cloud.widght.SmoothCheckBox;
import com.midian.base.api.ApiCallback;
import com.midian.base.base.BaseFragment;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.vondear.rxtools.view.RxToast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 购物车
 * Created by Administrator on 2017/8/8.
 */

public class FragmentShopCar extends BaseFragment {

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    Unbinder unbinder;
    @BindView(R.id.expandableListView)
    ExpandableListView expandableListView;
    @BindView(R.id.cb_SelectAll)
    SmoothCheckBox cbSelectAll;
    @BindView(R.id.tv_AllMoney)
    TextView tvAllMoney;
    @BindView(R.id.tv_Transport)
    TextView tvTransport;
    @BindView(R.id.btn_Settlement)
    Button btnSettlement;

    private ExpandableListAdapter adapter;
    private ShopCarListBean goodBean;
    boolean isEdit = true;
    TextView tvEdit;

    private int allCount;
    private double allMoney;

    private RefreshShopCar refreshShopCar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = LayoutInflater.from(_activity).inflate(R.layout.fragment_shopcar, null);
        unbinder = ButterKnife.bind(this, v);
        init();
        getData();
        refreshShopCar = new RefreshShopCar(){
            @Override
            public void onReceive(Context context, Intent intent) {
                super.onReceive(context, intent);
                getData();
            }
        };
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.RefreshShopCar");
        _activity.registerReceiver(refreshShopCar,filter);
        return v;
    }

    private void getData() {
        AppUtil.getApiClient(ac).cartList(callback);
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
                goodBean = (ShopCarListBean) res;
                adapter = new ExpandableListAdapter(_activity, goodBean);
                adapter.setChangedListener(updateView);
                expandableListView.setAdapter(adapter);
                for (int i = 0; i < goodBean.getData().size(); i++) {
                    expandableListView.expandGroup(i);
                }
                adapter.clearStatus();
            }else{
                RxToast.error(res.getMessage());
            }
        }

        @Override
        public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
            _activity.hideLoadingDlg();
        }

        @Override
        public void onParseError(String tag) {
            _activity.hideLoadingDlg();
        }
    };

    private void init() {
        topbar.setTitle("购物车");
        tvEdit = topbar.getRight_tv();
        topbar.setRightText("编辑", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit();
            }
        });
        expandableListView.setGroupIndicator(null);
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                return true;
            }
        });
    }

    UpdateView updateView = new UpdateView() {
        @Override
        public void update(boolean isAllSelected, int count, double price) {
            btnSettlement.setText("结算(" + count + ")");
            tvAllMoney.setText("￥" + price);
            cbSelectAll.setChecked(isAllSelected);
        }
    };

    private void edit() {
        if (isEdit) {
            isEdit = false;
            tvEdit.setText("完成");
            for (int i = 0; i < goodBean.getData().size(); i++) {
                for (int n = 0; n < goodBean.getData().get(i).getList().size(); n++) {
                    goodBean.getData().get(i).getList().get(n).setEdit(true);
                }
            }
        } else {
            isEdit = true;
            tvEdit.setText("编辑");
            for (int i = 0; i < goodBean.getData().size(); i++) {
                for (int n = 0; n < goodBean.getData().get(i).getList().size(); n++) {
                    goodBean.getData().get(i).getList().get(n).setEdit(false);
                }
            }
        }
        adapter.notifyDataSetChanged();
    }

    private void selectAll() {
        allCount = goodBean.getAllCount();
        allMoney = goodBean.getAllMoney();
        String isSelect;
        if (!cbSelectAll.isChecked()) {
            isSelect = "1";
            for (int i = 0; i < goodBean.getData().size(); i++) {
                for (int n = 0; n < goodBean.getData().get(i).getList().size(); n++) {
                    final int finalI = i;
                    final int finalN = n;
                    AppUtil.getApiClient(ac).cartDefault(goodBean.getData().get(i).getList().get(n).getId(),
                            isSelect,new BaseApiCallback(){
                                @Override
                                public void onApiSuccess(NetResult res, String tag) {
                                    super.onApiSuccess(res, tag);
                                    if(res.isOK()){
                                        if(finalN ==goodBean.getData().get(finalI).getList().size()-1){
                                            LogUtils.e("finalN ==goodBean.getData().get(finalI).getList().size()-1");
                                            goodBean.setAllSelect(true);
                                            for (int i = 0; i < goodBean.getData().size(); i++) {
                                                goodBean.getData().get(i).setSelected(true);
                                                for (int n = 0; n < goodBean.getData().get(i).getList().size(); n++) {
//                                                    if (!goodBean.getData().get(i).getList().get(n).isSelected()) {
                                                    if (goodBean.getData().get(i).getList().get(n).getIsselect()!=1) {
                                                        allCount++;
                                                        allMoney += Integer.valueOf(goodBean.getData().get(i).getList().get(n).getTotal())
                                                                * Double.valueOf(goodBean.getData().get(i).getList().get(n).getCost());
//                                                        goodBean.getData().get(i).getList().get(n).setSelected(true);
                                                        goodBean.getData().get(i).getList().get(n).setIsselect(1);
                                                    }
                                                }
                                            }
                                            goodBean.setAllMoney(allMoney);
                                            goodBean.setAllCount(allCount);
                                            updateView.update(goodBean.isAllSelect(), allCount, allMoney);
                                            adapter.notifyDataSetChanged();
                                        }
                                    }else{
                                        RxToast.error(res.getMessage());
                                    }
                                }
                            });
                }
            }
        } else {
            isSelect = "0";
            for (int i = 0; i < goodBean.getData().size(); i++) {
                for (int n = 0; n < goodBean.getData().get(i).getList().size(); n++) {
                    final int finalI = i;
                    final int finalN = n;
                    AppUtil.getApiClient(ac).cartDefault(goodBean.getData().get(i).getList().get(n).getId(),
                            isSelect,new BaseApiCallback(){
                                @Override
                                public void onApiSuccess(NetResult res, String tag) {
                                    super.onApiSuccess(res, tag);
                                    if(res.isOK()){
                                        if(finalN ==goodBean.getData().get(finalI).getList().size()-1){
                                            goodBean.setAllSelect(false);
                                            for (int i = 0; i < goodBean.getData().size(); i++) {
                                                goodBean.getData().get(i).setSelected(false);
                                                for (int n = 0; n < goodBean.getData().get(i).getList().size(); n++) {
//                                                    goodBean.getData().get(i).getList().get(n).setSelected(false);
                                                    goodBean.getData().get(i).getList().get(n).setIsselect(0);
                                                }
                                            }
                                        }
                                        allCount = 0;
                                        allMoney = 0;
                                        goodBean.setAllMoney(allMoney);
                                        goodBean.setAllCount(allCount);
                                        updateView.update(goodBean.isAllSelect(), allCount, allMoney);
                                        adapter.notifyDataSetChanged();
                                    }else{
                                        RxToast.error(res.getMessage());
                                    }
                                }
                            });
                }
            }
        }
    }

    @OnClick({R.id.btn_Settlement,R.id.cb_SelectAll})
    public void onViewClicked(View v) {
        switch (v.getId()){
            case R.id.cb_SelectAll:
                selectAll();
                break;
            case R.id.btn_Settlement:
                if("结算(0)".equals(btnSettlement.getText().toString())){
                    RxToast.error("请选择商品结算");
                    return;
                }
                UIHelper.jump(_activity, ActivityReadyToCommitOrder.class);
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        _activity.unregisterReceiver(refreshShopCar);
    }
}
