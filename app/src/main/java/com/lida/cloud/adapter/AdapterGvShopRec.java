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
import com.lida.cloud.activity.ActivityShopDetail;
import com.lida.cloud.bean.IndexBean;
import com.midian.base.app.AppContext;
import com.midian.base.util.UIHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/8.
 */

public class AdapterGvShopRec extends BaseAdapter {

    private Context context;
    private List<IndexBean.DataBean.StoreBean> shopData = new ArrayList<>();
    private AppContext ac;

    public AdapterGvShopRec(Context context, List<IndexBean.DataBean.StoreBean> shopData) {
        this.context = context;
        this.shopData = shopData;
        ac = (AppContext) context.getApplicationContext();
    }

    @Override
    public int getCount() {
        return shopData==null?0:shopData.size();
    }

    @Override
    public Object getItem(int position) {
        return shopData==null?0:shopData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_home_shoprec, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvName.setText(shopData.get(position).getStore_name());
        viewHolder.tvPositon.setText(shopData.get(position).getStore_address());
        if(shopData.get(position).getLogo()!=null){
            ac.setImage(viewHolder.iv,shopData.get(position).getLogo());
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("selid",shopData.get(position).getStore_id()+"");
                UIHelper.jump((Activity) context, ActivityShopDetail.class,bundle);
            }
        });
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvPositon)
        TextView tvPositon;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
