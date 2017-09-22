package com.lida.cloud.fragment.main;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lida.cloud.R;
import com.lida.cloud.activity.ActivityAccountSetting;
import com.lida.cloud.activity.ActivityBalance;
import com.lida.cloud.activity.ActivityConsumptionBean;
import com.lida.cloud.activity.ActivityCreditDetail;
import com.lida.cloud.activity.ActivityLoginAct;
import com.lida.cloud.activity.ActivityMyOrder;
import com.lida.cloud.activity.ActivitySetting;
import com.lida.cloud.adapter.AdapterPersonalIconTab;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.PersonalInfoBean;
import com.lida.cloud.bean.SignBean;
import com.lida.cloud.broadcast.PersonalInfoRefreshBroadCast;
import com.lida.cloud.widght.InnerGridView;
import com.makeramen.roundedimageview.RoundedImageView;
import com.midian.base.api.ApiCallback;
import com.midian.base.app.AppManager;
import com.midian.base.base.BaseFragment;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.vondear.rxtools.RxActivityUtils;
import com.vondear.rxtools.view.RxToast;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 我的
 * Created by Administrator on 2017/8/8.
 */

public class FragmentPersonal extends BaseFragment{

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.gvIconTabs)
    InnerGridView gvIconTabs;
    Unbinder unbinder;
    @BindView(R.id.llBalance)
    LinearLayout llBalance;
    @BindView(R.id.llCreditScore)
    LinearLayout llCreditScore;
    @BindView(R.id.llConsumptionBean)
    LinearLayout llConsumptionBean;
    @BindView(R.id.tvLookMore)
    TextView tvLookMore;
    @BindView(R.id.tvReadyToPay)
    TextView tvReadyToPay;
    @BindView(R.id.tvReadyToDeliver)
    TextView tvReadyToDeliver;
    @BindView(R.id.tvReadyToReceive)
    TextView tvReadyToReceive;
    @BindView(R.id.tvReadyToCommend)
    TextView tvReadyToCommend;
    @BindView(R.id.tvService)
    TextView tvService;
    @BindView(R.id.ivHead)
    RoundedImageView ivHead;
    @BindView(R.id.tvName)
    public TextView tvName;
    @BindView(R.id.tvBalance)
    TextView tvBalance;
    @BindView(R.id.tvCreditScore)
    TextView tvCreditScore;
    @BindView(R.id.tvConsumptionBean)
    TextView tvConsumptionBean;

    public String head = null;
    public String qCode = null;
    private PersonalInfoRefreshBroadCast personalInfoRefreshBroadCast;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = LayoutInflater.from(_activity).inflate(R.layout.fragment_personal, null);
        unbinder = ButterKnife.bind(this, v);
        topbar.setTitle("我的");
        gvIconTabs.setAdapter(new AdapterPersonalIconTab(this,_activity));
        AppUtil.getApiClient(ac).getPersonalInfo(_activity,ac.memid, callback);
        personalInfoRefreshBroadCast = new PersonalInfoRefreshBroadCast(){
            @Override
            public void onReceive(Context context, Intent intent) {
                super.onReceive(context, intent);
                AppUtil.getApiClient(ac).getPersonalInfo(_activity,ac.memid, callback);
            }
        };
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.PersonalInfoRefreshBroadCast");
        _activity.registerReceiver(personalInfoRefreshBroadCast,filter);
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        _activity.unregisterReceiver(personalInfoRefreshBroadCast);
    }

    @OnClick({R.id.llBalance, R.id.llCreditScore, R.id.llConsumptionBean, R.id.tvLookMore,
            R.id.tvReadyToPay, R.id.tvReadyToDeliver, R.id.tvReadyToReceive, R.id.tvReadyToCommend,
            R.id.tvService, R.id.tvSetting, R.id.llHead})
    public void onViewClicked(View view) {
        Bundle bundle = new Bundle();
        if(!ac.isLogin()){
            UIHelper.jump(_activity, ActivityLoginAct.class);
        }
        switch (view.getId()) {
            case R.id.llBalance:
                UIHelper.jump(_activity, ActivityBalance.class);
                break;
            case R.id.llCreditScore:
                UIHelper.jump(_activity, ActivityCreditDetail.class);
                break;
            case R.id.llConsumptionBean:
                UIHelper.jump(_activity, ActivityConsumptionBean.class);
                break;
            case R.id.tvLookMore:
                bundle.putInt("position",0);
                UIHelper.jump(_activity, ActivityMyOrder.class);
                break;
            case R.id.tvReadyToPay:
                bundle.putInt("position",1);
                UIHelper.jump(_activity, ActivityMyOrder.class,bundle);
                break;
            case R.id.tvReadyToDeliver:
                bundle.putInt("position",2);
                UIHelper.jump(_activity, ActivityMyOrder.class,bundle);
                break;
            case R.id.tvReadyToReceive:
                bundle.putInt("position",3);
                UIHelper.jump(_activity, ActivityMyOrder.class,bundle);
                break;
            case R.id.tvReadyToCommend:
                bundle.putInt("position",4);
                UIHelper.jump(_activity, ActivityMyOrder.class,bundle);
                break;
            case R.id.tvService:
                bundle.putInt("position",5);
                UIHelper.jump(_activity, ActivityMyOrder.class,bundle);
                break;
            case R.id.tvSetting:
                UIHelper.jump(_activity, ActivitySetting.class);
                break;
            case R.id.llHead:
                UIHelper.jump(_activity, ActivityAccountSetting.class);
                break;
        }
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
                if("getPersonalInfo".equals(tag)){
                    PersonalInfoBean bean = (PersonalInfoBean) res;
                    PersonalInfoBean.DataBean dataBean = bean.getData().get(0);
                    if(dataBean.getMem_tx()!=null){
                        head = dataBean.getMem_tx();
                        ac.setImage(ivHead, dataBean.getMem_tx());
                    }
                    String mem_state = dataBean.getMem_state();
                    String mem_name = dataBean.getMem_name();
                    if("0".equals(mem_state)){
                        tvName.setText(mem_name+"(普通会员)");
                    }else if("1".equals(mem_state)){
                        tvName.setText(mem_name+"(银钻会员)");
                    }else if("2".equals(mem_state)){
                        tvName.setText(mem_name+"(金钻会员)");
                    }else if("3".equals(mem_state)){
                        tvName.setText(mem_name+"(商家)");
                    }
                    ac.saveMemState(mem_state);
                    tvBalance.setText(dataBean.getMem_yue());
                    tvCreditScore.setText(dataBean.getMem_credit());
                    tvConsumptionBean.setText(dataBean.getMem_pay_beans());
                    qCode = dataBean.getQrcode();
                }
                if("token".equals(tag)){
                    SignBean bean = (SignBean) res;
                    try {
                        JSONObject jsonObject = new JSONObject(bean.getData().get(0).getTokenInfo());
                        ac.saveUserInfo(jsonObject.getInt("uid")+"",jsonObject.getString("access_token"),
                                jsonObject.getString("refresh_token"),jsonObject.getString("timestamp")+"000");
                        AppUtil.getApiClient(ac).getPersonalInfo(_activity, ac.memid, callback);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }else{
                RxToast.error(_activity,res.getMessage()).show();
                if("10001".equals(res.getErrorCode())||"10003".equals(res.getErrorCode())){
                    ac.clearUserInfo();
                    RxActivityUtils.skipActivityAndFinishAll(AppManager.getAppManager().currentActivity(), ActivityLoginAct.class);
                }else if("10002".equals(res.getErrorCode())){
                    AppUtil.getApiClient(ac).token(ac.memid,ac.refresh_token,callback);
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

}
