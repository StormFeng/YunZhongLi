package com.lida.cloud.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.lida.cloud.R;
import com.lida.cloud.activity.ActivityShopDetail;
import com.lida.cloud.bean.CollectShopBean;
import com.midian.base.app.AppContext;
import com.midian.base.util.UIHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 收藏的店铺
 * Created by WeiQingFeng on 2017/8/25.
 */

public class AdapterFragmetCollectionShop extends BaseAdapter {

    private Context context;
    private List<CollectShopBean.DataBean.ListBean> data;
    private AppContext ac;

    public AdapterFragmetCollectionShop(Context context, List<CollectShopBean.DataBean.ListBean> data) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_fragmentshoplist, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if(data.get(position).getLogo()!=null){
            ac.setImage(viewHolder.iv, data.get(position).getLogo());
        }
        viewHolder.tvName.setText(data.get(position).getSelshopname());
        viewHolder.tvSelBack.setText("消费立享" + data.get(position).getSelback() + "补贴");
        viewHolder.tvDistance.setText(data.get(position).getDistant());
        viewHolder.tvPosition.setText(data.get(position).getSelshopadd());
        if (data.get(position).isEdit()){
            viewHolder.cb.setVisibility(View.VISIBLE);
        }else{
            viewHolder.cb.setVisibility(View.GONE);
        }
        if(data.get(position).isSelect()){
            viewHolder.cb.setChecked(true);
        }else{
            viewHolder.cb.setChecked(false);
        }
        viewHolder.cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.get(position).setSelect(!data.get(position).isSelect());
                notifyDataSetChanged();
            }
        });
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("selid", data.get(position).getSelid());
                UIHelper.jump((Activity) context, ActivityShopDetail.class, bundle);
            }
        });
        return convertView;
    }

    public void toggleEdit(boolean var){
        if(data!=null){
            for (int i = 0; i < data.size(); i++) {
                data.get(i).setEdit(var);
            }
            notifyDataSetChanged();
        }
    }

    public List<CollectShopBean.DataBean.ListBean> getData() {
        return data;
    }

    static class ViewHolder {
        @BindView(R.id.cb)
        CheckBox cb;
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvSelBack)
        TextView tvSelBack;
        @BindView(R.id.tvPosition)
        TextView tvPosition;
        @BindView(R.id.tvDistance)
        TextView tvDistance;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
