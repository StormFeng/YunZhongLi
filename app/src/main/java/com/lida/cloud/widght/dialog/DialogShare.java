package com.lida.cloud.widght.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.apkfuns.logutils.LogUtils;
import com.lida.cloud.R;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/10/27 0027.
 */

public class DialogShare extends Dialog {

    @BindView(R.id.iv_WeChat)
    TextView ivWeChat;
    @BindView(R.id.tvQQ)
    TextView tvQQ;
    @BindView(R.id.tvSina)
    TextView tvSina;
    @BindView(R.id.btnCancel)
    Button btnCancel;

    private Context context;
    private String title;
    private String content;
    private String imageUrl;
    private String targetUrl;

    public DialogShare(Context context) {
        super(context, R.style.bottom_dialog);
        init(context);
    }

    public DialogShare(Context context, int themeResId) {
        super(context, R.style.bottom_dialog);
        init(context);
    }

    public DialogShare(Context context, String title, String content, String targetUrl) {
        super(context, R.style.bottom_dialog);
        init(context);
        this.title = title;
        this.content = content;
        this.targetUrl = targetUrl;
    }

    private void init(Context context) {
        this.context = context;
        Window w = this.getWindow();
        WindowManager.LayoutParams lp = w.getAttributes();
        lp.gravity = Gravity.BOTTOM;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        w.setAttributes(lp);
        this.setCanceledOnTouchOutside(true);
        View v = View.inflate(context, R.layout.dialog_share, null);
        this.setContentView(v);
        ButterKnife.bind(this, v);
    }

    @OnClick({R.id.iv_WeChat, R.id.tvQQ, R.id.tvSina, R.id.btnCancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_WeChat:
                share(SHARE_MEDIA.WEIXIN,title,content,targetUrl);
                break;
            case R.id.tvQQ:
                share(SHARE_MEDIA.QQ,title,content,targetUrl);
                break;
            case R.id.tvSina:
                share(SHARE_MEDIA.SINA,title,content,targetUrl);
                break;
            case R.id.btnCancel:
                dismiss();
                break;
        }
    }

    UMShareListener listener=new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA share_media) {
            LogUtils.d("share_media:"+share_media);
            Toast.makeText(context, share_media + " 分享成功", Toast.LENGTH_SHORT).show();
            dismiss();
        }

        @Override
        public void onError(SHARE_MEDIA share_media, Throwable throwable) {
            LogUtils.e("share_media:"+share_media+throwable.getMessage());
            Toast.makeText(context, share_media + " 分享失败", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media) {
            LogUtils.e("share_media:"+share_media);
            Toast.makeText(context, share_media + " 分享取消了", Toast.LENGTH_SHORT).show();
            dismiss();
        }
    };

    public void share(SHARE_MEDIA platform, String title, String content, String targetUrl) {
//        http://yzl.gzldrj.com/wap/member/us
        UMImage umImage = new UMImage(context, R.drawable.icon_logo);
        new ShareAction((Activity) context).setPlatform(platform)
                .withTitle(title)
                .withText(" ")
                .withMedia(umImage)
                .withTargetUrl(targetUrl)
                .setCallback(listener)
                .share();
    }

}
