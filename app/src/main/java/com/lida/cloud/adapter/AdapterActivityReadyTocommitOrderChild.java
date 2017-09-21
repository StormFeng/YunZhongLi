package com.lida.cloud.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lida.cloud.R;
import com.lida.cloud.bean.ActivityReadyToCommitOrderBean;
import com.midian.base.app.AppContext;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 提交订单-子项
 * Created by WeiQingFeng on 2017/9/7.
 */

public class AdapterActivityReadyTocommitOrderChild extends BaseAdapter {

    private List<ActivityReadyToCommitOrderBean.DataBean.ListBean.GoodsBean> data;
    private Context context;
    private AppContext ac;

    public AdapterActivityReadyTocommitOrderChild(Context context,List<ActivityReadyToCommitOrderBean.DataBean.ListBean.GoodsBean> data) {
        this.data = data;
        this.context = context;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_activityreadytocommitorderchild, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvName.setText(data.get(position).getSp_name());
        viewHolder.tvSpec.setText(data.get(position).getSpec_name());
        viewHolder.tvPrice.setText("消费豆："+data.get(position).getCost());
        viewHolder.tvCount.setText("×"+data.get(position).getTotal());
        if(data.get(position).getSp_image()!=null){
            ac.setImage(viewHolder.iv,data.get(position).getSp_image());
        }
        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvSpec)
        TextView tvSpec;
        @BindView(R.id.tvPrice)
        TextView tvPrice;
        @BindView(R.id.tvCount)
        TextView tvCount;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
