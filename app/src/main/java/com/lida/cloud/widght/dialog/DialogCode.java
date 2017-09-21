package com.lida.cloud.widght.dialog;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.lida.cloud.R;
import com.lida.cloud.bean.MemberQrcodeBean;
import com.lida.cloud.bean.PersonalInfoBean;
import com.midian.base.app.AppContext;
import com.vondear.rxtools.view.dialog.RxDialog;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by WeiQingFeng on 2017/8/29.
 */

public class DialogCode extends RxDialog {

    @BindView(R.id.ivHead)
    ImageView ivHead;
    @BindView(R.id.tvNick)
    TextView tvNick;
    @BindView(R.id.tvPhone)
    TextView tvPhone;
    @BindView(R.id.tvAoocuntId)
    TextView tvAoocuntId;
    @BindView(R.id.tvCode)
    ImageView ivCode;

    private AppContext ac;

    public DialogCode(Context context, int themeResId) {
        super(context, themeResId);
    }

    public DialogCode(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public DialogCode(Context context,PersonalInfoBean personalInfoBean) {
        super(context);
        ac = (AppContext) context.getApplicationContext();
        initData(personalInfoBean);
    }

    public void initData(PersonalInfoBean personalInfoBean) {
        setContentView(R.layout.dialog_code);
        ButterKnife.bind(this);
        if(personalInfoBean.getData().get(0).getMem_tx()!=null){
            ac.setImage(ivHead,personalInfoBean.getData().get(0).getMem_tx());
        }
        if(personalInfoBean.getData().get(0).getQrcode()!=null){
            ac.setImage(ivCode,personalInfoBean.getData().get(0).getQrcode());
        }
        tvNick.setText("昵称："+personalInfoBean.getData().get(0).getMem_nc());
        tvPhone.setText("电话："+personalInfoBean.getData().get(0).getMem_tel());
        tvAoocuntId.setText("I    D："+personalInfoBean.getData().get(0).getMemid());
    }
}
