package com.lida.cloud.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import com.apkfuns.logutils.LogUtils;
import com.jph.takephoto.model.TResult;
import com.lida.cloud.R;
import com.lida.cloud.adapter.AdapterActivityGoodCommend;
import com.lida.cloud.adapter.AdapterShopPic;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.OrderEvaluateBean;
import com.lida.cloud.widght.BaseTakePhotoActivity;
import com.lida.cloud.widght.dialog.DialogAuthComplete;
import com.midian.base.api.ApiCallback;
import com.midian.base.app.AppContext;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.vondear.rxtools.RxDataUtils;
import com.vondear.rxtools.view.RxToast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 商品评价
 * Created by WeiQingFeng on 2017/9/4.
 */

public class ActivityGoodCommend extends BaseTakePhotoActivity {

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.btnCommit)
    Button btnCommit;

    public int position;
    private OrderEvaluateBean bean;
    private AdapterActivityGoodCommend adapter;
    private String order_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goodcommend);
        ButterKnife.bind(this);
        order_id = getIntent().getExtras().getString("order_id");
        ac = (AppContext) getApplicationContext();
        topbar.setTitle("商品评价");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(this));
        AppUtil.getApiClient(ac).orderEvaluate(order_id,callback);
    }

    @OnClick(R.id.btnCommit)
    public void onViewClicked() {
        JSONArray jsonArray = new JSONArray();
        List<String> pics = new ArrayList<>();
        try {
            for (int i = 0; i < bean.getData().size(); i++) {
                OrderEvaluateBean.DataBean dataBean = bean.getData().get(i);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("goods_id", dataBean.getGoods_id());
                jsonObject.put("content", dataBean.getCon());
                jsonArray.put(jsonObject);
                for (int j = 0; j < dataBean.getPics().size(); j++) {
                    if(!RxDataUtils.isNullString(dataBean.getPics().get(j))){
                        String key = "image_" + dataBean.getGoods_id() + "_" + (j + 1);
                        String value = dataBean.getPics().get(j);
                        pics.add(key+"!$!"+value);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        AppUtil.getApiClient(ac).orderEvaluateWrite(order_id,jsonArray.toString(),pics,callback);
    }

    ApiCallback callback = new ApiCallback() {
        @Override
        public void onApiStart(String tag) {
            showLoadingDlg();
        }

        @Override
        public void onApiLoading(long count, long current, String tag) {
            showLoadingDlg();
        }

        @Override
        public void onApiSuccess(NetResult res, String tag) {
            hideLoadingDlg();
            if (res.isOK()) {
                if("orderEvaluate".equals(tag)){
                    bean = (OrderEvaluateBean) res;
                    adapter = new AdapterActivityGoodCommend((ActivityGoodCommend) _activity,bean.getData());
                    listView.setAdapter(adapter);
                }
                if("orderEvaluateWrite".equals(tag)){
                    RxToast.success("评论提交成功");
                    finish();
                }
            } else {
                RxToast.error(res.getMessage());
            }
        }

        @Override
        public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
            hideLoadingDlg();
            RxToast.error("网络异常");
        }

        @Override
        public void onParseError(String tag) {
            hideLoadingDlg();
            RxToast.error("数据解析异常");
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1003 || requestCode == 1006) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        if (!"".equals(result.getImage().getCompressPath())) {
            List<String> pics = bean.getData().get(position).getPics();
            pics.add(pics.size() - 1, result.getImage().getCompressPath());
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.notifyDataSetChanged();
                }
            });
        }
    }
}
