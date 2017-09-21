package com.lida.cloud.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lida.cloud.R;
import com.lida.cloud.bean.OrderListBean;
import com.midian.base.app.AppContext;
import com.vondear.rxtools.RxDataUtils;
import com.vondear.rxtools.RxTextUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 我的订单
 * Created by Administrator on 2017/8/10.
 */

public class AdapterFragmentOrderChild extends BaseAdapter {

    private Context context;
    private List<OrderListBean.DataBean.OrderBean.GoodsBean> data;
    private AppContext ac;

    public AdapterFragmentOrderChild(Context context, List<OrderListBean.DataBean.OrderBean.GoodsBean> data) {
        this.context = context;
        this.data = data;
        ac = (AppContext) context.getApplicationContext();
    }

    @Override
    public int getCount() {
        return data==null?0:data.size();
    }

    @Override
    public Object getItem(int position) {
        return data==null?0:data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_fragmentorder_child, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        OrderListBean.DataBean.OrderBean.GoodsBean bean = data.get(position);
        viewHolder.tvGoodName.setText(bean.getGoods_name());
        viewHolder.tvPrice.setText(bean.getCost_price());
        viewHolder.tvGoodDes.setText(bean.getSpec_name());
        RxTextUtils.getBuilder(bean.getMarket_price()).setStrikethrough().into(viewHolder.tvOldPrice);
        viewHolder.tvColor.setText(bean.getSpec_name());
        viewHolder.tvColor.setText("×"+bean.getGoods_num());
        if(!RxDataUtils.isNullString(bean.getGoods_image())){
            ac.setImage(viewHolder.ivGood,bean.getGoods_image());
        }
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.ivGood)
        ImageView ivGood;
        @BindView(R.id.tvGoodName)
        TextView tvGoodName;
        @BindView(R.id.tvPrice)
        TextView tvPrice;
        @BindView(R.id.tvGoodDes)
        TextView tvGoodDes;
        @BindView(R.id.tvOldPrice)
        TextView tvOldPrice;
        @BindView(R.id.tvColor)
        TextView tvColor;
        @BindView(R.id.tvSize)
        TextView tvSize;
        @BindView(R.id.tvCount)
        TextView tvCount;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
