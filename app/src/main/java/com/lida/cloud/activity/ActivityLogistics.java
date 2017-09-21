package com.lida.cloud.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import com.baoyachi.stepview.VerticalStepView;
import com.lida.cloud.R;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.LogisticsBean;
import com.midian.base.api.ApiCallback;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.vondear.rxtools.view.RxToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 物流
 * Created by WeiQingFeng on 2017/9/12.
 */

public class ActivityLogistics extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.stepView)
    VerticalStepView stepView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logistics);
        ButterKnife.bind(this);
        topbar.setTitle("物流信息");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        AppUtil.getApiClient(ac).orderLogistics(mBundle.getString("id"),callback);
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
                LogisticsBean bean = (LogisticsBean) res;
                List<LogisticsBean.DataBean> data = bean.getData();
                List<String> temp = new ArrayList<>();
                for (int i = 0; i < data.size(); i++) {
                    temp.add(data.get(data.size()-i-1).getContext());
                }
                stepView.setStepsViewIndicatorComplectingPosition(temp.size()-1)//设置完成的步数
                        .reverseDraw(true)//default is true
                        .setTextSize(13)
                        .setStepViewTexts(temp)//总步骤
                        .setLinePaddingProportion(1)
                        .setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(_activity, android.R.color.white))//设置StepsViewIndicator完成线的颜色
                        .setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(_activity, R.color.uncompleted_text_color))//设置StepsViewIndicator未完成线的颜色
                        .setStepViewComplectedTextColor(ContextCompat.getColor(_activity, android.R.color.white))//设置StepsView text完成线的颜色
                        .setStepViewUnComplectedTextColor(ContextCompat.getColor(_activity, R.color.uncompleted_text_color))//设置StepsView text未完成线的颜色
                        .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(_activity, R.drawable.complted))//设置StepsViewIndicator CompleteIcon
                        .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(_activity, R.drawable.default_icon))//设置StepsViewIndicator DefaultIcon
                        .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(_activity, R.drawable.attention));//设置StepsViewIndicator AttentionIcon

            }else{
                RxToast.error("网络异常");
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
}
