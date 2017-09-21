package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 加密字符串
 * Created by WeiQingFeng on 2017/8/23.
 */

public class PayBean extends NetResult {

    private List<DataBean> data;

    public static PayBean parse(String json) throws AppException {
        PayBean res = new PayBean();
        try {
            res = gson.fromJson(json, PayBean.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            throw AppException.json(e);
        }
        return res;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }


    public static class DataBean {
        /**
         * orderString : alipay_sdk=alipay-sdk-php-20161101&amp;app_id=2017060107400667&amp;biz_content=%7B%22body%22%3A%22%E5%85%85%E5%80%BC%E4%BD%99%E9%A2%9D100.00%E5%85%83%22%2C%22subject%22%3A+%22%E5%85%85%E5%80%BC%E4%BD%99%E9%A2%9D100.00%E5%85%83%22%2C%22out_trade_no%22%3A+%22B_A831608391047508%22%2C%22timeout_express%22%3A+%2230m%22%2C%22total_amount%22%3A+%22100.00%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&amp;charset=UTF-8&amp;format=json&amp;method=alipay.trade.app.pay&amp;notify_url=http%3A%2F%2Fyzl.gzldrj.com%2Fapi%2Fpay%2Falipay%2Fnotice&amp;sign_type=RSA2&amp;timestamp=2017-09-04+19%3A34%3A56&amp;version=1.0&amp;sign=CPrDcaYz7YKp923EXNK%2BV4l7DmATvQKUwUCz5sNoMp8DkKwfHhrWuKoUJTkhAbwsdwkIFo9o7EDL5%2Frh%2Bn822kHyJKfHLdUA99UenDXxIfRK7EsYFBi3%2BGXNDEsraoWclG%2BIW%2BxdRHy%2BU0q%2F6FvrOBMSiTs9BxZ1FnndmcFr3hpGkBUA8vJaI6DMZ50xu4bKLhakhPGqeTbhG21veV%2FAo427ia7FEPcmMOOpG4BymjKP8kUXqT%2Fo5VO0DPM98U4i6PQgoDL5WXO%2BhvxzsUrJULVBsIB0dB1SSCFUF2oJq4sQtUj2Ji3EgDmUN0a8dAfXAG9DIX1Ant23K8GUX3bgNA%3D%3D
         */

        private String orderString;

        public String getOrderString() {
            return orderString;
        }

        public void setOrderString(String orderString) {
            this.orderString = orderString;
        }
    }
}
