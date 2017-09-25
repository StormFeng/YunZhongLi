package com.lida.cloud.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lida.cloud.R;
import com.lida.cloud.activity.ActivityGoodDetail;
import com.lida.cloud.bean.ActivityGoodListBean;
import com.midian.base.app.AppContext;
import com.midian.base.util.UIHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 商品列表
 * Created by WeiQingFeng on 2017/8/25.
 */

public class AdapterActivityGoodList extends BaseAdapter {

    private Context context;
    private List<ActivityGoodListBean.DataBean.ListBean> data;
    private AppContext ac;

    public AdapterActivityGoodList(Context context, List<ActivityGoodListBean.DataBean.ListBean> data) {
        this.context = context;
        this.data = data;
        ac = (AppContext) context.getApplicationContext();
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public Object getItem(int position) {
        return data == null ? 0 : data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_activitygoodlist, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ac.setImage(viewHolder.iv,data.get(position).getGoods_image());
        viewHolder.tvGoodName.setText(data.get(position).getGoods_name());
        viewHolder.tvPrice.setText("消费豆："+data.get(position).getCost());
        viewHolder.tvAddress.setText(data.get(position).getCity());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("id", String.valueOf(data.get(position).getGoods_id()));
                UIHelper.jump((Activity) context, ActivityGoodDetail.class,bundle);
            }
        });
        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.tvGoodName)
        TextView tvGoodName;
        @BindView(R.id.tvWelfare)
        TextView tvWelfare;
        @BindView(R.id.tvAddress)
        TextView tvAddress;
        @BindView(R.id.tvPrice)
        TextView tvPrice;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
