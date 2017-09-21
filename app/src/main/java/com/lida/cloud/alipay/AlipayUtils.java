package com.lida.cloud.alipay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.alipay.sdk.app.PayTask;
import com.midian.base.util.UIHelper;
import com.vondear.rxtools.view.RxToast;

import java.util.Map;

/**
 * Created by WeiQingFeng on 2017/9/13.
 */

public class AlipayUtils {

    private static final int SDK_PAY_FLAG = 1;
    private Activity context;
    private String orderInfo;

    public AlipayUtils builder(Activity context, String orderInfo){
        this.context = context;
        this.orderInfo = orderInfo;
        return this;
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case SDK_PAY_FLAG:
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        RxToast.success("支付成功");
                        Intent intent = new Intent("android.intent.action.PersonalInfoRefreshBroadCast");
                        context.sendBroadcast(intent);
                        context.finish();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。]
                        RxToast.error("支付失败，请重试！");
                    }
                    break;
            }
        }
    };

    public void pay(){
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(context);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }
}
