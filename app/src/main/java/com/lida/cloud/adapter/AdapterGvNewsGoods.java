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

public class AdapterGvNewsGoods extends BaseAdapter {

    private Context context;
    private List<IndexBean.DataBean.GoodsBean> goodsData = new ArrayList<>();
    private AppContext ac;

    public AdapterGvNewsGoods(Context context, List<IndexBean.DataBean.GoodsBean> goodsData) {
        this.context = context;
        this.goodsData = goodsData;
        ac = (AppContext) context.getApplicationContext();
    }

    @Override
    public int getCount() {
        return goodsData==null?0:goodsData.size();
    }

    @Override
    public Object getItem(int position) {
        return goodsData==null?0:goodsData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_home_gvnewgoods, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ac.setImage(viewHolder.iv,goodsData.get(position).getGoods_image());
        viewHolder.tv.setText(goodsData.get(position).getGoods_name());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("id",goodsData.get(position).getGoods_id()+"");
                UIHelper.jump((Activity) context, ActivityGoodDetail.class,bundle);
            }
        });
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.tv)
        TextView tv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
