package com.lida.cloud.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.lida.cloud.R;
import com.midian.base.base.BaseActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.vondear.rxtools.RxRegUtils;
import com.vondear.rxtools.RxTimeUtils;
import com.vondear.rxtools.view.RxToast;
import com.vondear.rxtools.view.dialog.RxDialogWheelYearMonthDay;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 销售额-选择日期
 * Created by WeiQingFeng on 2017/9/1.
 */

public class ActivitySalesChooseTime extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.btnStart)
    Button btnStart;
    @BindView(R.id.btnEnd)
    Button btnEnd;
    @BindView(R.id.btnQuery)
    Button btnQuery;

    private RxDialogWheelYearMonthDay dialogWheelYearMonthDay;
    private int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saleschoosetime);
        ButterKnife.bind(this);
        topbar.setTitle("销售额");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
    }

    @OnClick({R.id.btnStart, R.id.btnEnd, R.id.btnQuery})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnStart:
                showTimeDialog(R.id.btnStart);
                break;
            case R.id.btnEnd:
                showTimeDialog(R.id.btnEnd);
                break;
            case R.id.btnQuery:
                if(btnStart.getText().toString().trim().equals("")){
                    UIHelper.t(_activity,"请选择开始时间");
                    return;
                }
                if(btnEnd.getText().toString().trim().equals("")){
                    UIHelper.t(_activity,"请选择结束时间");
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString("starttime",btnStart.getText().toString());
                bundle.putString("endtime",btnEnd.getText().toString());
                UIHelper.jump(_activity,ActivityAgentCenterSalesVolume.class,bundle);
                break;
        }
    }

    public void showTimeDialog(int flag){
        this.flag = flag;
        if(dialogWheelYearMonthDay==null){
            dialogWheelYearMonthDay = new RxDialogWheelYearMonthDay(_activity);
            dialogWheelYearMonthDay.getCheckBox_day().setEnabled(false);
            dialogWheelYearMonthDay.getTv_cancle().setOnClickListener(onClickListener);
            dialogWheelYearMonthDay.getTv_sure().setOnClickListener(onClickListener);
        }
        dialogWheelYearMonthDay.show();
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.tv_sure:
                    String time = dialogWheelYearMonthDay.getSelectorYear() + "-" + dialogWheelYearMonthDay.getSelectorMonth()
                            + "-" + dialogWheelYearMonthDay.getSelectorDay();
                    switch (flag){
                        case R.id.btnStart:
                            String endTime = btnEnd.getText().toString();
                            if(RxRegUtils.isDate(endTime)){
                                if(RxTimeUtils.string2Milliseconds(endTime + " 00:00:00")<RxTimeUtils.string2Milliseconds(time + " 00:00:00")){
                                    RxToast.error("起始日期大于终止日期");
                                    return;
                                }
                            }
                            btnStart.setText(time);
                            break;
                        case R.id.btnEnd:
                            String startTime = btnStart.getText().toString();
                            if(RxRegUtils.isDate(startTime)){
                                if(RxTimeUtils.string2Milliseconds(time + " 00:00:00")<RxTimeUtils.string2Milliseconds(startTime + " 00:00:00")){
                                    RxToast.error("终止日期小于起始日期");
                                    return;
                                }
                            }
                            btnEnd.setText(time);
                            break;
                    }
                    break;
            }
            dialogWheelYearMonthDay.dismiss();
        }
    };

}
