package com.lida.cloud.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.lida.cloud.R;
import com.lida.cloud.adapter.AdapterActivityGoodList;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.ActivityGoodListBean;
import com.midian.base.api.ApiCallback;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.pulltorefresh.HeaderFooterGridView;
import com.vondear.rxtools.view.RxToast;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 商品列表
 * Created by WeiQingFeng on 2017/8/25.
 */

public class ActivityGoodList extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.rbNew)
    RadioButton rbNew;
    @BindView(R.id.rbCount)
    RadioButton rbCount;
    @BindView(R.id.rbPrice)
    RadioButton rbPrice;
    @BindView(R.id.rGroup)
    RadioGroup rGroup;
    @BindView(R.id.gvGood)
    HeaderFooterGridView gvGood;

    private boolean sortByPositive = true;
    private String sort = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goodlist);
        ButterKnife.bind(this);
        topbar.setTitle(mBundle.getString("title"));
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        gvGood.setEmptyView(LayoutInflater.from(_activity).inflate(R.layout.layout_empty,null));
        AppUtil.getApiClient(ac).goodList("",mBundle.getString("cate_id"),"",sort,"1",callback);
        rGroup.setOnCheckedChangeListener(this);
        rbPrice.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                if(sortByPositive){
                    sortByPositive = false;
                    sort = "2";
                    rbPrice.setCompoundDrawablesWithIntrinsicBounds(null,null,getDrawable(R.drawable.icon_sort_d),null);
                }else{
                    sortByPositive = true;
                    sort = "1";
                    rbPrice.setCompoundDrawablesWithIntrinsicBounds(null,null,getDrawable(R.drawable.icon_sort_t),null);
                }
                AppUtil.getApiClient(ac).goodList("",mBundle.getString("cate_id"),"",sort,"1",callback);
            }
        });
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
            if(res.isOK()){
                ActivityGoodListBean bean = (ActivityGoodListBean) res;
                gvGood.setAdapter(new AdapterActivityGoodList(_activity,bean.getData().get(0).getList()));
            }
        }

        @Override
        public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
            RxToast.error(_activity,"网络异常").show();
        }

        @Override
        public void onParseError(String tag) {
            RxToast.error(_activity,"数据解析异常").show();
        }
    };

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        int type = 1;
        switch (checkedId){
            case R.id.rbNew:
                type=1;
                sort="1";
                break;
            case R.id.rbCount:
                type=2;
                sort="1";
                break;
            case R.id.rbPrice:
                type=3;
                break;
        }
        AppUtil.getApiClient(ac).goodList("",mBundle.getString("cate_id"),"",sort,type+"",callback);
    }
}
