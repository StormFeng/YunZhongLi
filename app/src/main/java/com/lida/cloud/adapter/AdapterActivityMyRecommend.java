package com.lida.cloud.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lida.cloud.R;
import com.midian.base.app.AppContext;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 我的推荐
 * Created by WeiQingFeng on 2017/8/25.
 */

public class AdapterActivityMyRecommend extends BaseAdapter {

    private Context context;
    private AppContext ac;

    public AdapterActivityMyRecommend(Context context) {
        this.context = context;
        ac = (AppContext) context.getApplicationContext();
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_activitymyrecommend, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvDate)
        TextView tvDate;
        @BindView(R.id.tvMoney)
        TextView tvMoney;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
