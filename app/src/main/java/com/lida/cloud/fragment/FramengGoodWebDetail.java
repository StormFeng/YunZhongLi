package com.lida.cloud.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.apkfuns.logutils.LogUtils;
import com.lida.cloud.bean.GoodDetailBean;
import com.midian.base.base.BaseFragment;

/**
 * Created by WeiQingFeng on 2017/8/25.
 */

public class FramengGoodWebDetail extends BaseFragment {
    private GoodDetailBean data;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        data = (GoodDetailBean) getArguments().getSerializable("data");
        LogUtils.e(data+"\n********************************");
        WebView webView = new WebView(_activity);
        // 设置WebView属性，能够执行JavaScript脚本
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }
        });
        webView.loadUrl(data.getData().get(0).getContent());
        webView.getSettings().setJavaScriptEnabled(true);
        // 设置可以支持缩放
        webView.getSettings().setSupportZoom(true);
        // 设置出现缩放工具
        webView.getSettings().setBuiltInZoomControls(true);
        // 为图片添加放大缩小功能
        webView.getSettings().setUseWideViewPort(true);
        webView.setInitialScale(100);
        return webView;
    }
}
