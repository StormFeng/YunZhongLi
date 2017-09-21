package com.lida.cloud.widght;

import android.content.Context;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lida.cloud.R;
import com.vondear.rxtools.view.RxToast;

/**
 * Created by Administrator on 2017/3/11 0011.
 */

public class NumberWidget extends LinearLayout implements View.OnClickListener {

    private TextView tvPlus;
    private TextView tvReduce;
    private EditText etNumber;
    private LinearLayout llReduce;
    private OnBtnClickedListener listener;

    private int limit;

    public NumberWidget(Context context) {
        super(context);
        init();
    }

    public NumberWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NumberWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        View view = View.inflate(getContext(), R.layout.numberwidget,this);
        etNumber = (EditText) view.findViewById(R.id.et_number);
        tvPlus = (TextView) view.findViewById(R.id.tv_plus);
        tvReduce = (TextView) view.findViewById(R.id.tv_reduce);
        tvPlus.setOnClickListener(this);
        tvReduce.setOnClickListener(this);
    }

    public void setOnNumberChangeListener(TextWatcher watcher){
        etNumber.addTextChangedListener(watcher);
    }

    public String getNumber(){
        return etNumber.getText().toString().trim();
    }

    public void setNumber(String i){
        etNumber.setText(i);
    }

    @Override
    public void onClick(View v) {
        String number = etNumber.getText().toString().trim();
        int i = Integer.valueOf(number);
        if(R.id.tv_plus == v.getId()){
            if(i==limit){
                RxToast.error("超出库存数量");
                return;
            }
            i++;
            if(listener!=null){
                listener.onBtnClicked(i,true);
            }
        }else{
            if(i==1){
                return;
            }else{
                i--;
                if(listener!=null){
                    listener.onBtnClicked(i,false);
                }
            }
        }
        etNumber.setText(i+"");
    }

    public void setLimit(int limit){
        setNumber("0");
        this.limit = limit;
    }

    public interface OnBtnClickedListener{
        void onBtnClicked(int i,boolean isAdd);
    }

    public void setOnBtnClickedListener(OnBtnClickedListener listener){
        this.listener=listener;
    }
}
