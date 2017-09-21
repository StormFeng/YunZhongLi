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
import com.lida.cloud.bean.ShopGoodBean;
import com.midian.base.app.AppContext;
import com.midian.base.util.UIHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 商铺详情
 * Created by WeiQingFeng on 2017/8/25.
 */

public class AdapterActivityShopDetail extends BaseAdapter {

    private Context context;
    private AppContext ac;
    private List<ShopGoodBean.DataBean.GoodsBean> data;

    public AdapterActivityShopDetail(Context context, List<ShopGoodBean.DataBean.GoodsBean> data) {
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
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_shopgood, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String goods_image = data.get(position).getGoods_image();
        if(goods_image!=null){
            ac.setImage(viewHolder.iv,goods_image);
        }
        viewHolder.tvName.setText(data.get(position).getSp_name());
        viewHolder.tvIntegral.setText("积分："+data.get(position).getCost());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("id",data.get(position).getSp_id());
                UIHelper.jump((Activity) context, ActivityGoodDetail.class, bundle);
            }
        });
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvIntegral)
        TextView tvIntegral;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
