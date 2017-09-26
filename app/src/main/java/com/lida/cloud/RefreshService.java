package com.lida.cloud;

import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import com.apkfuns.logutils.LogUtils;
import com.lida.cloud.activity.ActivityLoginAct;
import com.lida.cloud.app.AesEncryptionUtil;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.app.Constant;
import com.lida.cloud.bean.LoginBean;
import com.lida.cloud.bean.SignBean;
import com.midian.base.api.ApiCallback;
import com.midian.base.app.AppContext;
import com.midian.base.app.AppManager;
import com.midian.base.bean.NetResult;
import com.midian.base.util.AnimatorUtils;
import com.vondear.rxtools.RxActivityUtils;
import com.vondear.rxtools.RxTimeUtils;
import com.vondear.rxtools.view.RxToast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by WeiQingFeng on 2017/8/28.
 */

public class RefreshService extends Service {

    private CountDownTimer timer;
    private AppContext ac;

    @Override
    public void onCreate() {
        super.onCreate();
        ac = (AppContext) getApplication();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        long now = RxTimeUtils.getCurTimeMills();
        long loginTime = Long.parseLong(ac.timestamp);
        final long dTime = now - loginTime;
        LogUtils.e("现在时刻："+RxTimeUtils.milliseconds2String(now));
        LogUtils.e("现在时刻："+now);
        LogUtils.e("登录时刻："+RxTimeUtils.milliseconds2String(loginTime));
        LogUtils.e("登录时刻："+loginTime);
        LogUtils.e("刷新时间："+(Constant.REFRESHTIME - dTime));
        if(Constant.REFRESHTIME - dTime > 0){
            timer = new CountDownTimer(Constant.REFRESHTIME - dTime, Constant.REFRESHTIME - dTime - 1) {
                @Override
                public void onTick(long millisUntilFinished) {
                    AppUtil.getApiClient(ac).token(ac.memid,ac.refresh_token,callback);
                }

                @Override
                public void onFinish() {
                    LogUtils.e("onFinish -- 倒计时结束");
                }
            };
        }else{
            timer = new CountDownTimer(Constant.REFRESHTIME , Constant.REFRESHTIME ) {
                @Override
                public void onTick(long millisUntilFinished) {
                    AppUtil.getApiClient(ac).token(ac.memid,ac.refresh_token,callback);
                }

                @Override
                public void onFinish() {
                    LogUtils.e("onFinish -- 倒计时结束");
                }
            };
        }

        timer.start();
        return super.onStartCommand(intent, flags, startId);
    }

    ApiCallback callback = new ApiCallback() {
        @Override
        public void onApiStart(String tag) {

        }

        @Override
        public void onApiLoading(long count, long current, String tag) {

        }

        @Override
        public void onApiSuccess(NetResult res, String tag) {
            if(res.isOK()){
                SignBean bean = (SignBean) res;
                String tokenInfo = bean.getData().get(0).getTokenInfo();
                String decrypt = AesEncryptionUtil.decrypt(tokenInfo);
                JSONObject jsonObject;
                try {
                    jsonObject = new JSONObject(decrypt);
                    ac.saveUserInfo(jsonObject.getInt("uid")+"",jsonObject.getString("access_token"),
                            jsonObject.getString("refresh_token"),jsonObject.getString("timestamp")+"000");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else{
                RxToast.error(ac, res.getMessage()).show();
                if("10001".equals(res.getErrorCode())||"10002".equals(res.getErrorCode())
                        ||"10003".equals(res.getErrorCode())){
                    ac.clearUserInfo();
                    RxActivityUtils.skipActivityAndFinishAll(AppManager.getAppManager().currentActivity(), ActivityLoginAct.class);
                }
            }
        }

        @Override
        public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {

        }

        @Override
        public void onParseError(String tag) {

        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(timer!=null){
            timer.cancel();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
