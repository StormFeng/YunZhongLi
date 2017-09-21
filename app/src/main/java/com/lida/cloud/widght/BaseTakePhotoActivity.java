package com.lida.cloud.widght;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;

import com.jph.takephoto.app.TakePhotoActivity;
import com.midian.base.app.AppContext;
import com.midian.base.widget.dialog.LoadingDialog;

/**
 * Created by WeiQingFeng on 2017/9/7.
 */

public class BaseTakePhotoActivity extends TakePhotoActivity {
    protected AppContext ac;
    protected LoadingDialog dlg;
    protected Activity _activity;
    public boolean flag  = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ac = (AppContext) getApplicationContext();
        _activity = this;
    }

    public void showLoadingDlg(String msg, final boolean isNotBackFinish) {
        if (dlg != null && dlg.isShowing()) {
            return;
        }
        if (dlg == null) {
            dlg = new LoadingDialog(this, com.bishilai.thirdpackage.R.layout.dialog_msg,
                    com.bishilai.thirdpackage.R.style.dialog_msg);
        }
        dlg.setCanceledOnTouchOutside(isNotBackFinish);
        dlg.setOnCancelListener(new DialogInterface.OnCancelListener() {

            @Override
            public void onCancel(DialogInterface dialog) {
                if (!isNotBackFinish) {
                    finish();
                }
            }
        });
        dlg.showMessage(msg);
    }

    public void showLoadingDlg() {
        showLoadingDlg("", true);
    }

    public void hideLoadingDlg() {
        if (dlg != null) {
            dlg.dismiss();
        }
    }
}
