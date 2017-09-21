package com.lida.cloud.widght.signdate;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lida.cloud.R;
import com.vondear.rxtools.RxDataUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/16.
 */

public class AdapterDate extends BaseAdapter {

    private Context context;
    private List<Integer> days = new ArrayList<>();
    private List<Boolean> status = new ArrayList<>();
    private OnSignedSuccess onSignedSuccess;
    private List<String> initData;

    public AdapterDate(Context context,List<String> initData,List<Integer> days,List<Boolean> status) {
        this.context = context;
        this.initData = initData;
        this.days = days;
        this.status = status;
    }

    @Override
    public int getCount() {
        return days.size();
    }

    @Override
    public Object getItem(int i) {
        return days.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view==null){
            view = LayoutInflater.from(context).inflate(R.layout.item_gv,null);
            viewHolder = new ViewHolder();
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.tv = (TextView) view.findViewById(R.id.tvWeek);
        viewHolder.rlItem = (RelativeLayout) view.findViewById(R.id.rlItem);
        viewHolder.ivStatus = (ImageView) view.findViewById(R.id.ivStatus);
        viewHolder.tv.setText(days.get(i)+"");
        if(days.get(i)==0){
            viewHolder.rlItem.setVisibility(View.GONE);
        }
        if(status.get(i)){
            viewHolder.tv.setTextColor(Color.parseColor("#FD0000"));
            viewHolder.ivStatus.setVisibility(View.VISIBLE);
        }else{
            viewHolder.tv.setTextColor(Color.parseColor("#666666"));
            viewHolder.ivStatus.setVisibility(View.GONE);
        }
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(status.get(i)){
//                    Toast.makeText(context,"Already sign in!", Toast.LENGTH_SHORT).show();
//                }else{
//                    Toast.makeText(context,"Sign in success!", Toast.LENGTH_SHORT).show();
//                    status.set(i,true);
//                    notifyDataSetChanged();
//                    if(onSignedSuccess!=null){
//                        onSignedSuccess.OnSignedSuccess();
//                    }
//                }
//            }
//        });
        return view;
    }

    class ViewHolder{
        RelativeLayout rlItem;
        TextView tv;
        ImageView ivStatus;
    }

    public void setOnSignedSuccess(OnSignedSuccess onSignedSuccess){
        this.onSignedSuccess = onSignedSuccess;
    }
}
