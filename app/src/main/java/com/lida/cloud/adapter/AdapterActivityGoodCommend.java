package com.lida.cloud.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;
import com.lida.cloud.R;
import com.lida.cloud.activity.ActivityGoodCommend;
import com.lida.cloud.bean.OrderEvaluateBean;
import com.lida.cloud.widght.InnerGridView;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by WeiQingFeng on 2017/9/13.
 */

public class AdapterActivityGoodCommend extends BaseAdapter {

    private ActivityGoodCommend _activity;
    private List<OrderEvaluateBean.DataBean> data;

    public AdapterActivityGoodCommend(ActivityGoodCommend _activity, List<OrderEvaluateBean.DataBean> data) {
        this._activity = _activity;
        this.data = data;
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
            convertView = LayoutInflater.from(_activity).inflate(R.layout.item_activitygoodcommend, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvName.setText("商品名称："+data.get(position).getGoods_name());
        viewHolder.tvSpec.setText("商品规格："+data.get(position).getSpec_name());
        viewHolder.gvPic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int p, long id) {
                _activity.position=position;
            }
        });
        AdapterShopPic adapter = new AdapterShopPic(_activity, data.get(position).getPics());
        viewHolder.gvPic.setAdapter(adapter);
        viewHolder.etCon.setText(data.get(position).getCon());
        viewHolder.etCon.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    data.get(position).setCon(viewHolder.etCon.getText().toString());
                }
            }
        });
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvSpec)
        TextView tvSpec;
        @BindView(R.id.etCon)
        EditText etCon;
        @BindView(R.id.gvPic)
        InnerGridView gvPic;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
