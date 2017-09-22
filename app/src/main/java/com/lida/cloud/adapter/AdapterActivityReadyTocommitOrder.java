package com.lida.cloud.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.lida.cloud.R;
import com.lida.cloud.bean.ActivityReadyToCommitOrderBean;
import com.midian.base.app.AppContext;
import com.midian.base.widget.ListViewForScrollView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 提交订单
 * Created by WeiQingFeng on 2017/9/7.
 */

public class AdapterActivityReadyTocommitOrder extends BaseAdapter {

    private List<ActivityReadyToCommitOrderBean.DataBean.ListBean> data;
    private Context context;
    private AppContext ac;

    public AdapterActivityReadyTocommitOrder(Context context,List<ActivityReadyToCommitOrderBean.DataBean.ListBean> data) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_activityreadytocommitorder, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvName.setText(data.get(position).getSelshopname());
        viewHolder.lvGoods.setAdapter(new AdapterActivityReadyTocommitOrderChild(context,data.get(position).getGoods()));
        viewHolder.etRemark.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    data.get(position).setRemark(viewHolder.etRemark.getText().toString());
                }
            }
        });
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.lvGoods)
        ListViewForScrollView lvGoods;
        @BindView(R.id.tvSelBack)
        TextView tvSelBack;
        @BindView(R.id.tvExpress)
        TextView tvExpress;
        @BindView(R.id.etRemark)
        EditText etRemark;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
