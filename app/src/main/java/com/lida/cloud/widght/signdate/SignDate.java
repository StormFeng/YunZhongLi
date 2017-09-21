package com.lida.cloud.widght.signdate;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.lida.cloud.R;
import com.lida.cloud.widght.InnerGridView;
import com.vondear.rxtools.RxDataUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/16.
 */

public class SignDate extends LinearLayout {

    private TextView tvYear;
    private InnerGridView gvWeek;
    private InnerGridView gvDate;
    private AdapterDate adapterDate;

    private List<Integer> days = new ArrayList<>();
    private List<Boolean> status = new ArrayList<>();
    private List<String> initData = new ArrayList<>();

    public SignDate(Context context) {
        super(context);
        init();
    }

    public SignDate(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SignDate(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        View view = View.inflate(getContext(),R.layout.layout_signdate,this);
        tvYear = (TextView) view.findViewById(R.id.tvYear);
        gvWeek = (InnerGridView) view.findViewById(R.id.gvWeek);
        gvDate = (InnerGridView) view.findViewById(R.id.gvDate);
        tvYear.setText(DateUtil.getCurrentYearAndMonth());
        gvWeek.setAdapter(new AdapterWeek(getContext()));
        adapterDate = new AdapterDate(getContext(),initData,days,status);
        gvDate.setAdapter(adapterDate);
        initData();
    }

    private void initData(){
        days.clear();
        status.clear();
        int maxDay = DateUtil.getCurrentMonthLastDay();
        for (int i = 0; i < DateUtil.getFirstDayOfMonth() - 1; i++) {
            days.add(0);
            status.add(false);
        }
        for (int i = 0; i < maxDay; i++) {
            int a = i + 1;
            days.add(a);
            String temp;
            if(a<10){
                temp = "0"+a;
            }else{
                temp = a+"";
            }
            if(initData.contains(temp)){
                status.add(true);
            }else{
                status.add(false);
            }
        }
        adapterDate.notifyDataSetChanged();
    }

    public void setOnSignedSuccess(OnSignedSuccess onSignedSuccess){
        adapterDate.setOnSignedSuccess(onSignedSuccess);
    }

    public void setInitData(List<String> initData){
        this.initData = initData;
        initData();
    }
}
