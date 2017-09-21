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

import com.lida.cloud.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 确定删除？
 * Created by Administrator on 2016/10/28 0028.
 */

public class DialogGoSetting extends Dialog {

    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.btn_ok)
    Button btnOk;
    @BindView(R.id.tv)
    TextView tv;

    private Context context;
    private onButtonClickListener listener;

    public DialogGoSetting(Context context) {
        super(context, R.style.diy_dialog);
        init(context);
    }

    public DialogGoSetting(Context context, int themeResId) {
        super(context, R.style.diy_dialog);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        Window w = this.getWindow();
        WindowManager.LayoutParams lp = w.getAttributes();
        lp.gravity = Gravity.CENTER;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        w.setAttributes(lp);
        this.setCanceledOnTouchOutside(true);
        View v = View.inflate(context, R.layout.dialog_gosetting, null);
        this.setContentView(v);
        ButterKnife.bind(this, v);
    }

    @OnClick({R.id.btn_cancel, R.id.btn_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_cancel:
                ((Activity)context).finish();
                break;
            case R.id.btn_ok:
                if(listener!=null){
                    listener.onGoSettingClick();
                }
                break;
        }
    }

    public interface onButtonClickListener{
        void onGoSettingClick();
    }

    public void setOnButtonClickListener(onButtonClickListener listener){
        this.listener = listener;
    }
}
