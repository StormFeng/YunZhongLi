package com.lida.cloud.activity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.lida.cloud.R;
import com.lida.cloud.app.Constant;
import com.midian.base.base.BaseActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 关于我们
 * Created by Administrator on 2017/8/10.
 */

public class ActivityAboutUs extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.webView)
    WebView webView;

    private String title;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);
        ButterKnife.bind(this);
        if(mBundle!=null){
            title = mBundle.getString("title");
            url = mBundle.getString("url");
        }else{
            title = "关于我们";
            url = Constant.BASEURL+"/wap/member/us";
        }
        topbar.setTitle(title);
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        webView.loadUrl(url);
    }
}
